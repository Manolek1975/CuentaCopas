package com.copas;

import static java.lang.String.format;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class historicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        getHistoric();

    }

    private void getHistoric() {
        Historico hist = new Historico();
        LinearLayout lin = findViewById(R.id.historicLayout);
        List<Historico> historicoList = hist.getAll(this);
        Bebidas bebida = new Bebidas();

        for (Historico h : historicoList){
            TextView view = new TextView(this);
            view.setTextColor(Color.WHITE);

            String strDate = h.getFecha();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            //Date convertedDate = new Date();
            try {
                Date convertedDate = dateFormat.parse(strDate);
                SimpleDateFormat sdfnewformat = new SimpleDateFormat("dd-MM-yyyy     HH:mm");
                String fecha = sdfnewformat.format(convertedDate);
                bebida = bebida.getBebida(this, h.getDrink());

                view.setText(String.format("%s %10s %s", fecha, " ", bebida.getName()));
            } catch (ParseException e) {
                e.printStackTrace();
            }








            lin.addView(view);
        }


    }
}
