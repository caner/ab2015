package org.ab2015.ab2015duyuru.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.ab2015.ab2015duyuru.R;


public class GirisActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        Log.i(this.getClass().getSimpleName().toString(), "onCreate");

        //giriş düğmesine ulaşıyoruz
        Button btnGiris= (Button) findViewById(R.id.btnGiris);

        //kaydol düğmesine ulaşıyoruz
        Button btnKaydol= (Button) findViewById(R.id.btnKaydol);

        //giriş düğmesinin tıklanma/dokunma
        //olayını dinliyoruz
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent yeniActivityAcIntent;

                //şu anki Activity'den yeni Activity'ye geçiş
                //niyetimizi beyan ediyoruz
                yeniActivityAcIntent = new Intent(
                        GirisActivity.this, ListeActivity.class);
                yeniActivityAcIntent.setAction("duyurulistele");

                //Activity başlatma nitetiyle startActivity()
                // metodunu çalıştırıyoruz
                startActivity(yeniActivityAcIntent);

            }
        });

        btnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent yeniActivityAcIntent;

                //şu anki Activity'den yeni Activity'ye geçiş
                //niyetimizi beyan ediyoruz
                yeniActivityAcIntent = new Intent(
                        GirisActivity.this, KaydolActivity.class);

                //Activity başlatma nitetiyle startActivity()
                // metodunu çalıştırıyoruz
                startActivity(yeniActivityAcIntent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_giris, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(this.getClass().getSimpleName().toString(), "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(this.getClass().getSimpleName().toString(), "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(this.getClass().getSimpleName().toString(), "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(this.getClass().getSimpleName().toString(), "onPause");
    }
}
