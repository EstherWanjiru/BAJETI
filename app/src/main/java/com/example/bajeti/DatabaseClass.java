package com.example.bajeti;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created on 9/11/2019.
 */

public class DatabaseClass extends SQLiteOpenHelper {

    SQLiteDatabase database;
    Context ctx;
    static ArrayList<com.example.bajeti.DataModel> list;
    CustomAdapter listAdapter;
    ListView listView;
    static final private String DBNAME="ExpenseActivity.sqlite";
    public DatabaseClass(Context context) {
        super(context, DBNAME, null, 1);

        this.ctx=context;
    }


    public void queryData(String sql)
    {
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);

    }
    // Insert data to income
    public void insertData(String amount,String category,String note,String date,String image,String spinner )
    {
        database=getWritableDatabase();
        String sql="insert into  incomes  values ( NULL,?,?,?,?,?,? )";
        SQLiteStatement sqLiteStatement=database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,amount);
        sqLiteStatement.bindString(2,category);
        sqLiteStatement.bindString(3,note);
        sqLiteStatement.bindString(4,date);
        sqLiteStatement.bindString(5,image);
        sqLiteStatement.bindString(6,spinner);

        sqLiteStatement.executeInsert();
        Log.e("success","add");

    }

    //Insert Data to ExpenseActivity
    public void insertDataToExpense(String amount,String category,String note,String date,String image,String spinner )
    {
        database=getWritableDatabase();
        String sql="insert into  expense  values ( NULL,?,?,?,?,?,? )";
        SQLiteStatement sqLiteStatement=database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,amount);
        sqLiteStatement.bindString(2,category);
        sqLiteStatement.bindString(3,note);
        sqLiteStatement.bindString(4,date);
        sqLiteStatement.bindString(5,image);
        sqLiteStatement.bindString(6,spinner);

        sqLiteStatement.executeInsert();
        Log.e("success","add");

    }

    public Cursor getData(String sql)
    {
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    public void getDatabase(){
        database=getReadableDatabase();
    }
    public void getAll(){
        list=new ArrayList<>();
        database=getReadableDatabase();
        Cursor cr=database.rawQuery("Select * from "+"expense",null);
        StringBuilder stringBuilder=new StringBuilder();
        while (cr.moveToNext())
        {
            int id=cr.getInt(0);
            String Id = String.valueOf(id);
            String s1=cr.getString(1);
            String s2=cr.getString(2);
            String s3 =cr.getString(3);
            String s4=cr.getString(4);
            String img =cr.getString(5);
            String  s5=cr.getString(6);
            stringBuilder.append(s1+""+s2);
            list.add(new com.example.bajeti.DataModel(Id,s1,s2,s3));
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+" incomes" +"( _id integer primary key autoincrement, amount text, category text, note text,date text,image text,addtype text)");
        sqLiteDatabase.execSQL("create table if not exists "+" expense" +"( _id integer primary key autoincrement, amount text, category text, note text,date text,image text,addtype text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
