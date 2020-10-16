package com.example.meditation;

import android.app.*;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Fragment_sleep extends Fragment implements View.OnClickListener {


    AlarmManager alarm_manager;
    TimePickerDialog dialog_start;
    SharedPreferences spf;

    Button btn_input;
    Button btn_input_time;
    EditText edit_text;

    int hour_24;
    int minute_;

    String formatHour;
    String formatMin;
    String formatYear;
    String formatMonth;
    String formatDay;
    Calendar calendar;
    Calendar calendar_save;
    Intent my_intent;
    Button btn_cancel;
    PendingIntent pendingIntent;

    TextView text_alarm;

    View_Test activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (View_Test) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_sleep, container, false);


        SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("daily alarm", Context.MODE_PRIVATE);
        long millis = sharedPreferences.getLong("nextNotifyTime", Calendar.getInstance().getTimeInMillis());
        spf = Objects.requireNonNull(getActivity()).getSharedPreferences("alarm", Context.MODE_PRIVATE);

        // 날짜 생성
        Date date = new Date(millis);
        SimpleDateFormat sdfNow_hour = new SimpleDateFormat("HH");
        SimpleDateFormat sdfNow_min = new SimpleDateFormat("mm");
        SimpleDateFormat sdfNow_year = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfNow_month = new SimpleDateFormat("MM");
        SimpleDateFormat sdfNow_day = new SimpleDateFormat("dd");
        formatHour = sdfNow_hour.format(date);
        hour_24 = Integer.parseInt(formatHour);
        formatMin = sdfNow_min.format(date);
        formatYear = sdfNow_year.format(date);
        formatMonth = sdfNow_month.format(date);
        formatDay = sdfNow_day.format(date);
        Log.i("formatHour",formatHour);
        Log.i("formatMin",formatMin);
        Log.i("formatYear",formatYear);
        Log.i("formatMonth",formatMonth);
        Log.i("formatDay",formatDay);

        calendar = Calendar.getInstance();
        // 알람매니저 설정
        alarm_manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        edit_text = viewGroup.findViewById(R.id.edit_text);

        btn_input = viewGroup.findViewById(R.id.btn_input);
        btn_input.setOnClickListener(this);
        btn_input_time = viewGroup.findViewById(R.id.btn_input_time);
        btn_input_time.setOnClickListener(this);
        btn_cancel = viewGroup.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
        text_alarm = viewGroup.findViewById(R.id.test_alarm);


        // 알람리시버 intent 생성
        my_intent = new Intent(getContext(), Alarm_Receiver.class);
        calendar_save = Calendar.getInstance();
        Long time = spf.getLong("alarm", 1);
        Log.i("save", String.valueOf(time));
        try {
        calendar_save.setTimeInMillis(time);
        Date date_save = new Date(calendar_save.getTimeInMillis());
        SimpleDateFormat sdfNow_hour_save = new SimpleDateFormat("HH");
        SimpleDateFormat sdfNow_min_save = new SimpleDateFormat("mm");
        String formatHour_save = sdfNow_hour_save.format(date_save);
        String formatMin_save = sdfNow_min_save.format(date_save);
        Log.i("formatHour_save",formatHour_save);
        Log.i("formatMin_save",formatMin_save);
        text_alarm.setText(formatHour_save + " : " + formatMin_save);
        } catch (NullPointerException e) {
            Log.i("calendar_save","null");
        }
        if(time == 1){
            text_alarm.setText("설정된 알람이 없습니다.");
        }
        return viewGroup;
    }


    private TimePickerDialog.OnTimeSetListener listener_start = new TimePickerDialog.OnTimeSetListener() {
        @Override

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Toast.makeText(getContext(), hourOfDay + "시 " + minute + "분", Toast.LENGTH_SHORT).show();

            String min = String.valueOf(minute);
            if (minute < 10) {
                min = "0" + min;
            }
            btn_input_time.setText(hourOfDay + ":" + min);
            hour_24 = hourOfDay;
            minute_ = minute;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_input:
                Log.i("onclick", "btn_input");
                InputAlarm(hour_24, minute_);

                break;
            case R.id.btn_input_time:
                Log.i("onclick", "btn_input_time");
                dialog_start = new TimePickerDialog(getContext(), listener_start, Integer.parseInt(formatHour), 00, false);
                dialog_start.show();
                break;
            case R.id.btn_cancel:
                Log.i("onclick", "btn_cancel");
                CancleAlarm();
        }
    }

    private void CancleAlarm() {
        // 알람매니저 취소
        try {
            alarm_manager.cancel(pendingIntent);
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "알람이 없습니다", Toast.LENGTH_SHORT).show();

            my_intent.putExtra("state", "alarm off");

            // 알람취소
            Objects.requireNonNull(getContext()).sendBroadcast(my_intent);
            Log.i("cancleAlarm", "");
        }
        try{
            SharedPreferences.Editor editor = spf.edit();
            editor.remove("alarm");
            editor.commit();

            //알람 삭제 텍스트 표시
            text_alarm.setText("설정된 알람이 없습니다.");
            Toast.makeText(getContext(), "알람이 없습니다", Toast.LENGTH_SHORT).show();


            Log.i("cancleAlarm", "remove");
            Log.i("cancleAlarm", String.valueOf(spf.getLong("alarm",0)));
        }catch(NullPointerException e){
          e.printStackTrace();
            Log.i("cancleAlarm", "remove_null");
        }

    }

    public void InputAlarm(int hour, int min) {

        btn_input_time.setText("시 간 설 정");

        String str = hour + ":" + min;

        // receiver에 string 값 넘겨주기
        my_intent.putExtra("state", "alarm on");


        pendingIntent = PendingIntent.getBroadcast(getContext(), 0, my_intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Log.i("before", String.valueOf(calendar.getTimeInMillis()));
        //입력받은 시간 연결
        calendar.set(Integer.parseInt(formatYear), Integer.parseInt(formatMonth)-1, Integer.parseInt(formatDay), hour_24, minute_,0);
        Log.i("year", formatYear);
        Log.i("mon", formatMonth);
        Log.i("day", formatDay);
        Log.i("hour", String.valueOf(hour_24));
        Log.i("min", String.valueOf(minute_));
        text_alarm.setText(hour_24 + ":" + minute_); // 텍스트 변경

        // 알람셋팅
        alarm_manager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,
                pendingIntent);

        SharedPreferences.Editor editor = spf.edit();

        editor.putLong("alarm", calendar.getTimeInMillis());
        editor.commit();
        Log.i("long time", String.valueOf(spf.getLong("alarm", 0)));

    }

}