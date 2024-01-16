package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Bdplants;
import Model.Bdrecords;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VRSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PLANTS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ("
                + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + " TEXT, "
                + Util.KEY_IMAGE + " TEXT " + " )";
        sqLiteDatabase.execSQL(CREATE_PLANTS_TABLE);


        String CREATE_RECORD_TABLE = "CREATE TABLE " + Util.TABLE_NAME_2 + " ("
                + Util.KEY_ID_2 + " INTEGER PRIMARY KEY, "
                + Util.KEY_ID_FOREIGN_2 + " INTEGER, "
                + Util.KEY_NAME_PLANT_2 + " TEXT, "
                + Util.KEY_TIME_2 + " TEXT, "
                + Util.KEY_TITLE_2 + " TEXT" + " )";
        sqLiteDatabase.execSQL(CREATE_RECORD_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTs " + Util.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTs " + Util.TABLE_NAME_2);
        onCreate(sqLiteDatabase);
    }
    public void addPlant(Bdplants bdplants){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_NAME, bdplants.getName());
        contentValues.put(Util.KEY_IMAGE, bdplants.getImage());


        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    public  Bdplants getPlant(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.KEY_ID, Util.KEY_NAME, Util.KEY_IMAGE},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        Bdplants bdplants = new Bdplants(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return  bdplants;
    }
    public List<Bdplants> getAllPlants (){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Bdplants> listPlants = new ArrayList<>();
        String selectAllPlant = "select * from " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllPlant, null);
        if (cursor.moveToFirst()){
            do {
                Bdplants bdplants = new Bdplants();
                bdplants.setId(Integer.parseInt(cursor.getString(0)));
                bdplants.setName(cursor.getString(1));
                bdplants.setImage(cursor.getString(2));
                listPlants.add(bdplants);
            } while(cursor.moveToNext());
        }
        return listPlants;

    }
    public void delete_el(Bdplants bdplants){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?", new String[]{String.valueOf(bdplants.getId())});
        db.close();
    }




    public void addRecord(Bdrecords bdrecords){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_ID_FOREIGN_2, bdrecords.getPlant_id());
        contentValues.put(Util.KEY_NAME_PLANT_2, bdrecords.getimage());
        contentValues.put(Util.KEY_TIME_2, bdrecords.getTime());
        contentValues.put(Util.KEY_TITLE_2, bdrecords.getTitle());

        db.insert(Util.TABLE_NAME_2, null, contentValues);
        db.close();
    }

    public List<Bdrecords> getAllRecords (int id){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Bdrecords> listRecord = new ArrayList<>();
        String selectAllRecord = "select * from " + Util.TABLE_NAME_2 + " where " +
                Util.TABLE_NAME_2 + "." + Util.KEY_ID_FOREIGN_2 + "=" + String.valueOf(id);
        Cursor cursor = db.rawQuery(selectAllRecord, null);
        if (cursor.moveToFirst()){
            do {
                Bdrecords bdrecords = new Bdrecords();
                bdrecords.setId(Integer.parseInt(cursor.getString(0)));
                bdrecords.setPlant_id(Integer.parseInt(cursor.getString(1)));
                bdrecords.setimage(cursor.getString(2));
                bdrecords.setTime(cursor.getString(3));
                bdrecords.setTitle(cursor.getString(4));
                listRecord.add(bdrecords);
            } while(cursor.moveToNext());
        }
        return listRecord;
    }
    public void delete_record(Bdrecords bdrecords){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME_2, Util.KEY_ID_2 + "=?", new String[]{String.valueOf(bdrecords.getId())});
        db.close();
    }

//    public Bdrecords getRecord(int id){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(Util.TABLE_NAME_2, new String[] {Util.KEY_ID_2, Util.KEY_ID_FOREIGN_2, Util.KEY_NAME_PLANT_2, Util.KEY_TIME_2, Util.KEY_TITLE_2},
//                Util.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
//        if (cursor != null){
//            cursor.moveToFirst();
//        }
//        Bdrecords bdrecords = new Bdrecords(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2), cursor.getString(3));
//        return  bdrecords;
//    }
}
