package com.copas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    private Bebidas bebida;
    private EditText name;
    private EditText tasa;
    private EditText volumen;
    private EditText precio;
    private ImageView image;
    private TextView imageName;


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
        imageName = findViewById(R.id.imageName);

        Intent i = getIntent();
        //Bundle crud = i.getExtras();
        bebida = (Bebidas) i.getSerializableExtra("bebida");

        if (bebida == null){
            this.setTitle(R.string.menu_crearbebida);
            btnSave.setText(R.string.crear);
            papelera.setVisibility(View.INVISIBLE);
        } else {
            this.setTitle(R.string.menu_modificarbebida);
            btnSave.setText(R.string.modificar);
            name.setText(bebida.getName());
            tasa.setText(Float.toString(bebida.getTasa()));
            volumen.setText(Float.toString(bebida.getVol()));
            precio.setText(Double.toString(bebida.getPrecio() / 100));
            image.setImageResource(this.getResources().getIdentifier(bebida.getImage(), "drawable", getPackageName()));
            imageName.setText(bebida.getImage());
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
        if (TextUtils.isEmpty(imageName.getText())){
            Toast.makeText(getApplicationContext(),"Debe seleccionar una bebida",Toast.LENGTH_LONG).show();
            return;
        }
        Bebidas bebida = new Bebidas();
        bebida.setName(String.valueOf(name.getText()));
        bebida.setTasa(Float.parseFloat(tasa.getText().toString()));
        bebida.setVol(Float.parseFloat(volumen.getText().toString()));
        bebida.setPrecio(Double.parseDouble(precio.getText().toString())*100);
        bebida.setImage(String.valueOf(imageName.getText()));

        bebida.createBebida(this, bebida);
        runBebidas();
    }

    private void updateBebida() {
        bebida.setName(String.valueOf(name.getText()));
        bebida.setTasa(Float.parseFloat(tasa.getText().toString()));
        bebida.setVol(Float.parseFloat(volumen.getText().toString()));
        bebida.setPrecio(Double.parseDouble(precio.getText().toString())*100);
        bebida.setImage(String.valueOf(imageName.getText()));

        bebida.updateBebida(this, bebida);
        runBebidas();
    }

    public void deleteBebida(View view){
        int res = getResources().getIdentifier(bebida.getImage(), "drawable", this.getPackageName());

        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                .setIcon(res)
                .setTitle(bebida.getName())
                .setMessage(R.string.dialog_message_eliminar)
                .setPositiveButton("SI", (dialogInterface, i) -> {
                    bebida.deleteBebida(FormActivity.this, bebida.getId());
                    runBebidas();
                })
                .setNegativeButton("NO", (dialogInterface, i) -> {
                    //Toast.makeText(getApplicationContext(),"Acción cancelada",Toast.LENGTH_LONG).show();
                })
                .show();
    }

    public void onRadioButtonClicked(View v) {
        // Is the button now checked?

        // Check which radio button was clicked
        switch(v.getId()) {
            case (R.id.radioButtonCerveza):
                imageName.setText(R.string.cerveza);
                image.setImageResource(R.drawable.cerveza);
                break;
            case (R.id.radioButtonVino):
                imageName.setText(R.string.vino);
                image.setImageResource(R.drawable.vino);
                break;
            case (R.id.radioButtonVermu):
                imageName.setText(R.string.vermut);
                image.setImageResource(R.drawable.vermut);
                break;
            case (R.id.radioButtonLicor):
                imageName.setText(R.string.licor);
                image.setImageResource(R.drawable.licor);
                break;
            case (R.id.radioButtonBrandy):
                imageName.setText(R.string.brandy);
                image.setImageResource(R.drawable.brandy);
                break;
            case (R.id.radioButtonCombinado):
                imageName.setText(R.string.combinado);
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

    public void runForm(View view){
        Intent intent = new Intent(FormActivity.this, FormActivity.class);
        startActivity(intent);
    }

}
