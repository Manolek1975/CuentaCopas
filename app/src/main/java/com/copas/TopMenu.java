package com.copas;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class TopMenu extends AppCompatActivity {

    private Context context;

    public TopMenu(Context context){
        this.context = context;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem option) {
        int id = option.getItemId();
        if (id == R.id.menu_perfil || id == R.id.icon_menu_perfil){
            runPerfil(null);
            return true;
        }
        if (id == R.id.menu_bebidas || id == R.id.icon_menu_bebidas){
            runBebidas(null);
            return true;
        }
        return super.onOptionsItemSelected(option);
    }

    private void runPerfil(Object o) {
        Intent i =  new Intent(context, PerfilActivity.class);
        context.startActivity(i);
    }

    private void runBebidas(Object o) {
        Intent i =  new Intent(context, BebidasActivity.class);
        context.startActivity(i);
    }
}
