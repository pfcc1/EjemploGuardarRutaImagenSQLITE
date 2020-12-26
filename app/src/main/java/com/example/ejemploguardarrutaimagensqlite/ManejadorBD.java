package com.example.ejemploguardarrutaimagensqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ManejadorBD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "usuarios.db";
    private static final String TABLE_NAME = "usuarios";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NOMBRE";
    private static final String COL_3 = "IMAGEN";

    public ManejadorBD(Context context){
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME +
                "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_2+ " TEXT, " +COL_3+ " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertar(String nombre, String rutaimagen){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nombre);
        contentValues.put(COL_3, rutaimagen);


        long resultado = db.insert(TABLE_NAME, null, contentValues);


        if (resultado ==-1) {
            return false;
        }else{
            return true;
        }

    }

    Cursor listarUsuarios(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor datos = db.rawQuery("SELECT * FROM usuarios",null);
        return datos;

    }
}
