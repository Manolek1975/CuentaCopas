package com.copas;

import static java.lang.String.valueOf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Historico historico;
    private SeekBar seekbar;
    private int lapso = 6;
    private double tasaTotal, precioTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = findViewById(R.id.seekBar);
        seekbar.setMin(6);
        TextView lapsoText = findViewById(R.id.lapsoTiempo);

        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);
        lapso = data.getInt("lapso", 0);
        tasaTotal = 0.00;
        precioTotal = 0.00;
        historico = new Historico();

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lapsoText.setText(String.format("Lapso: %sh", progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        showBebidas();

    }

    private void setTasa(Bebidas bebida) {
        //T= (grado alcohol / 100) x Volumen ingerido (en ml) x 0,8 (0,8 es la densidad del alcohol puro)
        //graduacion = graduacion / 100; // Porcentaje
        //volumen = volumen * 10; // En mililitros
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);
        int peso = Integer.parseInt(data.getString("peso", ""));
        int altura = Integer.parseInt(data.getString("altura", ""));
        TextView tasaText = findViewById(R.id.tasaTotal);
        TextView precioText = findViewById(R.id.precioTotal);
        double alcoholPuroIngerido = (bebida.getTasa() * bebida.getVol() * 0.8) / 10;
        double h= (double) altura / 100;
        Double masa = peso / Math.pow(h, 2);
        double tasa = alcoholPuroIngerido / (peso * 0.7);
        tasaTotal = tasaTotal + tasa;
        precioTotal = precioTotal + bebida.getPrecio() / 100;
        tasaText.setText(String.format("%.2f", tasaTotal));
        precioText.setText(String.format("%s€", String.format("%.2f", precioTotal)));
        setTasaColor(tasaTotal);
        //Log.i("LIST calculoTasa: ", "tasa:" + tasa + " masa:" + masa + " alcoholpuroingerido:" + alcoholPuroIngerido);

    }

    private void setTasaColor(Double tasa) {
        TextView tasaTotal = findViewById(R.id.tasaTotal);
        TextView gl = findViewById(R.id.gl);
        if(tasa <= 0.2){
            tasaTotal.setTextColor(Color.GREEN);
            gl.setTextColor(Color.GREEN);
        }
        if(tasa > 0.2 && tasa < 0.5){
            tasaTotal.setTextColor(Color.YELLOW);
            gl.setTextColor(Color.YELLOW);
        }
        if(tasa >= 0.5){
            tasaTotal.setTextColor(Color.RED);
            gl.setTextColor(Color.RED);
        }
    }

    private void showBebidas() {
        LinearLayout scroll = findViewById(R.id.mainButtonsLayout);
        List<Bebidas> bebidasList = historico.getHistorico(this, lapso);

        for (Bebidas bebida : bebidasList){
            int resImage = this.getResources().getIdentifier(bebida.getImage(), "drawable", this.getPackageName());
            Button button = new Button(this);
            button.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
            button.setCompoundDrawablesWithIntrinsicBounds(resImage, 0, 0, 0);
            button.setBackgroundColor(Color.TRANSPARENT);
            button.setCompoundDrawablePadding(50);
            button.setText(bebida.getName());
            button.setTextColor(Color.WHITE);

            button.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            scroll.addView(button);
            setTasa(bebida);
        }
    }

    public void onPause(){
        super.onPause();
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor dataEdit = data.edit();
        lapso = seekbar.getProgress();
        dataEdit.putInt("lapso", lapso);
        dataEdit.apply();
        Log.i("Lapso Pause", lapso  + "");

    }
    public void onResume(){
        super.onResume();
        Bebidas bebidas = new Bebidas();
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);

        String name = data.getString("name", "");
        lapso = data.getInt("lapso", 0);

        if(name.isEmpty()){
            bebidas.deleteDB(this);
            bebidas.LoadBebidasDB(this);
            runPerfil();
        }

        TextView namePerfil = findViewById(R.id.namePerfil);
        namePerfil.setText(name);
        seekbar.setProgress(lapso);
        Log.i("name", name + "");
        Log.i("Lapso Resume", lapso  + "");

    }

     private void runPerfil() {
        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                .setIcon(android.R.drawable.ic_menu_my_calendar)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setPositiveButton("CREAR PERFIL", (dialogInterface, i) -> {
                    Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
                    startActivity(intent);

                })
                .show();
    }

    public void addBebida(View view){
        Intent intent = new Intent(MainActivity.this, ConsumoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem option){
        TopMenu menu = new TopMenu(this);
        menu.onOptionsItemSelected(option);
        return false;
    }
}