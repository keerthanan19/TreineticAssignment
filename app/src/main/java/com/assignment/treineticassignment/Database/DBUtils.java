package com.assignment.treineticassignment.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.assignment.treineticassignment.Object.Data;

import java.util.ArrayList;

public class DBUtils {
    private static db_manager db_manager;
    private static final String TAG = "DBUtils";

    public static boolean insertData(Data data, Context mContext){
        boolean isSuccess = false;
        db_manager = new db_manager(mContext);
        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {

            ContentValues cv = new ContentValues();
            cv.put(db_tables.DATA_ID, data.getId());
            cv.put(db_tables.DATA_TITLE, data.getTitle());
            cv.put(db_tables.DATA_PRICE, data.getPrice());
            cv.put(db_tables.DATA_RATING, data.getRating());
            cv.put(db_tables.DATA_DESCRIPTION, data.getDescription());
            cv.put(db_tables.DATA_IMAGES, data.getImages());

            long rowCount = db.insertOrThrow(db_tables.TABLE_NAME_DATA, null, cv);
            if (rowCount != -1){
                isSuccess = true;
            }else{
                Log.e(TAG, "insertData " + " insert failed");
                isSuccess = false;
            }
        } catch (Exception e) {
            Log.e(TAG, "insertData " + " Exception : " + e.getMessage());
            isSuccess = false;
        }
        return isSuccess;
    }

    @SuppressLint("Range")
    public static ArrayList<Data> getAllData(Context mContext) {
        ArrayList<Data>  dataArrayList = new ArrayList<>();
        db_manager = new db_manager(mContext);
        Cursor c;
        String qty = "0";

        StringBuilder query = new StringBuilder("SELECT * FROM " + db_tables.TABLE_NAME_DATA
        );

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);
            if (c.moveToFirst()) {
                do {
                    Data data = new Data();

                    data.setId(c.getString(c.getColumnIndex(db_tables.DATA_ID)));
                    data.setTitle(c.getString(c.getColumnIndex(db_tables.DATA_TITLE)));
                    data.setPrice(c.getString(c.getColumnIndex(db_tables.DATA_PRICE)));
                    data.setRating(c.getFloat(c.getColumnIndex(db_tables.DATA_RATING)));
                    data.setDescription(c.getString(c.getColumnIndex(db_tables.DATA_DESCRIPTION)));
                    data.setImages(c.getString(c.getColumnIndex(db_tables.DATA_IMAGES)));

                    dataArrayList.add(data);
                } while (c.moveToNext());
            }
            return dataArrayList;
        } catch (Exception e) {
            Log.e(TAG, "getAllData " + e);
        }
        return dataArrayList;
    }

    public static boolean updateData(Data data, Context mContext) {
        boolean isSuccess = false;
        db_manager = new db_manager(mContext);
        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            ContentValues cv = new ContentValues();
            cv.put(db_tables.DATA_TITLE, data.getTitle());
            cv.put(db_tables.DATA_PRICE, data.getPrice());
            cv.put(db_tables.DATA_RATING, data.getRating());
            cv.put(db_tables.DATA_DESCRIPTION, data.getDescription());
            cv.put(db_tables.DATA_IMAGES, data.getImages());

            int row = db.update(db_tables.TABLE_NAME_DATA, cv, db_tables.DATA_ID + " = ?", new String[]{data.getId()});
            if (row > 0) {
                Log.d(TAG, "updateData " + " updated " + data.getId());
                isSuccess = true;
            } else {
                Log.e(TAG, "updateData " + " update failed");
                isSuccess = false;
            }
        } catch (Exception e) {
            Log.e(TAG, "updateData " + " update failed :" + e.getMessage());
            isSuccess = false;
        }
        return isSuccess;
    }

    @SuppressLint("Range")
    public static Data getDataByID(Context mContext, String userID) {
        db_manager = new db_manager(mContext);
        Cursor c;
        String qty = "0";
        Data data = new Data();

        StringBuilder query = new StringBuilder("SELECT * FROM " + db_tables.TABLE_NAME_DATA
                + " where " + db_tables.DATA_ID + "='" + userID + "'"
        );

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);
            if (c.moveToFirst()) {
                do {

                    data.setId(c.getString(c.getColumnIndex(db_tables.DATA_ID)));
                    data.setTitle(c.getString(c.getColumnIndex(db_tables.DATA_TITLE)));
                    data.setPrice(c.getString(c.getColumnIndex(db_tables.DATA_PRICE)));
                    data.setRating(c.getFloat(c.getColumnIndex(db_tables.DATA_RATING)));
                    data.setDescription(c.getString(c.getColumnIndex(db_tables.DATA_DESCRIPTION)));
                    data.setImages(c.getString(c.getColumnIndex(db_tables.DATA_IMAGES)));

                } while (c.moveToNext());
            }
            return data;
        } catch (Exception e) {
            Log.e(TAG, "getAllData " + e);
        }
        return data;
    }
    public static void deleteAllData(Context context) {
        db_manager = new db_manager(context);
        SQLiteDatabase db = db_manager.getReadableDatabase();
        db.execSQL("delete from " + db_tables.TABLE_NAME_DATA);
    }


    public static boolean deleteDataById(String id, Context mContext) {
        db_manager = new db_manager(mContext);
        SQLiteDatabase db = db_manager.getWritableDatabase();

        String selection = db_tables.DATA_ID + " = ?";
        String[] selectionArgs = { id };

        int deletedRows = db.delete(db_tables.TABLE_NAME_DATA, selection, selectionArgs);
        db.close();

        return deletedRows > 0;
    }

}
