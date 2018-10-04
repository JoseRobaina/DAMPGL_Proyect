package com.example.aplicacion2.empleate;

/**
 * Created by Andrea on 29/2/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Empleate.db";

    public AdminSQLiteOpenHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table perfil(" +
                        "id integer primary key," +
                        "nombre text, " +
                        "dni integer, " +
                        "ciudad text, " +
                        "numero integer)"
        );

        db.execSQL("create table login(id integer,login text primary key, pass text,FOREIGN KEY (id) REFERENCES perfil(id))");

        // Contenedor de valores
        ContentValues values = new ContentValues();

        // Pares clave-valor
        values.put("id", "1");
        values.put("nombre", "Jose Robaina");
        values.put("dni", "45770424X");

        // Insertar...
        db.insert("perfil", null, values);

        ContentValues values2 = new ContentValues();

        // Pares clave-valor
        values2.put("id", "1");
        values2.put("login", "Jose");
        values2.put("pass", "123456");

        // Insertar...
        db.insert("login", null, values2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {
        db.execSQL("drop table if exists perfil");
        db.execSQL("create table perfil(" +
                "id integer primary key," +
                "nombre text," +
                "dni integer," +
                "ciudad text," +
                "numero integer)");
    }
}
