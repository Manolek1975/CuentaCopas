package com.copas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;

public class BebidasActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas);

        getButtons();

    }

    private void getButtons() {
        LinearLayout lin = findViewById(R.id.bebidasLayout);
        Bebidas bebidas = new Bebidas();
        Historico historico = new Historico();
        List<Bebidas> bebidasList = bebidas.readBebidas(this);

        for (Bebidas bebida : bebidasList){
            int resImage = this.getResources().getIdentifier(bebida.getImage(), "drawable", getPackageName());
            Button button = new Button(this);
            button.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
            button.setCompoundDrawablesWithIntrinsicBounds(resImage, 0, 0, 0);
            button.setBackgroundColor(Color.TRANSPARENT);
            button.setCompoundDrawablePadding(50);
            button.setText(bebida.getName());
            //button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            button.setTextColor(Color.WHITE);
            button.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("crud", bebida);
                Intent i = new Intent(BebidasActivity.this, FormActivity.class);
/*                i.putExtra("nombre", bebida.getName());
                i.putExtra("tasa", bebida.getTasa());
                i.putExtra("volumen", bebida.getVol());
                i.putExtra("precio", bebida.getPrecio());
                i.putExtra("imagen", bebida.getImage());*/
                i.putExtra("bebida", bebida);
                i.putExtras(bundle);

                //historico.setHistorico(this, bebida.getId());
                startActivity(new Intent(i));
            });
            button.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            lin.addView(button);
        }
    }

    public void runForm(View view){
        Intent intent = new Intent(BebidasActivity.this, FormActivity.class);
        startActivity(intent);
    }

}
