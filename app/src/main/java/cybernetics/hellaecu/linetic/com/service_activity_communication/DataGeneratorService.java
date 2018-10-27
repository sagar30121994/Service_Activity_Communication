package cybernetics.hellaecu.linetic.com.service_activity_communication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import cybernetics.hellaecu.linetic.com.service_activity_communication.UsingEventBus.GlobalEventBus;
import cybernetics.hellaecu.linetic.com.service_activity_communication.UsingEventBus.MessegeEvent;

public class DataGeneratorService extends Service {

    int i=0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Intent","onStartCommand");
        Timer timerObj = new Timer();
          TimerTask timerTaskObj = new TimerTask() {
            @Override
            public void run() {
                //perform your action here
                i=i+5;
                sendBroadcastUpdate(i);
                MessegeEvent messegeEvent=new MessegeEvent(i);
                GlobalEventBus.getBus().post(messegeEvent);
            }
        };

          timerObj.schedule(timerTaskObj,1,1000);

        return START_NOT_STICKY;

    }




    private void sendBroadcastUpdate(int i) {
        Log.d("Intent",i+"");
        Intent intent=new Intent("Action_data_update");
        intent.putExtra("data",i+"");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    /**
     * Called by the system to notify a Service that it is no longer used and is being removed.  The
     * service should clean up any resources it holds (threads, registered
     * receivers, etc) at this point.  Upon return, there will be no more calls
     * in to this Service object and it is effectively dead.  Do not call this method directly.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
