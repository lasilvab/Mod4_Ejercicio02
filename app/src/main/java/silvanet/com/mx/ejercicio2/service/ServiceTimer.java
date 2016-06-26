package silvanet.com.mx.ejercicio2.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by LuisAlfredoSilva on 24/06/2016.
 */
public class ServiceTimer extends Service {
    public static final String TAG = "unam_tag";
    public static final String ACTION_SEND_TIMER ="silvanet.com.mx.ejercicio2.SEND_TIMER";
    int counter;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            counter++;
            handler.postDelayed(runnable,1000);
            Intent i = new Intent(ACTION_SEND_TIMER);
            i.putExtra("timer",counter);
            sendBroadcast(i);
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"OnstartCommand called");
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"Oncreate servicio");
        handler.post(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"OnDestroy Servicio");
        handler.removeCallbacks(runnable);
    }
}
