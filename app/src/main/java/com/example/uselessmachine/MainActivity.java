package com.example.uselessmachine;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonSelfDestruct;
    private Switch switchUseless;
    private TextView textViewMessage;
    private int count;
    private int destructCount;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
        count = 0;
        destructCount = 10;
    }

    private void setListeners() {
        //TODO self destruct button
        buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSelfDestructSequence();
            }
        });

        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked){
                    //Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
                    startSwitchOffTimer();
                    }
                    /*Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            switchUseless.setChecked(false);
                        }
                    }, 1000);*/
                else{
                    //Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startSelfDestructSequence() {
        //disable the button
        buttonSelfDestruct.setEnabled(false);
        switchUseless.setEnabled(false);
        //start a 10 second countdown timer that updates the display every second
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                buttonSelfDestruct.setText("Destruct in " + destructCount);
                destructCount--;
            }

            @Override
            public void onFinish() {
                textViewMessage.setText(getString(R.string.destruct_face));
                finish();
            }
        }.start();
        //close the activity
    }

    private void startSwitchOffTimer() {
        new CountDownTimer(1000, 100) {

            @Override
            public void onTick(long l) {
                count++;
                if (count < 20) {
                    textViewMessage.setTextSize(count + 10);
                } else if (count < 40) {
                    textViewMessage.setTextSize(count + 10);
                } else if (count < 60) {
                    textViewMessage.setTextSize(count + 10);
                } else {
                    textViewMessage.setTextSize(count + 10);
                }
                if (!switchUseless.isChecked()) {
                    //Log.d(TAG, "onTick: cancel");
                    cancel();
                }
            }

            @Override
            public void onFinish() {
                switchUseless.setChecked(false);
                if (count < 20) {
                    textViewMessage.setText(getString(R.string.neutral_face));
                } else if (count < 40) {
                    textViewMessage.setText(getString(R.string.smiling_face));
                } else if (count < 60) {
                    textViewMessage.setText(getString(R.string.evil_face));
                    //Log.d(TAG, "onFinish: switch set to false");
                }
            }
        }.start();
    }

    private void wireWidgets(){
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct);
        switchUseless = findViewById(R.id.switch_main_useless);
        textViewMessage = findViewById(R.id.textview_main_message);
    }
}
