package org.ab2015.ab2015duyuru.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.apigee.sdk.data.client.ApigeeDataClient;
import com.apigee.sdk.data.client.callbacks.ApiResponseCallback;
import com.apigee.sdk.data.client.entities.Entity;
import com.apigee.sdk.data.client.response.ApiResponse;

import org.ab2015.ab2015duyuru.DuyuruOperation;
import org.ab2015.ab2015duyuru.R;
import org.ab2015.ab2015duyuru.adapter.DuyuruAdapter;
import org.ab2015.ab2015duyuru.model.Duyuru;

import java.util.ArrayList;
import java.util.List;

public class ListeActivity extends ActionBarActivity {

    ListView listView;
    ArrayList<Duyuru> duyuruListesi;

    // ApigeeDataClient sınıfını Activity'mize
    // üye olarak ekleyelim
    ApigeeDataClient client;
    DuyuruAdapter adapter;
    List<Entity> entityList; //yanıttan dönen Entity'leri tutan liste

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        //ListView'a ulaşalım
        listView= (ListView) findViewById(R.id.listDuyurular);

        //boş bir liste oluşturuyoruz
        duyuruListesi=new ArrayList<Duyuru>();

        //asıl işi yapacak olan nesneyi oluşturalım
        client=new ApigeeDataClient("cgural","sandbox",this);
    }

    private void getDuyuruFromUsergrid(){
        // duyuru tipindeki entity'leri (varlık) getirelim
        // parametreler: entity type, query string, callback
        // UYARI: bu metodun düzgün çalışması için AndroidManifest.xml'de
        // gerekli izni (android.permission.INTERNET) vermelisiniz
        client.getEntitiesAsync("duyuru?limit=1000","",new ApiResponseCallback() {
            @Override
            public void onResponse(ApiResponse apiResponse) {
                if(null!=apiResponse){

                    //sunucudan yanıt başarıyla gelirse
                    Log.i("ListeActivity", "Şu kadar duyuru var: " + apiResponse.getEntityCount());

                    //apiResponse nesnesi şeklinde dönen yanıtın
                    //içinden Entity'leri alalım
                    //HATIRLATMA: Sunucudan dönen JSON yanıtında "entities" adında bir dizi vardı
                    entityList=apiResponse.getEntities();

                    if(null!=entityList){
                        //listenin içinde dönelim
                        for(int i=0;i<entityList.size();i++){
                            Entity entity=entityList.get(i); //listeden Entity'leri tek tek al

                            // property'leri al, ilgili String'e ata

                            String baslik=entity.getStringProperty("baslik");
                            if(null==baslik){ baslik="Başlıksız";}

                            String icerik=entity.getStringProperty("icerik");
                            if(null==icerik){ icerik="İçerik yok"; }

                            //Long saat=entity.getLongProperty("created");

                            //duyuru nesnesini oluşturalım
                            Duyuru duyuru=new Duyuru();
                            duyuru.setDuyuruBaslik(baslik);
                            duyuru.setDuyuruIcerik(icerik);

                            //son olarak duyuru'ları duyuruListesi'ne (ArrayList) ekleyelim
                            duyuruListesi.add(duyuru);
                        }
                    }
                    adapter=new DuyuruAdapter(ListeActivity.this,duyuruListesi);
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);

                    Log.i(ListeActivity.class.getSimpleName().toString(), "duyuruListesi'nin boyutu şu: " + duyuruListesi.size());
                }
            }

            @Override
            public void onException(Exception e) {
                //sunucudan yanıtın gelmesinde bir sorun olursa

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDuyuruFromUsergrid();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liste, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id==R.id.action_yeniekle){
            Intent intent;
            intent = new Intent(this, DuyuruEkleActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public class DuyuruListesiGetirTask extends AsyncTask<Void, Void, ArrayList<Duyuru>> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //işleme başlamadan önce ProgressDialog gösterelim
            dialog=new ProgressDialog(ListeActivity.this);
            dialog.setTitle("İşlem yapılıyor...");
            dialog.setMessage("Lütfen bekleyiniz");
            dialog.show();

        }

        @Override
        protected ArrayList<Duyuru> doInBackground(Void... params) {
            DuyuruOperation duyuruOp=new DuyuruOperation(ListeActivity.this);
            return duyuruOp.getDuyuruList();
        }

        @Override
        protected void onPostExecute(ArrayList<Duyuru> duyurus) {
            super.onPostExecute(duyurus);

            DuyuruAdapter adapter=new DuyuruAdapter(ListeActivity.this,duyurus);
            listView.setAdapter(adapter);

            //işlem bittiğine göre ProgressDialog'u kapatalım
            dialog.dismiss();
        }
    }

}
