package com.example.meditation;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.*;

public class CustomDialog extends Dialog {
    private OnDialogListener listener;
    private Context context;
    private Button mod_bt;
    private EditText mod_name, mod_age;
    private String name;
    private int year,month,day,img;
    private int image, age;
    private int img_click=0;

    public CustomDialog(Context context, final int position, CalData calData) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.customdialog);

        DatePicker datePicker = findViewById(R.id.date_picker);
        ImageButton dialog_img_btn = findViewById(R.id.dialog_img_btn);
        dialog_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(img_click==0){
                    img_click++;
                    dialog_img_btn.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_48);
                    System.out.println(img_click+"/"+dialog_img_btn.getDrawable());
                }else if(img_click==1){
                    img_click++;
                    dialog_img_btn.setImageResource(R.drawable.ic_baseline_sentiment_very_satisfied_48);
                    System.out.println(img_click+"/"+dialog_img_btn.getDrawable());
                }else {
                    img_click=0;
                    dialog_img_btn.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_48);
                    System.out.println(img_click+"/"+dialog_img_btn.getDrawable());
                }
            }
        });
        mod_bt=findViewById(R.id.mod_bt);
        mod_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    year = datePicker.getYear();
                    month = datePicker.getMonth();
                    day = datePicker.getDayOfMonth();

                    if(img_click==0){
                        img = R.drawable.ic_baseline_sentiment_very_dissatisfied_48;
                    }else if(img_click==1){
                        img= R.drawable.ic_baseline_sentiment_satisfied_48;
                    }else {
                        img=R.drawable.ic_baseline_sentiment_very_satisfied_48;
                    }

                    String str = year+"/"+month+"/"+day;
                    CalData calData1 = new CalData(year,month,day,img,"명상",str);
                    String msg = String.format("%d 월 %d 일", month+1, day);
                    System.out.println(msg);
                    listener.onFinish(position,calData1); //Listener를 통해서 person객체 전달
                    dismiss();//다이얼로그 종료
                }
            }
        });
    }

    public void setDialogListener(OnDialogListener listener) {
        this.listener = listener;
    }
}

