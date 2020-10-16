package com.example.meditation;


import android.content.Intent;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Timer extends AppCompatActivity {

    NumberPicker numberPicker_sec;
    NumberPicker numberPicker_min;
    Button button;
    EditText editText;
    String msg;
    int sec;
    int min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        numberPicker_min = findViewById(R.id.numberPicker_min);
        numberPicker_min.setMaxValue(59);

        numberPicker_sec = findViewById(R.id.numberPicker_sec);
        numberPicker_sec.setMaxValue(59);

        button = findViewById(R.id.btn_timer);
        editText = findViewById(R.id.edit_text_timer);

        msg = editText.getText().toString();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min = numberPicker_min.getValue();
                sec = numberPicker_sec.getValue();
                sec = min*60 + sec;
                createTimer(msg,sec);
            }
        });

    }

    public void createTimer(String msg,int sec){
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE,msg)
                .putExtra(AlarmClock.EXTRA_LENGTH,sec)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, false);

        Log.i("Log", String.valueOf(sec));

        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }


}