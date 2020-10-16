package com.example.meditation;

import android.content.Intent;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class Alarm extends AppCompatActivity {

    String msg;
    int hour;
    int min;
    Button button;
    TimePicker timePicker;
    EditText editText;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        timePicker=findViewById(R.id.timepicker);
        hour = timePicker.getHour();
        min = timePicker.getMinute();

        editText = findViewById(R.id.edit_text);
        msg = editText.getText().toString();

        button = findViewById(R.id.btn_input);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE,msg)
                        .putExtra(AlarmClock.EXTRA_HOUR,hour)
                        .putExtra(AlarmClock.EXTRA_MINUTES,min);
                startActivity(intent);
            }
        });

        button2 = findViewById(R.id.btn_move);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Timer.class);
                startActivity(intent);


            }
        });


        button3 = findViewById(R.id.btn_move_show);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                startActivity(intent);

            }
        });



    }


}