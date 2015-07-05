package emotions.kids.com.psychology;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Blob;
import java.util.ArrayList;

/**
 * Created by rakendu on 04/01/15.
 */
public class DBHelper extends SQLiteOpenHelper{

    FeelingModel feelings;

    private static final String tableFeelings = "feelings";
    private static final String KEY_ID = "id";
    private static final String image1 = "image1";
    private static final String image2 = "image2";
    private static final String image3 = "image3";
    private static final String name = "name";
    private static final String desc = "desc";
    private static final String temp = "temp";
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "feelingsDB";




    private static final String createTableFeelings =   "create table if not exists "
            + "feelings" + "(" + KEY_ID + " INTEGER PRIMARY KEY," + name
            + " TEXT," + temp + " INTEGER," + desc
            + " TEXT," +image1+ " BLOB,"+image2+" BLOB,"+image3+" BLOB"+ ")";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("TAG1","sql  "+createTableFeelings);
        db.execSQL(createTableFeelings);

    }

    public long insertFeelings(FeelingModel feelings)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        Log.d("TAG1", "Insert ");
        ContentValues values = new ContentValues();
        values.put(name,feelings.name);
        values.put(desc,feelings.desc);
        values.put(temp,feelings.temp);
        values.put(image1,feelings.image1Bytes);
        values.put(image2,feelings.image2Bytes);
        values.put(image3,feelings.image3Bytes);
        long rowId = db.insert(tableFeelings, null, values);
        Log.d("TAG1","RowId  "+feelings.name);
        return rowId;
    }

    public ArrayList<FeelingModel> retrieveFeelings(){

        ArrayList<FeelingModel> feelingsArrayList = null;
        SQLiteDatabase db =  this.getReadableDatabase();

        int id;
        String[] projection = {KEY_ID,name,temp,desc,image1,image2,image3};
        Cursor feelingsCursor = db.query("feelings",projection,null,null,null,null,null);
        if(feelingsCursor.moveToFirst()) {
            feelingsArrayList = new ArrayList<FeelingModel>();
            Log.d("TAG1", "Hello  Name " + feelingsCursor.getType(0)+" DEs    "+feelingsCursor.getInt(1)+" Img1  "+feelingsCursor.getType(3)+"  IMG2  "+feelingsCursor.getType(6)+"  IMG3  "+feelingsCursor.getType(6));
            Log.d("TAG1", "The feelings  Name " + feelingsCursor.getString(1));
            //String[] feelingList = new String[feelingsCursor.getCount()];
            do {
                FeelingModel feelings = new FeelingModel();
                feelings.name = feelingsCursor.getString(1);
                feelings.temp = feelingsCursor.getInt(2);
                feelings.desc = feelingsCursor.getString(3);
                feelings.image1Bytes = feelingsCursor.getBlob(4);
                feelings.image2Bytes = feelingsCursor.getBlob(5);
                feelings.image3Bytes = feelingsCursor.getBlob(6);
                feelingsArrayList.add(feelings);
                Log.d("TAG1", feelingsArrayList.toString());

            }
            while (feelingsCursor.moveToNext());
        }


        return feelingsArrayList;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
