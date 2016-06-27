package silvanet.com.mx.ejercicio2.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import silvanet.com.mx.ejercicio2.model.ModelItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuisAlfredoSilva on 24/06/2016.
 */
public class ItemDataSource {
    private final SQLiteDatabase db;

    public ItemDataSource(Context context) {
        MySqliteHelper helper = new MySqliteHelper(context);
        db=helper.getWritableDatabase();
    }
    public void saveItem(ModelItem modelItem)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_ITEM_NAME,modelItem.item);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_DESC,modelItem.description);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_RESOURCE,modelItem.resourceId);
        db.insert(MySqliteHelper.TABLE_ITEM_NAME,null,contentValues);
    }
    public void deleteItem(ModelItem modelItem)
    {
        db.delete(MySqliteHelper.TABLE_ITEM_NAME,MySqliteHelper.COLUMN_ITEM_ID+"=?",
                new String[]{String.valueOf(modelItem.id)});
    }

    public List<ModelItem> getAllItems()
    {
        List<ModelItem> modelItemList = new ArrayList<>();
        Cursor cursor =db.query(MySqliteHelper.TABLE_ITEM_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_ID));
            String itemName=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_NAME));
            String desccription = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_DESC));
            int resourceId = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_RESOURCE));
            ModelItem modelItem = new ModelItem();
            modelItem.id=id;
            modelItem.resourceId=resourceId;
            modelItem.description=desccription;
            modelItem.item=itemName;
            modelItemList.add(modelItem);
        }

        return modelItemList;
    }
}
