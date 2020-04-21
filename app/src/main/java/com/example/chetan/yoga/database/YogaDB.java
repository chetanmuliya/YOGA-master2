
package com.example.chetan.yoga.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class YogaDB extends SQLiteAssetHelper {

    private static final String DB_NAME="YogaDB.db";
    private static final int DB_VER=1;
    public SQLiteDatabase db;

    public YogaDB(Context context) {
        super(context, DB_NAME, null, null, DB_VER);
    }

    public int getSettingMode(){
        db=getReadableDatabase();
        SQLiteQueryBuilder sb=new SQLiteQueryBuilder();
        String[] sqlSelect={"Mode"};
        String sqlTable="Setting";

        sb.setTables(sqlTable);
        Cursor c=sb.query(db,sqlSelect,null,null,null,null,null);
        c.moveToFirst();

        return c.getInt(c.getColumnIndex("Mode"));
    }
    public  void saveSetting(int Mode){
     db=getWritableDatabase();
        String query="UPDATE Setting SET Mode = "+Mode;
        db.execSQL(query);
    }
    public List<String> getWorkoutdays(){

        db=getReadableDatabase();
        SQLiteQueryBuilder sb=new SQLiteQueryBuilder();
        String[] sqlSelect={"Day"};
        String sqlTable="WorkoutDay";
        sb.setTables(sqlTable);


         List<String> result=new ArrayList<>();
        Cursor c= sb.query(db,sqlSelect,null,null,null,null,null);
        if(c.moveToFirst()){
            do {
                result.add(c.getString(c.getColumnIndex("Day")));
         }while (c.moveToNext());
        }
       return result;
    }
    public  void saveWorkoutDays(String value){
        db=getReadableDatabase();
        String query=String.format("INSERT into WorkoutDay(Day) values('%s');",value);
        db.execSQL(query);
    }
}
