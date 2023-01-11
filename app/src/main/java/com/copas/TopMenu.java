package com.copas;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TopMenu extends AppCompatActivity {

    private final Context context;

    public TopMenu(Context context){
        this.context = context;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem option) {
        int id = option.getItemId();
        if (id == R.id.menu_perfil || id == R.id.icon_menu_perfil){
            runPerfil();
            return true;
        }
        if (id == R.id.menu_bebidas || id == R.id.icon_menu_bebidas){
            runBebidas();
            return true;
        }
        if (id == R.id.menu_historico || id == R.id.icon_menu_historico){
            runHistorico();
            return true;
        }
        if (id == R.id.menu_info){
            runInfo();
            return true;
        }
        return super.onOptionsItemSelected(option);
    }

    private void runInfo() {
        Intent i =  new Intent(context, Info.class);
        context.startActivity(i);
    }

    private void runHistorico() {
        Intent i =  new Intent(context, historicActivity.class);
        context.startActivity(i);
    }

    private void runBebidas() {
        Intent i =  new Intent(context, BebidasActivity.class);
        context.startActivity(i);
    }

    private void runPerfil() {
        Intent i =  new Intent(context, PerfilActivity.class);
        context.startActivity(i);
    }


}
