package org.ab2015.ab2015duyuru.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.ab2015.ab2015duyuru.R;
import org.ab2015.ab2015duyuru.model.Duyuru;

import java.util.ArrayList;

public class DuyuruAdapter extends BaseAdapter{

    private ArrayList<Duyuru> veriKumesi;
    private Context context;

    //layout oluşturucu
    LayoutInflater inflater;

    //yapılandırıcı metot
    public DuyuruAdapter(Context c, ArrayList<Duyuru> veriler){
        this.veriKumesi=veriler;
        this.context=c;

        inflater= (LayoutInflater)
                c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        //veri kümesinin boyutunu döndürür
        return veriKumesi.size();
    }

    @Override
    public Duyuru getItem(int position) {
        //sırası belli nesneyi döndürür
        return veriKumesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        //veri kümesindeki elemanın ID'sini döndürüyoruz
        //şimdilik elemanın position değeri bize yeterli
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        //tek satır olarak göndereceğimiz View'ı oluşturmaya başlıyoruz
        satirView=inflater.inflate(R.layout.liste_satir_layout,null);

        //satirView'ın içindeki TextView'lara erişiyoruz
        TextView textViewBaslik= (TextView) satirView.findViewById(R.id.txtListeDuyuruBaslik);
        TextView textViewIcerik= (TextView) satirView.findViewById(R.id.txtListeDuyuruIcerik);

        // başlık verisini Duyuru nesnesinden,
        // onu da veriKumesi isimli listeden çekiyoruz
        String baslikString=veriKumesi.get(position).getDuyuruBaslik();
        textViewBaslik.setText(baslikString);

        // içerik verisini Duyuru nesnesinden,
        // onu da veriKumesi isimli listeden çekiyoruz
        String icerikString=veriKumesi.get(position).getDuyuruIcerik();
        textViewIcerik.setText(icerikString);

        //her çağırdığının pozisyonunu yaz
        Log.i("Adapter", "Pozisyon: " + position);

        //satirView'ın içini veri ile doldurduk ve artık return ediyoruz
        return satirView;
    }
}
