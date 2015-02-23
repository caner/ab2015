package org.ab2015.ab2015duyuru.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.ab2015.ab2015duyuru.DuyuruOperation;
import org.ab2015.ab2015duyuru.R;
import org.ab2015.ab2015duyuru.adapter.DuyuruAdapter;
import org.ab2015.ab2015duyuru.model.Duyuru;

import java.util.ArrayList;

public class ListeActivity extends ActionBarActivity {

    ListView listView;
    ArrayList<Duyuru> duyuruListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        //ListView'a ulaşalım
        listView= (ListView) findViewById(R.id.listDuyurular);

        //boş bir liste oluşturuyoruz
        duyuruListesi=new ArrayList<Duyuru>();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Activity onResume olurken, duyuruları veri tabanından çekelim
        DuyuruListesiGetirTask task=new DuyuruListesiGetirTask();
        task.execute();
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
