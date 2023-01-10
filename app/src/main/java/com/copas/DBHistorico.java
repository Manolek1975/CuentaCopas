package com.copas;

public class DBHistorico {

    private DBHistorico() {}

    public static final String TABLE_NAME = "historico";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_HOUR  = "hour";
    public static final String COLUMN_TERM  = "term";
    public static final String COLUMN_DRINK  = "id_drink";


    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBHistorico.TABLE_NAME + " (" +
                    DBHistorico.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    DBHistorico.COLUMN_DATE + " INTEGER," +
                    DBHistorico.COLUMN_HOUR + " INTEGER," +
                    DBHistorico.COLUMN_TERM + " INTEGER," +
                    DBHistorico.COLUMN_DRINK + " INTEGER)";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBHistorico.TABLE_NAME;
}
