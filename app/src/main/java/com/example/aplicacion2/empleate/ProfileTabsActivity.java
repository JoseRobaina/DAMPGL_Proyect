package com.example.aplicacion2.empleate;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacion2.empleate.BDData.AdminSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileTabsActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    public static String UserID;
    public static int mPosition;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_tabs);

        //UserID = getIntent().getExtras().getString("Id");
        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        UserID = sp.getString("Id","");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_tabs, menu);
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
        } else if(id == R.id.action_save){
            UpdateProfile();
        } else if(id == R.id.action_refresh){
            finish();
            startActivity(getIntent());
        }

        return super.onOptionsItemSelected(item);
    }

    private void UpdateProfile() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,null);

        SQLiteDatabase bd = admin.getWritableDatabase();

        TextInputEditText VarNombre = (TextInputEditText) findViewById(R.id.editTextNombre);
        TextInputEditText VarApeliidos = (TextInputEditText) findViewById(R.id.editTextApellidos);
        TextInputEditText VarDni = (TextInputEditText) findViewById(R.id.editTextDni);
        TextInputEditText VarFNac = (TextInputEditText) findViewById(R.id.editTextFNac);
        TextInputEditText VarTlfno = (TextInputEditText) findViewById(R.id.editTextTFijo);
        TextInputEditText VarMovil = (TextInputEditText) findViewById(R.id.editTextMovil);
        TextInputEditText VarDirec = (TextInputEditText) findViewById(R.id.editTextDireccion);
        TextInputEditText VarEmail = (TextInputEditText) findViewById(R.id.editTextEmail);
        TextInputEditText VarTitulacion = (TextInputEditText) findViewById(R.id.InputEditTextTitu1);
        TextInputEditText VarCentro = (TextInputEditText) findViewById(R.id.InputEditTextCentro);
        TextInputEditText VarMesFin = (TextInputEditText) findViewById(R.id.InputEditTextMesFin);
        TextInputEditText VarAnyoFin = (TextInputEditText) findViewById(R.id.InputEditTextAnyoFin);
        TextInputEditText VarPuesto = (TextInputEditText) findViewById(R.id.InputEditTExtPuesto);
        TextInputEditText VarEmpresa = (TextInputEditText) findViewById(R.id.InputEditTextEmpresa);
        TextInputEditText VarMeses = (TextInputEditText) findViewById(R.id.InputEditTextMesesExp);

        ContentValues values = new ContentValues();
        values.put("nombre", VarNombre.getText().toString());
        values.put("apellidos", VarApeliidos.getText().toString());
        values.put("dni", VarDni.getText().toString());
        values.put("fecha_nacimiento", VarFNac.getText().toString());
        values.put("telefono", VarTlfno.getText().toString());
        values.put("movil", VarMovil.getText().toString());
        values.put("direccion", VarDirec.getText().toString());
        values.put("email", VarEmail.getText().toString());

        long ExistTitu = DatabaseUtils.queryNumEntries(bd, "profile_titu");
        long ExistExp = DatabaseUtils.queryNumEntries(bd, "profile_exp");

        ContentValues valuesTit = new ContentValues();
        if(ExistTitu == 0){
            valuesTit.put("id_user",UserID);
        }
        valuesTit.put("titulacion", VarTitulacion.getText().toString());
        valuesTit.put("centro", VarCentro.getText().toString());
        valuesTit.put("mes_fin", VarMesFin.getText().toString());
        valuesTit.put("anyo_fin", VarAnyoFin.getText().toString());

        ContentValues valuesExp = new ContentValues();
        if(ExistExp == 0){
            valuesExp.put("id_user",UserID);
        }
        valuesExp.put("puesto", VarPuesto.getText().toString());
        valuesExp.put("empresa", VarEmpresa.getText().toString());
        valuesExp.put("meses", VarMeses.getText().toString());

        String[] args = new String[]{UserID};
        bd.update("profile",values,"id=?",args);

        if(ExistTitu == 0){
            bd.insert("profile_titu", null, valuesTit);
        } else {
            bd.update("profile_titu",valuesTit,"id_user=?",args);
        }

        if(ExistExp == 0){
            bd.insert("profile_exp", null, valuesExp);
        } else {
            bd.update("profile_exp",valuesExp,"id_user=?",args);
        }

        Toast toast =
                Toast.makeText(getApplicationContext(),
                        "Datos Guardados Correctamente", Toast.LENGTH_SHORT);
        toast.getView().setBackgroundResource(R.color.colorToast);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            if(getArguments()!=null){
                mPosition = getArguments().getInt(ARG_SECTION_NUMBER);
            }

            View FormTab1View = inflater.inflate(R.layout.fragment_profile_tab1, container, false);
            View FormTab2View = inflater.inflate(R.layout.fragment_profile_tab2, container, false);
            View FormTab3View = inflater.inflate(R.layout.fragment_profile_tab3, container, false);

            CreateDatePicker(FormTab1View);
            CreateSpinnerEstudios(FormTab1View);

            switch (mPosition){
                case 1:
                    LoadDataProfileBD(FormTab1View);
                    return FormTab1View;
                case 2:
                    LoadDataProfileTituBD(FormTab2View);
                    return FormTab2View;
                case 3:
                    LoadDataProfileExpBD(FormTab3View);
                    return FormTab3View;
            }
            return null;
        }

        private void LoadDataProfileBD(View formTab1View) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(),null);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String[] args = new String[]{UserID};
            Cursor DataProfile = bd.rawQuery(" SELECT * FROM profile WHERE id=?", args);

            if (DataProfile.moveToFirst()) {
                TextInputEditText Nombre = formTab1View.findViewById(R.id.editTextNombre);
                Nombre.setText(DataProfile.getString(DataProfile.getColumnIndex("nombre")));
                TextInputEditText Apellidos = formTab1View.findViewById(R.id.editTextApellidos);
                Apellidos.setText(DataProfile.getString(DataProfile.getColumnIndex("apellidos")));
                TextInputEditText Dni = formTab1View.findViewById(R.id.editTextDni);
                Dni.setText(DataProfile.getString(DataProfile.getColumnIndex("dni")));
                TextInputEditText FNac = formTab1View.findViewById(R.id.editTextFNac);
                FNac.setText(DataProfile.getString(DataProfile.getColumnIndex("fecha_nacimiento")));
                TextInputEditText TFijo = formTab1View.findViewById(R.id.editTextTFijo);
                TFijo.setText(DataProfile.getString(DataProfile.getColumnIndex("telefono")));
                TextInputEditText Movil = formTab1View.findViewById(R.id.editTextMovil);
                Movil.setText(DataProfile.getString(DataProfile.getColumnIndex("movil")));
                TextInputEditText Direcc = formTab1View.findViewById(R.id.editTextDireccion);
                Direcc.setText(DataProfile.getString(DataProfile.getColumnIndex("direccion")));
                TextInputEditText Email = formTab1View.findViewById(R.id.editTextEmail);
                Email.setText(DataProfile.getString(DataProfile.getColumnIndex("email")));
            }
            DataProfile.close();
        }

        private void LoadDataProfileTituBD(View formTab2View) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(),null);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String[] args = new String[]{UserID};
            Cursor DataProfileTitu = bd.rawQuery(" SELECT * FROM profile_titu WHERE id_user=?", args);

            if (DataProfileTitu.moveToFirst()) {
                TextInputEditText Titulacion = formTab2View.findViewById(R.id.InputEditTextTitu1);
                Titulacion.setText(DataProfileTitu.getString(DataProfileTitu.getColumnIndex("titulacion")));
                TextInputEditText Centro = formTab2View.findViewById(R.id.InputEditTextCentro);
                Centro.setText(DataProfileTitu.getString(DataProfileTitu.getColumnIndex("centro")));
                TextInputEditText MesFin = formTab2View.findViewById(R.id.InputEditTextMesFin);
                MesFin.setText(DataProfileTitu.getString(DataProfileTitu.getColumnIndex("mes_fin")));
                TextInputEditText AnyoFin = formTab2View.findViewById(R.id.InputEditTextAnyoFin);
                AnyoFin.setText(DataProfileTitu.getString(DataProfileTitu.getColumnIndex("anyo_fin")));
            }
            DataProfileTitu.close();
        }

        private void LoadDataProfileExpBD(View formTab3View) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(),null);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String[] args = new String[]{UserID};
            Cursor DataProfileTitu = bd.rawQuery(" SELECT * FROM profile_exp WHERE id_user=?", args);

            if (DataProfileTitu.moveToFirst()) {
                TextInputEditText Puesto = formTab3View.findViewById(R.id.InputEditTExtPuesto);
                Puesto.setText(DataProfileTitu.getString(DataProfileTitu.getColumnIndex("puesto")));
                TextInputEditText Empresa = formTab3View.findViewById(R.id.InputEditTextEmpresa);
                Empresa.setText(DataProfileTitu.getString(DataProfileTitu.getColumnIndex("empresa")));
                TextInputEditText Meses = formTab3View.findViewById(R.id.InputEditTextMesesExp);
                Meses.setText(DataProfileTitu.getString(DataProfileTitu.getColumnIndex("meses")));
            }
            DataProfileTitu.close();
        }

        private void CreateSpinnerEstudios(View rootView) {
            // Get reference of widgets from XML layout
            final Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerEstudios);

            // Initializing a String Array
            String[] Estudios = {"Nivel de Estudios","ESO","Bachiller","CFGM","CFGS","Grado","Licenciatura"};

            final List<String> plantsList = new ArrayList<>(Arrays.asList(Estudios));

            // Initializing an ArrayAdapter
            final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                    getActivity(),R.layout.support_simple_spinner_dropdown_item,Estudios){
                @Override
                public boolean isEnabled(int position){
                    if(position == 0){
                        // Disable the first item from Spinner
                        return false;
                    }
                    else {
                        return true;
                    }
                }

                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position == 0) {
                        // Set the disable item text color
                        tv.setTextColor(Color.GRAY);
                    }
                    else {
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };

            spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerArrayAdapter);
        }

        private void CreateDatePicker(View rootView) {
            final TextInputEditText DateNac = rootView.findViewById(R.id.editTextFNac);
            DateNac.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    Calendar c = Calendar.getInstance();
                    int Dia = c.get(Calendar.DAY_OF_MONTH);
                    int Mes = c.get(Calendar.MONTH);
                    int Ano = c.get(Calendar.YEAR);

                    DatePickerDialog dpt = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            String tDay = String.format("%0" + 2 + "d", day);
                            String tMes = String.format("%0" + 2 + "d", month);

                            DateNac.setText(tDay + "/" + tMes + "/" + year);
                        }
                    }, Ano, Mes, Dia);
                    dpt.show();
                }
            });
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

    }
}
