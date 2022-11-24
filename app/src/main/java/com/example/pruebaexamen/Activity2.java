package com.example.pruebaexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Activity2 extends AppCompatActivity {

    DecimalFormat df = new DecimalFormat("#.00");

    private TextView txtcantidadEntradas;
    private TextView txtprecioEntradas;
    private TextView txtzonaEntradas;
    private TextView txtprecioDescuento;
    private TextView txtprecioTotal;
    private TextView txtprecio;
    private RadioButton si;
    private RadioButton no;
    private Intent intent;

    private double descuento = 0.1;
    private double precioEntrada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina2);

        intent = getIntent();
        if(intent != null){
            txtcantidadEntradas = (TextView) findViewById(R.id.txt_NumCantidad);
            txtprecioEntradas = (TextView) findViewById(R.id.txtPrecioZona);
            txtzonaEntradas = (TextView) findViewById(R.id.txtZonaEntrada);
            txtprecioDescuento = (TextView) findViewById(R.id.txt_DineroDescuento);
            txtprecioTotal = (TextView) findViewById(R.id.txtPrecioTotal);
            txtprecio = (TextView) findViewById(R.id.txtPrecio);
            si = (RadioButton) findViewById(R.id.rb_Si);
            no = (RadioButton) findViewById(R.id.rb_No);

            String zona = intent.getStringExtra(MainActivity.EXTRA1);
            switch (zona){
                case "General":
                    precioEntrada = 5.00;
                    txtzonaEntradas.setText("General");
                    break;
                case "Anfiteatro":
                    precioEntrada = 4.00;
                    txtzonaEntradas.setText("Anfiteatro");
                    break;
                case "Goles":
                    precioEntrada = 3.00;
                    txtzonaEntradas.setText("Goles");
            }
            buttonSocio();
        }
    }



    private void buttonSocio(){
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtprecioEntradas.setText(precioEntrada + "€");
                txtcantidadEntradas.setText(intent.getStringExtra(MainActivity.EXTRA));
                txtprecio.setText(String.valueOf(precioEntrada * Double.valueOf(String.valueOf(txtcantidadEntradas.getText()))));
                txtprecioDescuento.setText(String.valueOf(descuento*Double.valueOf(String.valueOf(txtprecio.getText()))));
                Double precioTotal = Double.valueOf(String.valueOf(txtprecio.getText())) - Double.valueOf(String.valueOf(txtprecioDescuento.getText()));
                txtprecioTotal.setText(String.valueOf(precioTotal));
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtprecioEntradas.setText(String.valueOf(precioEntrada) + "€");
                txtcantidadEntradas.setText(intent.getStringExtra(MainActivity.EXTRA));
                txtprecio.setText(String.valueOf(precioEntrada * Double.valueOf(String.valueOf(txtcantidadEntradas.getText()))));
                txtprecioDescuento.setText("0€");
                txtprecioTotal.setText(txtprecio.getText());
            }
        });
    }
}