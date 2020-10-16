package com.example.meditation;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class InputEventActivity extends AppCompatActivity {
        static int day_year;
        static int day_month;
        static int day_day;
        static int img_click=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_event);
        Button button_date = findViewById(R.id.button_date);

            // 현재시간을 msec 으로 구한다.
            long now = System.currentTimeMillis();

            // 현재시간을 date 변수에 저장한다.
            Date date = new Date(now);

            // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
            SimpleDateFormat sdfNow_year = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdfNow_month = new SimpleDateFormat("MM");
            SimpleDateFormat sdfNow_day = new SimpleDateFormat("dd");

            // nowDate 변수에 값을 저장한다.
            String formatYear = sdfNow_year.format(date);
            String formatMonth = sdfNow_month.format(date);
            String formatDay = sdfNow_day.format(date);

            //날짜 선택 안할시 기본값 = 현재 날짜
            day_year= Integer.parseInt(formatYear);
            day_month= Integer.parseInt(formatMonth);
            day_day= Integer.parseInt(formatDay);



        DatePickerDialog dialog_date = new DatePickerDialog(this,listener_date,Integer.parseInt(formatYear)
                ,Integer.parseInt(formatMonth),Integer.parseInt(formatDay));


        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_date.show();
            }
        });
        Button button_input = findViewById(R.id.button_input);
        button_input.setClipToOutline(true);
        button_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("클릭 작동하는지?");
                SharedPreferences spf = getSharedPreferences("input",0);
                SharedPreferences.Editor editor = spf.edit();

                if(img_click==0){
                editor.putInt("img",R.drawable.ic_baseline_sentiment_very_dissatisfied_48);
                }else if(img_click==1){
                editor.putInt("img",R.drawable.ic_baseline_sentiment_satisfied_48);
                }else {
                editor.putInt("img",R.drawable.ic_baseline_sentiment_very_satisfied_48);
                }

                editor.putInt("year",day_year);
                editor.putInt("month",day_month);
                editor.putInt("day",day_day);
                editor.commit();
                System.out.println("저장된 년/월/일"+day_year+"/"+day_month+"/"+day_day+"표정 : "+img_click);
                setResult(0);
                finish();
            }
        });
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(img_click==0){
                    img_click++;
                    imageButton.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_48);
                    System.out.println(img_click+"/"+imageButton.getDrawable());
                }else if(img_click==1){
                    img_click++;
                    imageButton.setImageResource(R.drawable.ic_baseline_sentiment_very_satisfied_48);
                    System.out.println(img_click+"/"+imageButton.getDrawable());
                }else {
                    img_click=0;
                    imageButton.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_48);
                    System.out.println(img_click+"/"+imageButton.getDrawable());
                }
            }
        });

    }
    private DatePickerDialog.OnDateSetListener listener_date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String msg = String.format("%d 월 %d 일", month+1, dayOfMonth);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            day_year = year;
            day_month = month;
            day_day = dayOfMonth;
            Button button_date = findViewById(R.id.button_date);
            button_date.setText(day_year+" / "+day_month+" / "+day_day);
        }
    };
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String msg = "추가를 취소하였습니다.";
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        setResult(1);
        finish();
    }
}