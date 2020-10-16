package com.example.meditation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.N)
public class InputActivity extends AppCompatActivity {

    final Calendar cal = Calendar.getInstance();
    final Calendar cal_2 = Calendar.getInstance();


    Button btn_input;
    Button btn_al;
    TextView start_date;
    TextView start_time;
    TextView stop_time;
    EditText Title;
    EditText editText_input;

    static int day_year;
    static int day_month;
    static int day_day;
    static long start_time_hour;
    static long stop_time_hour;
    static long start_time_min;
    static long stop_time_min;

    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();

    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);

    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("MM/dd ");
    SimpleDateFormat sdfNow_year = new SimpleDateFormat("yyyy");
    SimpleDateFormat sdfNow_month = new SimpleDateFormat("MM");
    SimpleDateFormat sdfNow_Time = new SimpleDateFormat("HH:00");
    SimpleDateFormat sdfNow_day = new SimpleDateFormat("dd");
    SimpleDateFormat sdfNow_hour = new SimpleDateFormat("HH");

    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);
    String formatYear = sdfNow_year.format(date);
    String formatMonth = sdfNow_month.format(date);
    String formatDay = sdfNow_day.format(date);
    String formatTime = sdfNow_Time.format(date);
    String formatHour = sdfNow_hour.format(date);

    View layout_start_time_input;
    View layout_stop_time_input;
    View layout_date_input;

    TimePickerDialog dialog_start;
    TimePickerDialog dialog_stop;
    DatePickerDialog dialog_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_input);

        start_date = findViewById(R.id.date);
        start_time = findViewById(R.id.start_time);
        stop_time = findViewById(R.id.stop_time);
        btn_input = findViewById(R.id.btn_input);
//        btn_al = findViewById(R.id.btn_al);

        SharedPreferences sharedPreferences = getSharedPreferences("rebuild",MODE_PRIVATE);
        String text = sharedPreferences.getString("date","");
        if (text.equals("")) {
            start_date.setText(formatDate);
            start_time.setText(formatTime);
            stop_time.setText(formatTime);

        } else {
            start_date.setText(sharedPreferences.getString("date",""));
            start_time.setText(sharedPreferences.getString("start_time",""));
            stop_time.setText(sharedPreferences.getString("stop_time",""));
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putString("date","");
            editor.putString("start_time","");
            editor.putString("stop_time","");
//            SharedPreferences sharedPreferences = getSharedPreferences("rebuild",MODE_PRIVATE);
        }

        super.onCreate(savedInstanceState);





        layout_start_time_input = findViewById(R.id.layout_start_time_input);
        layout_stop_time_input = findViewById(R.id.layout_stop_time_input);
        layout_date_input = findViewById(R.id.layout_date_input);


        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }



    protected void onResume() {
        super.onResume();
        
     start_time_hour = System.currentTimeMillis();
     stop_time_hour = System.currentTimeMillis()+60*60*1000;

        // 다이얼로그 바디
        final AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);

        dialog_start = new TimePickerDialog(this, listener_start, Integer.parseInt(formatHour),00, false);
        dialog_stop = new TimePickerDialog(this, listener_stop, Integer.parseInt(formatHour), 00, false);
        dialog_date = new DatePickerDialog(this,listener_date,Integer.parseInt(formatYear) ,Integer.parseInt(formatMonth),Integer.parseInt(formatDay));

        layout_date_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_date.show();
            }
        });

        layout_start_time_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_start.show();
            }
        });

        layout_stop_time_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_stop.show();
            }
        });

        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Title = findViewById(R.id.Title);
                editText_input = findViewById(R.id.editText_input);

                String text = editText_input.getText().toString();
                String title = Title.getText().toString();
                String location= "null";

//                String title, String location,String content, long begin, long end, boolean isday


                if(cal.getTimeInMillis()>cal_2.getTimeInMillis()){
                    cal_2.setTimeInMillis(cal.getTimeInMillis());
                }

                addEvent(title,location,text,cal.getTimeInMillis(),cal_2.getTimeInMillis(),false);

                SharedPreferences sharedPreferences = getSharedPreferences("rebuild",MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();

                editor.putString("title","");
                editor.putString("date","");
                editor.putString("start_time","");
                editor.putString("stop_time","");
                editor.putString("editText_input_save","");


                editor.commit();

                start_date.setText(formatDate);
                start_time.setText(formatTime);
                stop_time.setText(formatTime);

            }
        });



    }

    @Override
    protected void onStop() {
        super.onStop();

        Title = findViewById(R.id.Title);
        start_date = findViewById(R.id.date);
        start_time = findViewById(R.id.start_time);
        stop_time = findViewById(R.id.stop_time);
        editText_input = findViewById(R.id.editText_input);

        String title_save = Title.getText().toString();
        String date_save = start_date.getText().toString();
        String start_time_save = start_time.getText().toString();
        String stop_time_save = stop_time.getText().toString();
        String editText_input_save = editText_input.getText().toString();


        SharedPreferences sharedPreferences = getSharedPreferences("rebuild",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putString("title",title_save);
        editor.putString("date",date_save);
        editor.putString("start_time",start_time_save);
        editor.putString("stop_time",stop_time_save);
        editor.putString("editText_input_save",editText_input_save);

        editor.commit();


        Toast.makeText(this, "onStop,save", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        SharedPreferences sharedPreferences = getSharedPreferences("rebuild",MODE_PRIVATE);
//        SharedPreferences.Editor editor= sharedPreferences.edit();
//
//        editor.putString("title","");
//        editor.putString("date","");
//        editor.putString("start_time","");
//        editor.putString("stop_time","");
//        editor.putString("editText_input_save","");
//
//
//        editor.commit();
//
//        start_date.setText(formatDate);
//        start_time.setText(formatTime);
//        stop_time.setText(formatTime);
//
//        finish();
    }


    private DatePickerDialog.OnDateSetListener listener_date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String msg = String.format("%d 월 %d 일", month+1, dayOfMonth);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            start_date.setText(msg);
            day_year = year;
            day_month = month;
            day_day = dayOfMonth;
            cal.set(year,month,dayOfMonth);

            Log.i("cal : ", String.valueOf(cal.getTimeInMillis()));
        }
    };

    private TimePickerDialog.OnTimeSetListener listener_stop = new TimePickerDialog.OnTimeSetListener() {
        @Override

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Toast.makeText(getApplicationContext(), hourOfDay + "시 " + minute + "분", Toast.LENGTH_SHORT).show();

            String min = String.valueOf(minute);
            if(minute<10){
                min = "0"+min;
            }
            cal_2.set(day_year,day_month,day_day,hourOfDay,minute);
            stop_time_hour=hourOfDay;
            stop_time_min=minute;
            stop_time.setText(hourOfDay+":"+min);
        }

    };

    private TimePickerDialog.OnTimeSetListener listener_start = new TimePickerDialog.OnTimeSetListener() {
        @Override

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Toast.makeText(getApplicationContext(), hourOfDay + "시 " + minute + "분", Toast.LENGTH_SHORT).show();

            String min = String.valueOf(minute);
            if(minute<10){
                min = "0"+min;
            }
            start_time_hour = hourOfDay;
            start_time_min = minute;
            cal.set(day_year,day_month,day_day,hourOfDay,minute);
            start_time.setText(hourOfDay+":"+min);
        }

    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void addEvent(String title, String location,String content, long begin, long end, boolean isday) {

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.ALL_DAY,isday)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.DESCRIPTION, content)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}