package silvanet.com.mx.ejercicio2.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import silvanet.com.mx.ejercicio2.service.ServiceTimer;

/**
 * Created by LuisAlfredoSilva on 24/06/2016.
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME ="unamsqlite";
    private final static int DATABASE_VERSION=2;
    public static final String TABLE_NAME ="item_table";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_ITEM_NAME = "name";
    public static final String COLUMN_ITEM_DESC = "description";
    public static final String COLUMN_ITEM_RESOURCE = "resource_id";

    private static final String CREATE_TABLE ="create table "+TABLE_NAME+
            "("+COLUMN_ID+" integer primary key autoincrement,"+
            COLUMN_ITEM_NAME+" text not null,"+
            COLUMN_ITEM_DESC+ " text not null,"+
            COLUMN_ITEM_RESOURCE+" integer not null)";




    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(ServiceTimer.TAG,"OnUpgrade SQL from "+oldVersion+ " to "+newVersion);

    }
}
