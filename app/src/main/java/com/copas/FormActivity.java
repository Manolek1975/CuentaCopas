package com.copas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    private String imageName = "cerveza";
    private Bebidas bebida;
    private EditText name;
    private EditText tasa;
    private EditText volumen;
    private EditText precio;
    private ImageView image;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Button btnSave = findViewById(R.id.btnGuardar);
        ImageButton papelera = findViewById(R.id.btnPapelera);
        name = findViewById(R.id.nombre);
        tasa = findViewById(R.id.tasa);
        volumen = findViewById(R.id.volumen);
        precio = findViewById(R.id.precio);
        image = findViewById(R.id.imageViewBebida);

        Intent i = getIntent();
        //Bundle crud = i.getExtras();
        bebida = (Bebidas) i.getSerializableExtra("bebida");

        if (bebida == null){
            this.setTitle("Crear Bebida");
            btnSave.setText("Crear");
            image.setImageResource(R.drawable.cerveza);
            papelera.setVisibility(View.INVISIBLE);
        } else {
            this.setTitle("Modificar Bebida");
            btnSave.setText("Modificar");
            name.setText(bebida.getName());
            tasa.setText(Float.toString(bebida.getTasa()));
            volumen.setText(Float.toString(bebida.getVol()));
            precio.setText(Double.toString(bebida.getPrecio() / 100));
            image.setImageResource(this.getResources().getIdentifier(bebida.getImage(), "drawable", getPackageName()));
        }


        btnSave.setOnClickListener(v -> {
            if (bebida == null){
                crearBebida();
            } else {
                updateBebida();
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
        bebida.deleteBebida(this, bebida.getId());
        runBebidas();
    }

    public void onRadioButtonClicked(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();
        // Check which radio button was clicked
        switch(v.getId()) {
            case (R.id.radioButtonCerveza):
                    imageName = "cerveza";
                    image.setImageResource(R.drawable.cerveza);
                break;
            case (R.id.radioButtonVino):
                    imageName =  "vino";
                    image.setImageResource(R.drawable.vino);
                break;
            case (R.id.radioButtonVermu):
                    imageName =  "vermu";
                    image.setImageResource(R.drawable.vermut);
                break;
            case (R.id.radioButtonLicor):
                    imageName =  "licor";
                    image.setImageResource(R.drawable.licor);
                break;
            case (R.id.radioButtonBrandy):
                    imageName =  "brandy";
                    image.setImageResource(R.drawable.brandy);
                break;
            case (R.id.radioButtonCombinado):
                    imageName =  "combinado";
                    image.setImageResource(R.drawable.combinado);
                break;
        }
    }

    public void runBebidas(){
        Intent intent = new Intent(FormActivity.this, BebidasActivity.class);
        startActivity(intent);
    }

    public void runCancelar(View v){
        Intent intent = new Intent(FormActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
