package com.assignment.treineticassignment.Database;

public class db_tables {

    static final String TABLE_NAME_DATA = "Data";

    public static final String DATA_ID = "_id";
    public static final String DATA_TITLE = "title";
    public static final String DATA_PRICE = "price";
    public static final String DATA_RATING = "rating";
    public static final String DATA_DESCRIPTION = "description";
    public static final String DATA_IMAGES = "images";

    static final String CREATE_TABLE_DATA = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_DATA
            + " ( " +
            DATA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DATA_TITLE + " TEXT, " +
            DATA_PRICE + " TEXT, " +
            DATA_RATING + " REAL, " +
            DATA_DESCRIPTION + " TEXT, " +
            DATA_IMAGES + " TEXT " +
            ");";

}
