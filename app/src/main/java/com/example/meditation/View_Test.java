package com.example.meditation;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class View_Test extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment_home fragment_1;
    Fragment_sleep fragment_2;
    Fragment_meditation fragment_3;
    Fragment_music fragment_4;
    Fragment_more fragment_5;

    TimePickerDialog dialog_start;
    int hour_24;
    int minute_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view__test);
        bottomNavigationView = findViewById(R.id.bottomNavigationView); //프래그먼트 생성

        fragment_1 = new Fragment_home();
        fragment_2 = new Fragment_sleep();
        fragment_3 = new Fragment_meditation();
        fragment_4 = new Fragment_music();
        fragment_5 = new Fragment_more();





        //제일 처음 띄워줄 뷰를 세팅해줍니다.
        //까지 해줘야 합니다. commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,fragment_1).commitAllowingStateLoss();

        //bottomnavigationview의 아이콘을 선택 했을때 원하는 프래그먼트가 띄워질 수 있도록 리스너를 추가합니다.
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){ //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
                     case R.id.tab1:{ getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,fragment_1).commitAllowingStateLoss();
                     return true;
                     } case R.id.tab2:{ getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,fragment_2).commitAllowingStateLoss();
                     return true;
                     } case R.id.tab3:{ getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,fragment_3).commitAllowingStateLoss();
                     return true;
                     } case R.id.tab4:{ getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,fragment_4).commitAllowingStateLoss();
                        return true;
                    } case R.id.tab5:{ getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,fragment_5).commitAllowingStateLoss();
                        return true;
                    }
                    default: return false;
                }
            }
        });
    }
    public void testLog(){
        Log.i("activity","testLog");
    }

//    public void TimePickerOn(){
//
//        SharedPreferences sharedPreferences = getSharedPreferences("daily alarm", Context.MODE_PRIVATE);
//        long millis = sharedPreferences.getLong("nextNotifyTime", Calendar.getInstance().getTimeInMillis());
//        Date date = new Date(millis);
//        SimpleDateFormat sdfNow_hour = new SimpleDateFormat("HH");
//        String formatHour = sdfNow_hour.format(date);
//
//        Calendar nextNotifyTime = new GregorianCalendar();
//        nextNotifyTime.setTimeInMillis(millis);
//
//
//        final AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
//        dialog_start = new TimePickerDialog(this, listener_start, Integer.parseInt(formatHour),00, false);
//
//
//        dialog_start.show();
//
//    }

//    private TimePickerDialog.OnTimeSetListener listener_start = new TimePickerDialog.OnTimeSetListener() {
//        @Override
//
//        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//            Toast.makeText(getApplicationContext(), hourOfDay + "시 " + minute + "분", Toast.LENGTH_SHORT).show();
//
//
//
//            String min = String.valueOf(minute);
//            if(minute<10){
//                min = "0"+min;
//            }
//
//            fragment_sleep.btn_input_time.setText(hourOfDay+":"+min);
//            hour_24 = hourOfDay;
//            minute_ = minute ;
//        }
//
//    };
}

