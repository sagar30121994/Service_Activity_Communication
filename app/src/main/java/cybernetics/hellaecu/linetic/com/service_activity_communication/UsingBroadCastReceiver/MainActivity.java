package cybernetics.hellaecu.linetic.com.service_activity_communication.UsingBroadCastReceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import cybernetics.hellaecu.linetic.com.service_activity_communication.DataGeneratorService;
import cybernetics.hellaecu.linetic.com.service_activity_communication.R;
import cybernetics.hellaecu.linetic.com.service_activity_communication.UsingEventBus.Main2Activity;

public class MainActivity extends Activity {

    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalBroadcastManager.getInstance(this).registerReceiver(dataReceiver, new IntentFilter("Action_data_update"));
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        Intent i=new Intent(this, DataGeneratorService.class);
        startService(i);

        button.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
            finish();

        });
    }

    BroadcastReceiver dataReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent!=null && textView !=null ){
                textView.setText( Objects.requireNonNull(intent.getExtras()).getString("data"));

            }
        }
    };




}
