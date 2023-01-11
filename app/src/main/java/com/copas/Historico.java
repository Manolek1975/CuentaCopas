package com.copas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class Historico {

    //public static final int ONE_DAY = 86400000; //24h  *  60m  *  60s  *  1000ms   =  86400000
    private Date date;
    private String fecha;
    private int drink;

    public Historico(){
        super();
    }

    public Historico(int drink) {
        this.drink = drink;
    }

    public Historico(String fecha, int drink) {
        this.fecha = fecha;
        this.drink = drink;
    }

    public void setHistorico(Context context, int id){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        // Formatear fecha
        date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        String formattedDate = df.format(date);
        SimpleDateFormat hf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedHour = hf.format(date);

        // Comparar fecha
        Calendar validDate = new GregorianCalendar();
        validDate.setTime(date);

        ContentValues values = new ContentValues();
            values.put(DBHistorico.COLUMN_DATE, formattedDate);
            values.put(DBHistorico.COLUMN_HOUR, formattedHour);
            values.put(DBHistorico.COLUMN_TERM, 0);
            values.put(DBHistorico.COLUMN_DRINK, id);

        db.insert(DBHistorico.TABLE_NAME, null, values);
        db.close();
    }

    public List<Bebidas> getHistorico(Context context, int lapso){
        Date dateNow = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow); // Configuramos la fecha que se recibe
        calendar.add(Calendar.HOUR, - lapso);  // numero de horas a añadir, o restar en caso de horas<0
        date = calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas

        SimpleDateFormat standar = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = standar.format(dateNow);
        String dateDrink = standar.format( date );
        Log.i("date", "Fecha: " + date + " - " + dateNow);

        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM historico WHERE date BETWEEN '" + dateDrink + "' AND '" + now + "'", null);
        if (c != null){
            Log.i("query", "SELECT * FROM historico WHERE date >= '"+dateDrink+"' AND date <= '"+now+"'");
        }
        List<Historico> list = new ArrayList<>();
        while (c.moveToNext()) {
            Historico bebida = new Historico(
                    c.getInt(4)
            );
            list.add(bebida);
        }
        c.close();

        List<Bebidas> bebidas = new ArrayList<>();
        for (Historico val : list) {
            Cursor c2 = db.rawQuery("SELECT * FROM bebidas WHERE id =" + val.drink, null);
            while (c2.moveToNext()) {
                Bebidas bebida = new Bebidas(
                        c2.getInt(0),
                        c2.getString(1),
                        c2.getInt(2),
                        c2.getInt(3),
                        c2.getInt(4),
                        c2.getString(5)
                );
                bebidas.add(bebida);
            }
            Log.i("historico", val.drink + "");
            c2.close();
        }

        db.close();
        return bebidas;
    }

    public List<Historico> getAll(Context context) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        List<Historico> historico = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM historico", null);
        while (c.moveToNext()) {
            Historico h = new Historico(
                    c.getString(1),
                    c.getInt(4)
            );
            historico.add(h);
        }
        c.close();
        db.close();
        return historico;
    }

    public String getFecha() {
        return fecha;
    }

    public int getDrink() {
        return drink;
    }
}
