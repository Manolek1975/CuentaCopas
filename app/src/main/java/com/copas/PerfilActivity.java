package com.copas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    private TextView name;
    private TextView edad;
    private TextView peso;
    private TextView altura;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        name = findViewById(R.id.editNombre);
        edad = findViewById(R.id.editEdad);
        peso = findViewById(R.id.editPeso);
        altura = findViewById(R.id.editAltura);
        sex = "M";
    }

    public void setMale(View view){
        ImageButton male = findViewById(R.id.maleBtn);
        ImageButton female = findViewById(R.id.femaleBtn);
        male.setBackgroundResource(R.drawable.roundedbutton);
        female.setBackgroundResource(android.R.color.transparent);
        sex = "M";
    }

    public void setFemale(View view){
        ImageButton female = findViewById(R.id.femaleBtn);
        ImageButton male = findViewById(R.id.maleBtn);
        female.setBackgroundResource(R.drawable.roundedbutton);
        male.setBackgroundResource(android.R.color.transparent);
        sex = "F";
    }


    public void onPause(){
        super.onPause();
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor dataEdit = data.edit();

        String edadString = edad.getText().toString();
        String[] edadSplit = edadString.split(" ");
        String getEdad = edadSplit[0];
        String pesoString = peso.getText().toString();
        String[] pesoSplit = pesoString.split(" ");
        String getPeso = pesoSplit[0];
        String alturaString = altura.getText().toString();
        String[] alturaSplit = alturaString.split(" ");
        String getAltura = alturaSplit[0];

        dataEdit.putString("name", name.getText().toString());
        dataEdit.putString("edad", getEdad);
        dataEdit.putString("peso", getPeso);
        dataEdit.putString("altura", getAltura);
        dataEdit.putString("sex", sex);
        dataEdit.apply();
    }

    public void onResume(){
        super.onResume();
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);
        name.setText(data.getString("name", ""));
        edad.setText(String.format("%s años", data.getString("edad", "")));
        peso.setText(String.format("%s kg", data.getString("peso", "")));
        altura.setText(String.format("%s cm", data.getString("altura", "")));
        sex = data.getString("sex", "");

        if (sex.equals("M")) {
            setMale(null);
        } else {
            setFemale(null);
        }
    }

    public void runGuardar(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void runBorrar(View view){
        name.setText("");
        edad.setText("");
        peso.setText("");
        altura.setText("");
        Intent i = new Intent(this, PerfilActivity.class);
        startActivity(i);
    }


}
