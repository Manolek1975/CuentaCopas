package com.copas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }

    public void runInfo(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                .setIcon(R.drawable.icon)
                .setTitle(R.string.app_name)
                .setMessage(R.string.info)
                .setPositiveButton("VOLVER", (dialogInterface, i) -> {

                })
                .setNegativeButton("CERRAR", (dialogInterface, i) -> {
                    Intent intent = new Intent(Help.this, MainActivity.class);
                    startActivity(intent);

                })
                .show();
        alertDialog.create();
    }


}
