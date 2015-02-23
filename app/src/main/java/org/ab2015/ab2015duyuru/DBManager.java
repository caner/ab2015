package org.ab2015.ab2015duyuru;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper{
    static String mDbName="DuyuruPanosuDB";
    static int version=1;
    public static final String TABLO_ADI="tableDuyuru";
    public static final String SUTUN_ID="col_ID";
    public static final String SUTUN_BASLIK="col_Baslik";
    public static final String SUTUN_ICERIK="col_Icerik";

    public DBManager(Context context) {
        super(context, mDbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //debug amaçlı ekleyelim
        Log.d(this.getClass().getSimpleName().toString(), "onCreate");

        //hangi tabloları oluşturmak istiyorsak onun SQL kodlarını yazıyoruz
        String sqlIfade="CREATE TABLE " +
                TABLO_ADI+
                " ("+
                SUTUN_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL," +
                SUTUN_BASLIK+" VARCHAR NOT NULL, "+
                SUTUN_ICERIK+" VARCHAR NOT NULL )";
        db.execSQL(sqlIfade);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
