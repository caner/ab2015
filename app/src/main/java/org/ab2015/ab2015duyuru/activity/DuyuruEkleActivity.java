package org.ab2015.ab2015duyuru.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ab2015.ab2015duyuru.DuyuruOperation;
import org.ab2015.ab2015duyuru.R;
import org.ab2015.ab2015duyuru.model.Duyuru;

public class DuyuruEkleActivity extends ActionBarActivity {

    EditText txtDuyuruBaslik,txtDuyuruIcerik;
    Button btnDuyuruKaydet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyuru_ekle);

        txtDuyuruBaslik= (EditText) findViewById(R.id.txtDuyuruBaslik);
        txtDuyuruIcerik= (EditText) findViewById(R.id.txtDuyuruIcerik);
        btnDuyuruKaydet= (Button) findViewById(R.id.btnDuyuruKaydet);

        btnDuyuruKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DuyuruOperation op=new DuyuruOperation(DuyuruEkleActivity.this);
                Duyuru duyuru=new Duyuru(txtDuyuruBaslik.getText().toString(),
                        txtDuyuruIcerik.getText().toString());

                //şık kullanım
                /*Duyuru duyuru=new Duyuru(
                        txtDuyuruBaslik.getText().toString()==null ?
                                "Başlıksız" : txtDuyuruBaslik.getText().toString(),
                        txtDuyuruIcerik.getText().toString()==null ?
                                "Başlıksız" : txtDuyuruIcerik.getText().toString());*/

                long sonuc=op.duyuruEkle(duyuru);

                if(sonuc>-1){
                    Toast.makeText(DuyuruEkleActivity.this, "Kaydedildi", Toast.LENGTH_SHORT).show();

                    //kayıt başarılıysa bu Activity'yi kapatalım
                    finish();
                }else{
                    Toast.makeText(DuyuruEkleActivity.this,"Kaydedilemedi!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_duyuru_ekle, menu);
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
}
