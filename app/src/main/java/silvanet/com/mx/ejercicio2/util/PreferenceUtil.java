package silvanet.com.mx.ejercicio2.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import silvanet.com.mx.ejercicio2.model.ModelUser;

/**
 * Created by LuisAlfredoSilva on 24/06/2016.
 */
public class PreferenceUtil {
    private static final String FILE_NAME ="unam_pref";
    private final SharedPreferences sp;

    public PreferenceUtil(Context context)
    {
        sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }
    public void saveUser(ModelUser modelUser)
    {
        //TODO validar si modelUser==null
        if(modelUser!=null){
            sp.edit().putString("id",String.valueOf(modelUser.id)).apply();
            sp.edit().putString("user_name",modelUser.userName).apply();
            sp.edit().putString("user_pwd",modelUser.password).apply();
            sp.edit().putString("user_date",modelUser.last_session).apply();
            sp.edit().putString("timestamp",modelUser.time_stamp).apply();
        }
    }
    public ModelUser getUser()
    {

        String mId = sp.getString("id",null);
        String mUser = sp.getString("user_name",null);
        String mPassword =sp.getString("user_password",null);
        String mDate = sp.getString("user_date",null);
        String mTime = sp.getString("timestamp",null);
        int id;
        if((TextUtils.isEmpty(mId) || TextUtils.isEmpty(mDate)) ){
            if(TextUtils.isEmpty(mTime)){
                return  null;
            }else{
                return new ModelUser(0, mUser,mPassword,mDate,"0",mTime);
            }
        }else{
            return new ModelUser(Integer.valueOf(mId), mUser,mPassword,mDate,"0",mTime);
        }

    }
}

