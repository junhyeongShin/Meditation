package com.example.meditation;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.meditation.data.Login;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_play;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play=findViewById(R.id.btn_play);



        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),View_Test.class);
                startActivity(intent);

                overridePendingTransition(R.anim.in_left,R.anim.out_right);
            }
        });


        button = findViewById(R.id.button); //알람설정
        button2 = findViewById(R.id.button2); //카메라
        button3 = findViewById(R.id.button3); //연락처
        button4 = findViewById(R.id.button4); //캘린더 일정추가
        button5 = findViewById(R.id.button5);
//        button6 = findViewById(R.id.button6);
//        button7 = findViewById(R.id.button7);
//        button8 = findViewById(R.id.button8);
//        button9 = findViewById(R.id.button9);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                startActivity(intent);

                overridePendingTransition(R.anim.in_left,R.anim.out_right);

            }
        }); //알람설정

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Camera.class);
                startActivity(intent);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactEx.class);
                startActivity(intent);

                overridePendingTransition(R.anim.in_left,R.anim.out_right);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InputActivity.class);
                startActivity(intent);

                overridePendingTransition(R.anim.in_left,R.anim.out_right);

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MeditationPlay.class);
                startActivity(intent);

                overridePendingTransition(R.anim.in_left,R.anim.out_right);

            }
        });
//        button6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),NextActivity.class);
//                startActivity(intent);
//
//                overridePendingTransition(R.anim.in_left,R.anim.out_right);
//
//            }
//        });
//        button7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),NextActivity.class);
//                startActivity(intent);
//
//                overridePendingTransition(R.anim.in_left,R.anim.out_right);
//
//            }
//        });
//        button8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),NextActivity.class);
//                startActivity(intent);
//
//                overridePendingTransition(R.anim.in_left,R.anim.out_right);
//
//            }
//        });
//        button9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),NextActivity.class);
//                startActivity(intent);
//
//                overridePendingTransition(R.anim.in_left,R.anim.out_right);
//
//            }
//        });
    }
//    PermissionListener permissionLisntener = new PermissionListener() {
//        @Override
//        public void onPermissionGranted() {
//            Toast.makeText(getApplicationContext(),"권환이 허용됨.",Toast.LENGTH_SHORT).show();
//        }
//        @Override
//        public void onPermissionDenied(List<String> deniedPermissions) {
//            Toast.makeText(getApplicationContext(),"권환이 거부됨.",Toast.LENGTH_SHORT).show();
//        }
//    };
}