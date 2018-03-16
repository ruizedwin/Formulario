package com.example.pcfamily.formulario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Calendar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView tVisualizar;
    EditText eIngresar, ePassword, erepPassword, eCorreo, eFecha;
    CheckBox cGimnasio, cNadar, cCine, cLectura;
    Spinner seleccionar;
    int anio, mes, dia;
    static final int DIALOGO=0;
    static DatePickerDialog.OnDateSetListener selFecha;
    String ingresar, password, reppassword, correo, sexo, fecha, lugar, gimnasio="", nadar="", cine="", lectura="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar fechaActual = Calendar.getInstance();

        dia = fechaActual.get(Calendar.DAY_OF_MONTH);
        mes = fechaActual.get(Calendar.MONTH);
        anio = fechaActual.get(Calendar.YEAR);

        seleccionar = findViewById(R.id.sLugar);
        eIngresar = findViewById(R.id.eIngresar);
        ePassword = findViewById(R.id.ePassword);
        erepPassword = findViewById(R.id.erepPassword);
        eCorreo = findViewById(R.id.eCorreo);
        eFecha = findViewById(R.id.eFecha);
        tVisualizar = findViewById(R.id.tVisualizar);
        cGimnasio = findViewById(R.id.cGimnasio);
        cNadar = findViewById(R.id.cNadar);
        cCine = findViewById(R.id.cCine);
        cLectura = findViewById(R.id.cLectura);

        selFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                dia = dayOfMonth;
                mes= monthOfYear;
                anio = year;
                eFecha.setText(dia+"/"+(mes +1)+"/"+anio);
            }
        };

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.seleccionar,android.R.layout.simple_spinner_item);
        seleccionar.setAdapter(adapter);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id){
            case 0 :
                return new DatePickerDialog(this,selFecha, anio, mes, dia);
        }
        return null;
    }


    public void onCheckBoxClicked(View view) {
        int id = view.getId();
        switch (id){
            case R.id.cGimnasio:
                if(cGimnasio.isChecked()) {
                    gimnasio ="Gimnasio";
                }else{
                    gimnasio ="";
                }
                break;
            case R.id.cNadar:
                if(cNadar.isChecked()) {
                    nadar ="Nadar";
                }else{
                    nadar = "";
                }
                break;
            case R.id.cCine:
                if(cCine.isChecked()) {
                    cine ="Cine";
                }else{
                    cine = "";
                }
                break;
            case R.id.cLectura:
                if(cLectura.isChecked()) {
                    lectura = "Lectura";
                }else{
                    lectura = "";
                }
                break;

        }
    }

    public void onRadioButtonClicked(View view) {
        int id = view.getId();

        if ( id== R.id.rMasculino){

            sexo = "Masculino";
        }else{
            sexo = "Femenino";
        }
    }

    public void onEditTextClicked(View view) {
        showDialog(DIALOGO);
    }

    public void onButtonClicked(View view) {
        ingresar= eIngresar.getText().toString();
        password= ePassword.getText().toString();
        reppassword= erepPassword.getText().toString();
        correo= eCorreo.getText().toString();
        fecha= eFecha.getText().toString();
        lugar =seleccionar.getSelectedItem().toString();

        if (ingresar.equals("") || password.equals("")|| reppassword.equals("")|| correo.equals("")|| fecha.equals("")|| sexo.equals("")|| lugar.equals("")|| (!(cGimnasio.isChecked())&&!(cNadar.isChecked())&&!(cCine.isChecked())&&!(cLectura.isChecked()) )) {
            Toast.makeText(this, "Debe diligenciar todos los campos", Toast.LENGTH_LONG).show();

        }else if(!(password.equals(reppassword))){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
        }else{
            tVisualizar.setText(ingresar + "  " + password + "  " + reppassword + "  " + correo + "  " + sexo + "  " + fecha + "  " + lugar + "  " + gimnasio + "  " + nadar+ "  " + cine+ "  " + lectura);

        }
    }
}
