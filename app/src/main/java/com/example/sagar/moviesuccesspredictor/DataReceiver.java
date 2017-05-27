package com.example.sagar.moviesuccesspredictor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.picasso.Picasso;

/**
 * Created by Sagar on 5/8/2017.
 */

public class DataReceiver {

    private Context mContext;
    private String[] arrangedstring=new String[3];

    public DataReceiver(Context context) {
        this.mContext=context;
    }

        public  String[] getMovie_name(){
            SQLiteDatabase db= open();
            Cursor cursor = db.query("data",
                    new String[]{"_id", "Movie_Name"},
                    null,
                    null, null, null, null
            );
            String send[]=new String[3];
            send= arrangeDescending(cursor,1);
            cursor.close();
            return send;
        }
        public String[] getImagelink(){
            SQLiteDatabase db= open();
            Cursor cursor = db.query("data",
                    new String[]{"_id",  "Image_Link"},
                    null,
                    null, null, null, null
            );
            String send[]=new String[3];
            send= arrangeDescending(cursor,1);
            cursor.close();
            return send;
        }
        public  String[] getRelease_date(){

            SQLiteDatabase db= open();
            Cursor cursor = db.query("data",
                    new String[]{"_id","Release_Date"},
                    null,
                    null, null, null, null
            );
            String send[]=new String[3];
            send= arrangeDescending(cursor,1);
            cursor.close();
            return send;
        }
        public String[] getPrediction(){
            SQLiteDatabase db= open();
            Cursor cursor = db.query("data",
                    new String[]{"_id","Prediction"},
                    null,
                    null, null, null, null
            );
            String send[]=new String[3];
             send= arrangeDescending(cursor,1);
            cursor.close();
            return  send;
        }

        private SQLiteDatabase open(){
            SQLiteOpenHelper DataBase = new DataBase(mContext);
            SQLiteDatabase db = DataBase.getReadableDatabase();
            return  db;
        }
    private String[] rvereseArray(String arr[], int start, int end)
    {
        String temp;
        while (start < end)
        {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return arr;
    }

       private String[] arrangeDescending(Cursor cursor,int column_no ){
                if(cursor.moveToFirst()){
        int count=0;
                    do{
                        arrangedstring[count]=cursor.getString(column_no);

                        count++;

                        cursor.moveToNext();
                    }while(!cursor.isAfterLast());
                    int end=getnumber(arrangedstring);
                    arrangedstring=rvereseArray(arrangedstring,0,(end-1));
                }
            return arrangedstring;
        }
    private int getnumber(String[]received){
        int count=0;
        for(int i=0;i<received.length;i++){
            if(received[i]==null){
                break;
            }count++;
        }
        return count;
    }
       /* private String[] moveStringRight(String[] received){
            //moves
            int count=getnumber(received);
            for(int i=(count-1);i>=0;i--){
                received[i+1]=received[i];
                if(i==0) {
                    received[0]=null;
                }

            }
            return received;
        }
    private String[] moveStringLeft(String[] received){
        //moves
        int i;
        int count=getnumber(received);
        for( i=1;i<count;i++){
            received[i-1]=received[i];
        }
        received[i]=null;
        return received;
    }

*/
}
