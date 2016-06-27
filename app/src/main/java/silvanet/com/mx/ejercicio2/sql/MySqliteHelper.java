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
    private final static String DATABASE_NAME ="DBsqlite";
    private final static int DATABASE_VERSION=1;
    // Tabla para los Items
    public static final String TABLE_ITEM_NAME ="item_table";
    public static final String COLUMN_ITEM_ID = BaseColumns._ID;
    public static final String COLUMN_ITEM_NAME = "name";
    public static final String COLUMN_ITEM_DESC = "description";
    public static final String COLUMN_ITEM_RESOURCE = "resource_id";

    private static final String CREATE_ITEM_TABLE ="create table "+TABLE_ITEM_NAME+
            "("+COLUMN_ITEM_ID+" integer primary key autoincrement,"+
            COLUMN_ITEM_NAME+" text not null,"+
            COLUMN_ITEM_DESC+ " text not null,"+
            COLUMN_ITEM_RESOURCE+" integer not null)";

    //Tabla para los Usuarios
    public static final String TABLE_USER_NAME = "user_table";
    public static final String COLUMN_USER_ID = BaseColumns._ID;
    public static final String COLUMN_USER_NAME= "name";
    public static final String COLUMN_USER_PWD= "pwd";
    public static final String COLUMN_USER_LASTSESSION= "last_session";
    public static final String COLUMN_USER_TIMEINAPP= "time_in";

    private static final String CREATE_USER_TABLE = "create table "+TABLE_USER_NAME+
            "("+COLUMN_USER_ID+ " integer primary key autoincrement,"+
            COLUMN_USER_NAME+ " text not null,"+
            COLUMN_USER_PWD+ " text not null,"+
            COLUMN_USER_LASTSESSION+ " text not null,"+
            COLUMN_USER_TIMEINAPP+" text not null)";

    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEM_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Log.d(ServiceTimer.TAG,"OnUpgrade SQL from "+oldVersion+ " to "+newVersion);

    }
}
