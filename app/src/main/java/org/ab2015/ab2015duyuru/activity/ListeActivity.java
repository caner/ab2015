package org.ab2015.ab2015duyuru.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.ab2015.ab2015duyuru.R;

public class ListeActivity extends ActionBarActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        String duyurular[]={"duyuru 1", "duyuru 2",
                "duyuru 3", "duyuru 4",
                "duyuru 5", "duyuru 6",
                "duyuru 7", "duyuru 8",
                "duyuru 9", "duyuru 10",
                "duyuru 11", "duyuru 12",
                "duyuru 13", "duyuru 14",
                "duyuru 15", "duyuru 16",
        };

        listView= (ListView) findViewById(R.id.listDuyurular);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                duyurular);
        listView.setAdapter(adapter);
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
