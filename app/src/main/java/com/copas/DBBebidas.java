package com.copas;

public class DBBebidas {

    private DBBebidas() {}

    public static final String TABLE_NAME = "bebidas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TASA  = "tasa";
    public static final String COLUMN_VOLUMEN  = "volumen";
    public static final String COLUMN_PRECIO  = "precio";
    public static final String COLUMN_IMAGE  = "image";


    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBBebidas.TABLE_NAME + " (" +
                    DBBebidas.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    DBBebidas.COLUMN_NAME + " INTEGER," +
                    DBBebidas.COLUMN_TASA + " INTEGER," +
                    DBBebidas.COLUMN_VOLUMEN + " INTEGER," +
                    DBBebidas.COLUMN_PRECIO + " REAL," +
                    DBBebidas.COLUMN_IMAGE + " INTEGER)";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBBebidas.TABLE_NAME;
}
