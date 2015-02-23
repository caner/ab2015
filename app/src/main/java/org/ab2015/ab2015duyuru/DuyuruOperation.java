package org.ab2015.ab2015duyuru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.ab2015.ab2015duyuru.model.Duyuru;

import java.util.ArrayList;

public class DuyuruOperation {
    Context context;

    public DuyuruOperation(Context context) {
        this.context = context;
    }

    public ArrayList<Duyuru> getDuyuruList() {
        ArrayList<Duyuru> duyuruListesi = new ArrayList<Duyuru>();

        DBManager dbManager=new DBManager(context);
        SQLiteDatabase db=dbManager.getReadableDatabase();

        Cursor c=db.query(DBManager.TABLO_ADI,null,null,null,null,null,null);
        while(c.moveToNext()){
            int baslikIndex=c.getColumnIndex(DBManager.SUTUN_BASLIK);
            int icerikIndex=c.getColumnIndex(DBManager.SUTUN_ICERIK);

            String baslik=c.getString(baslikIndex);
            String icerik=c.getString(icerikIndex);

            Duyuru duyuru=new Duyuru(baslik,icerik);
            duyuruListesi.add(duyuru);
        }
        db.close();
        return duyuruListesi;
    }

    public long duyuruEkle(Duyuru duyuru){
        long sonuc=0;
        DBManager dbManager=new DBManager(context);
        SQLiteDatabase database=dbManager.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(DBManager.SUTUN_BASLIK,duyuru.getDuyuruBaslik());
        values.put(DBManager.SUTUN_ICERIK,duyuru.getDuyuruIcerik());

        //başarılıysa 1, başarısızsa -1 döner.
        sonuc=database.insert(DBManager.TABLO_ADI,null,values);
        database.close();
        return sonuc;
    }
}
