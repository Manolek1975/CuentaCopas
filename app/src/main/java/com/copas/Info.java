package com.copas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Info extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                .setIcon(R.drawable.icon)
                .setTitle("Cuenta Copas")
                .setMessage("Cálculo aproximado de la tasa de alcoholemia. Control de gasto\n\n" +
                        "Buzón de sugerencias: cuentacopas2023@gmail.com\n\n" +
                        "Versión 1.0\n" +
                        "Manuel Domínguez 2023\n")
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    Intent intent = new Intent(Info.this, MainActivity.class);
                    startActivity(intent);

                })
                .show();
    }
}
