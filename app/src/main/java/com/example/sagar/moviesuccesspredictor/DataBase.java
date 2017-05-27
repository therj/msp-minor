package com.example.sagar.moviesuccesspredictor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

/**
 * Created by Sagar on 5/8/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    private  static  String DB_NAME="MovieData";

    private  static  String TABLE_VARIABLES="data";

    private static int DB_VERSION=1;
    DataBase(Context context)
    {super(context,DB_NAME,null,DB_VERSION);//null is for cursors//sqlite helper classes constructor is being called

    }
    @Override
    public void onCreate(SQLiteDatabase db)  { //Sqlitedatabase class gives us access to database

        db.execSQL("CREATE TABLE "+TABLE_VARIABLES+"("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Movie_Name TEXT,"
                + "Image_Link TEXT,"
                +"Release_Date TEXT,"

                + "Prediction TEXT);");
        insert_data(db,"Bahubali","https://www.desiretrees.in/wp-content/uploads/2016/12/Baahubali-2-Posters.jpg","2017/06/23","success");
        insert_data(db,"3 Idiots","http://s3.india.com/wp-content/uploads/2016/01/3-idiots-sequel-1.jpg","2018/06/11","success");
        insert_data(db,"Sagar","http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg","2019/07/17" ,"success");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private void insert_data(SQLiteDatabase db,String movie_name,String image_link,String release_date, String prediction){
        ContentValues
                movie_data= new ContentValues();
        movie_data.put("Movie_Name",movie_name);
        movie_data.put("Image_Link",image_link);
        movie_data.put("Release_Date",release_date);
        movie_data.put("Prediction",prediction);
        db.insert("data",null,movie_data);


    }
}
