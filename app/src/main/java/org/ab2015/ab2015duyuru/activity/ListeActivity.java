package org.ab2015.ab2015duyuru.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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

        //duyuruListesi'ni 20 tane Duyuru nesnesiyle dolduralım
        duyuruListesiniDoldur();

        //yazdığımız adapter'ı verileriyle oluşturalım
        DuyuruAdapter adapter=new DuyuruAdapter(this,duyuruListesi);

        //listView'a adapter'ını verelim
        listView.setAdapter(adapter);


    }

    private void duyuruListesiniDoldur() {
        for(int i=0;i<20;i++){
            Duyuru duyuru=new Duyuru("Duyuru başlık"+i,
                    "Duyuru içerik"+i);
            duyuruListesi.add(duyuru);
        }
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
}
