package silvanet.com.mx.ejercicio2.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import silvanet.com.mx.ejercicio2.model.ModelUser;
import silvanet.com.mx.ejercicio2.sql.UsersDataSource;

/**
 * Created by LuisAlfredoSilva on 26/06/2016.
 */

public class UsersDataSource {
    private final SQLiteDatabase db;
    public UsersDataSource (Context context) {
        MySqliteHelper helper = new MySqliteHelper(context);
        db=helper.getWritableDatabase();
    }

    public void saveUser(ModelUser modelUser){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_USER_NAME,modelUser.userName);
        contentValues.put(MySqliteHelper.COLUMN_USER_PWD,modelUser.password);
        contentValues.put(MySqliteHelper.COLUMN_USER_LASTSESSION,modelUser.last_session);
        contentValues.put(MySqliteHelper.COLUMN_USER_TIMEINAPP,modelUser.time_in);
        db.insert(MySqliteHelper.TABLE_USER_NAME,null,contentValues);
    }
    public void deletUser(ModelUser modelUser){
        db.delete(MySqliteHelper.TABLE_USER_NAME,MySqliteHelper.COLUMN_USER_ID+"=?",new String[]{String.valueOf(modelUser.id)});
    }
    public List<ModelUser> getAllUsers(){
        List<ModelUser> modelUserList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_USER_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_ID));
            String userName=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_NAME));
            String userPWD = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_PWD));
            String userLastSession = cursor.getColumnName(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_LASTSESSION));
            String userTimeIn = cursor.getColumnName(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_TIMEINAPP));
            ModelUser modelUser = new ModelUser(id,userName,userPWD,userLastSession,userTimeIn,"0");
            /*modelUser.id=id;
            modelUser.name=userName;
            modelUser.pwd=userPWD;
            modelUser.last_session=userLastSession;
            modelUser.time_in=userTimeIn;*/
            modelUserList.add(modelUser);
        }
        return modelUserList;
    }
    public List<ModelUser> getUser(String user, String pwd){
        List<ModelUser> modelUserList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_USER_NAME,null,MySqliteHelper.COLUMN_USER_NAME+"=? and "+MySqliteHelper.COLUMN_USER_PWD+"=?",new String[]{user,pwd},null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_ID));
            String userName=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_NAME));
            String userPWD = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_PWD));
            String userLastSession = cursor.getColumnName(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_LASTSESSION));
            String userTimeIn = cursor.getColumnName(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_TIMEINAPP));
            ModelUser modelUser = new ModelUser(id,userName,userPWD,userLastSession,userTimeIn,"0");

            modelUserList.add(modelUser);

        }
        return modelUserList;
    }
    public List<ModelUser> getUserByID(String shared_id){
        List<ModelUser> modelUserList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_USER_NAME,null,MySqliteHelper.COLUMN_USER_ID+"=?",new String[]{shared_id},null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_ID));
            String userName=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_NAME));
            String userPWD = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_PWD));
            String userLastSession = cursor.getColumnName(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_LASTSESSION));
            String userTimeIn = cursor.getColumnName(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_TIMEINAPP));
            ModelUser modelUser = new ModelUser(id,userName,userPWD,userLastSession,userTimeIn,"0");

            modelUserList.add(modelUser);
        }
        return modelUserList;
    }


}



