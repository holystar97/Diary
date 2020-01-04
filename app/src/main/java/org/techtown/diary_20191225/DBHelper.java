package org.techtown.diary_20191225;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper{
    public static String NAME="diary.db";
    public static int VERSION=1;
    public static String USER_TABLE_NAME="user";
    public DBHelper(Context context){
        super(context,NAME,null,VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="CREATE TABLE IF NOT EXISTS user("+
                "id TEXT PRIMARY KEY,"+
                "password TEXT,"+
                "name TEXT,"+
                "birth TEXT,"+
                "email TEXT )";

        db.execSQL(sql);
    }

    public void insertUser(User user){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id",user.getId());
        values.put("password",user.getPw());
        values.put("name",user.getName());
        values.put("birth",user.getBirth());
        values.put("email",user.getEmail());

        db.insert(USER_TABLE_NAME,null,values);
        db.close();
    }

    public ArrayList<User> getAllUser(){

        ArrayList<User> UserList= new ArrayList<User>();
        String selectQuery="SELECT * FROM "+ USER_TABLE_NAME;

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                User user=new User();
                user.setId(cursor.getString(0));
                user.setPw(cursor.getString(1));
                user.setName(cursor.getString(2));
                user.setBirth(cursor.getString(3));
                user.setEmail(cursor.getString(4));
                UserList.add(user);

            }while(cursor.moveToNext());
        }

        return UserList;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion >1){
            db.execSQL("DROP TABLE IF EXISTS user");
        }
    }
}
