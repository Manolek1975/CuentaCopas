package com.copas;

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
                .setTitle(R.string.app_name)
                .setMessage(R.string.info)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    Intent intent = new Intent(Info.this, MainActivity.class);
                    startActivity(intent);

                })
                .show();
        alertDialog.create();
    }
}
