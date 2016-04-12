package com.example.yoso.quotilius02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yoso on 15/03/16.
 */
public class QuotesReaderDbHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Quotes.db";
    public static final int DATABASE_VERSION = 1;

    public QuotesReaderDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la base de datos
        db.execSQL(QuotesDataSource.CREATE_QUOTES_SCRIPT);
        // Insertar registros iniciales
        db.execSQL(QuotesDataSource.INSERT_QUOTES_SCRIPT);
          /*  Nota: Usamos execSQL() ya que las sentencias son
            para uso interno y no están relacionadas con entradas
            proporcionadas por los usuarios
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*  Añade los cambios que se realizarán en el esquema
                en tu proxima versión
        */
    }
}
