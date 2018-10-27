package cybernetics.hellaecu.linetic.com.service_activity_communication.UsingEventBus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cybernetics.hellaecu.linetic.com.service_activity_communication.DataGeneratorService;
import cybernetics.hellaecu.linetic.com.service_activity_communication.R;
import cybernetics.hellaecu.linetic.com.service_activity_communication.UsingBroadCastReceiver.MainActivity;

public class Main2Activity extends AppCompatActivity {

    EventBus eventBus;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i=new Intent(this, DataGeneratorService.class);
        startService(i);
        textView=findViewById(R.id.textView);

        eventBus=GlobalEventBus.getBus();
        eventBus.register(this);

    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageFromService(MessegeEvent event) {
        Log.d("MessegeEvent",  event.getMessageEvent()+"");
        textView.setText(event.getMessageEvent()+"");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
