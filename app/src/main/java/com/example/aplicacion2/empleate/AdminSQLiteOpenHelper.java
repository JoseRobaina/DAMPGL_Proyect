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

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Empleate.db";

    public AdminSQLiteOpenHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        //context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table perfil(" +
                        "id integer primary key," +
                        "nombre text, " +
                        "apellidos text, " +
                        "dni text, " +
                        "fecha_nacimiento text, " +
                        "telefono integer, " +
                        "movil integer, " +
                        "direccion text, " +
                        "email text)"
        );

        db.execSQL("create table login(id integer,login text primary key, pass text,FOREIGN KEY (id) REFERENCES perfil(id))");

        // Contenedor de valores
        ContentValues values = new ContentValues();

        // Pares clave-valor
        values.put("id", "1");
        values.put("nombre", "Jose");
        values.put("apellidos", "Robaina Travieso");
        values.put("dni", "45770424X");
        values.put("fecha_nacimiento", "20/05/1987");
        values.put("telefono", "92866666666");
        values.put("movil", "66666666666");
        values.put("direccion", "Las Palmas");
        values.put("email", "jr_robaina@hotmail.com");

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
        db.execSQL("drop table if exists login");

       onCreate(db);

       /* db.execSQL("create table perfil(" +
                "id integer primary key," +
                "nombre text, " +
                "apellidos text, " +
                "dni text, " +
                "fecha_nacimiento text, " +
                "telefono integer, " +
                "movil integer, " +
                "direccion text, " +
                "email text)"
        );

        db.execSQL("create table login(id integer,login text primary key, pass text,FOREIGN KEY (id) REFERENCES perfil(id))");*/

    }
}
