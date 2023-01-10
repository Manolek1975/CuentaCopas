package com.copas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    private String imageName = "cerveza";
    private Bebidas bebida;
    private EditText name;
    private EditText tasa;
    private EditText volumen;
    private EditText precio;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Button btnSave = findViewById(R.id.btnGuardar);
        name = findViewById(R.id.nombre);
        tasa = findViewById(R.id.tasa);
        volumen = findViewById(R.id.volumen);
        precio = findViewById(R.id.precio);

        Intent i = getIntent();
        Bundle crud = i.getExtras();
        bebida = (Bebidas) i.getSerializableExtra("bebida");

        if (bebida == null){
            this.setTitle("Crear Bebida");
            btnSave.setText("Crear");
        } else {
            this.setTitle("Modificar Bebida");
            btnSave.setText("Modificar");
            name.setText(bebida.getName());
            tasa.setText(Float.toString(bebida.getTasa()));
            volumen.setText(Float.toString(bebida.getVol()));
            precio.setText(Double.toString(bebida.getPrecio() / 100));
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bebida == null){
                    crearBebida();
                } else {
                    updateBebida();
                }
            }
        });

    }

    public void crearBebida(){
        Bebidas bebida = new Bebidas();
        bebida.setName(String.valueOf(name.getText()));
        bebida.setTasa(Float.parseFloat(tasa.getText().toString()));
        bebida.setVol(Float.parseFloat(volumen.getText().toString()));
        bebida.setPrecio(Double.parseDouble(precio.getText().toString())*100);
        bebida.setImage(imageName);

        bebida.createBebida(this, bebida);
        runBebidas();

    }

    private void updateBebida() {
        bebida.setName(String.valueOf(name.getText()));
        bebida.setTasa(Float.parseFloat(tasa.getText().toString()));
        bebida.setVol(Float.parseFloat(volumen.getText().toString()));
        bebida.setPrecio(Double.parseDouble(precio.getText().toString())*100);
        bebida.setImage(imageName);
        bebida.updateBebida(this, bebida);
        runBebidas();
    }

    public void deleteBebida(View view){
        //Bebidas bebida = new Bebidas();
        bebida.deleteBebida(this, bebida.getId());
        runBebidas();
    }

    public void onRadioButtonClicked(View view){

    }

    public void runBebidas(){
        Intent intent = new Intent(FormActivity.this, BebidasActivity.class);
        startActivity(intent);
    }

}
