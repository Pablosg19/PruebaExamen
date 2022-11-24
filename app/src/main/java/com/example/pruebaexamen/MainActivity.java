package com.example.pruebaexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA = "comexamplepruebaexamenmainactivitydatos";
    public static final String EXTRA1 = "comexamplepruebaexamenmainactivitydatos2";
    private EditText edtcantidadEntradas = null;
    private Spinner spZonaEntradas;
    private String zonaSeleccionada =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtcantidadEntradas = (EditText) findViewById(R.id.edt_CantidadEntradas);
        spZonaEntradas = (Spinner) findViewById(R.id.sp_ZonaEntrada);

        if(spZonaEntradas != null){
            String[] zonaEntradas ={"General", "Anfiteatro", "Goles"};
            spZonaEntradas.setOnItemSelectedListener(this);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.estilos_spinner, zonaEntradas);
            spZonaEntradas.setAdapter(adapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
        zonaSeleccionada = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        zonaSeleccionada = "General";
    }

    public void continuar_pagina2(View view) {
        String texto = String.valueOf(edtcantidadEntradas.getText());
        if(texto.isEmpty()){
            edtcantidadEntradas.setError("Debes indicar una cantidad de entradas");
            return;
        }
        else{
            if(Integer.valueOf(texto) > 10 || Integer.valueOf(texto) < 1){
                edtcantidadEntradas.setError("El número de entradas debe de estar comprendido entre 1 y 10 entradas");
                return;
            }
            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra(EXTRA, texto);
            intent.putExtra(EXTRA1, zonaSeleccionada);
            startActivity(intent);
        }
    }
}