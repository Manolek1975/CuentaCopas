package com.copas;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Bebidas implements IBebidas, Serializable {

    private int id;
    private String name;
    private float tasa;
    private float vol;
    private double precio;
    private String image;


    public Bebidas(int id, String name, float tasa, float vol, double precio, String image) {
        this.id = id;
        this.name = name;
        this.tasa = tasa;
        this.vol = vol;
        this.precio = precio;
        this.image = image;
    }

    public Bebidas(){
        super();
    }

    @Override
    public void LoadBebidasDB(Context context) {
        Resources res = context.getResources();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] name = res.getStringArray(R.array.name_array);
        String[] tasa = res.getStringArray(R.array.tasa_array);
        String[] volumen = res.getStringArray(R.array.volumen_array);
        String[] precio = res.getStringArray(R.array.precio_array);
        String[] image = res.getStringArray(R.array.image_array);

        ContentValues values = new ContentValues();
        for (int i = 0; i < name.length; i++) {
            values.put(DBBebidas.COLUMN_NAME, name[i]);
            values.put(DBBebidas.COLUMN_TASA, tasa[i]);
            values.put(DBBebidas.COLUMN_VOLUMEN, volumen[i]);
            values.put(DBBebidas.COLUMN_PRECIO, precio[i]);
            values.put(DBBebidas.COLUMN_IMAGE, image[i]);

            db.insert(DBBebidas.TABLE_NAME, null, values);
        }
        db.close();
    }

    @Override
    public List<Bebidas> readBebidas(Context context) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM bebidas", null);
        List<Bebidas> bebidasList = new ArrayList<>();
        while (c.moveToNext()) {
            Bebidas bebida = new Bebidas(
                c.getInt(0),
                c.getString(1),
                c.getInt(2),
                c.getInt(3),
                c.getDouble(4),
                c.getString(5)
            );
            bebidasList.add(bebida);
        }
        c.close();
        db.close();

        return bebidasList;
    }

    @Override
    public Bebidas getBebida(Context context) {
        return null;
    }

    @Override
    public void createBebida(Context context, Bebidas bebida) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBBebidas.COLUMN_NAME, bebida.getName());
        values.put(DBBebidas.COLUMN_TASA, bebida.getTasa());
        values.put(DBBebidas.COLUMN_VOLUMEN, bebida.getVol());
        values.put(DBBebidas.COLUMN_PRECIO, bebida.getPrecio());
        values.put(DBBebidas.COLUMN_IMAGE, bebida.getImage());
        db.insert(DBBebidas.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void updateBebida(Context context, Bebidas bebida) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBBebidas.COLUMN_NAME, bebida.getName());
        values.put(DBBebidas.COLUMN_TASA, bebida.getTasa());
        values.put(DBBebidas.COLUMN_VOLUMEN, bebida.getVol());
        values.put(DBBebidas.COLUMN_PRECIO, bebida.getPrecio());

        db.update("bebidas", values,"id=" + bebida.getId(), null);
        db.close();
    }

    @Override
    public void deleteBebida(Context context, int id) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("bebidas", "id=?", new String[] {String.valueOf(id)} );
        db.close();
    }

    protected void deleteDB(Context context){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from "+ DBBebidas.TABLE_NAME);
        db.close();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public float getTasa() { return tasa; }

    public float getVol() { return vol; }

    public double getPrecio() { return precio; }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasa(float tasa) {
        this.tasa = tasa;
    }

    public void setVol(float vol) {
        this.vol = vol;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
