package com.example.aplicacion2.empleate.BDData;

/**
 * Created by Andrea on 29/2/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Empleate.db";

    public AdminSQLiteOpenHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        //context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table profile(" +
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

        db.execSQL("create table login (id integer,login text primary key, pass text,FOREIGN KEY (id) REFERENCES perfil(id))");

        db.execSQL("create table profile_titu(id_titu integer not null primary key autoincrement, " +
                "id_user integer not null, " +
                "titulacion text," +
                "FOREIGN KEY (id_user) REFERENCES perfil(id))"
        );

        db.execSQL("create table profile_exp(id_exp integer not null primary key autoincrement, " +
                "id_user integer not null, " +
                "puesto text not null," +
                "empresa text not null," +
                "meses integer," +
                "FOREIGN KEY (id_user) REFERENCES perfil(id))"
        );

        db.execSQL("create table ofertas(id_oferta integer not null primary key autoincrement, " +
                "titulo text not null, " +
                "descripcion text not null)"
        );

        db.execSQL("create table inscripion_oferta(id_inscripcion integer not null primary key autoincrement, " +
                "id_user integer not null, " +
                "id_oferta integer not null)"
        );

        // Contenedor de valores
        ContentValues vProfile = new ContentValues();

        // Pares clave-valor
        vProfile.put("id", "1");
        vProfile.put("nombre", "Jose");
        vProfile.put("apellidos", "Robaina Travieso");
        vProfile.put("dni", "45770424X");
        vProfile.put("fecha_nacimiento", "20/05/1987");
        vProfile.put("telefono", "92866666666");
        vProfile.put("movil", "66666666666");
        vProfile.put("direccion", "Las Palmas");
        vProfile.put("email", "jr_robaina@hotmail.com");

        // Insertar Perfil..
        db.insert("profile", null, vProfile);

        ContentValues vUserLogin = new ContentValues();

        // Pares clave-valor
        vUserLogin.put("id", "1");
        vUserLogin.put("login", "Jose");
        vUserLogin.put("pass", "123456");

        // Insertar Login...
        db.insert("login", null, vUserLogin);

        ContentValues vOfertas = new ContentValues();

        // Pares clave-valor
        vOfertas.put("titulo", "Programador Web");
        vOfertas.put("descripcion", "Se busca programador web con conocimientos de AngularJS");

        // Insertar Login...
        db.insert("ofertas", null, vOfertas);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {
        db.execSQL("drop table if exists perfil");
        db.execSQL("drop table if exists login");
        db.execSQL("drop table if exists profile_titu");
        db.execSQL("drop table if exists profile_exp");
        db.execSQL("drop table if exists ofertas");
        db.execSQL("drop table if exists inscripion_oferta");

        onCreate(db);
    }
}
