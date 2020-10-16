package com.example.meditation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MeditationPlay extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    int mp3;
    String str = "null";

    boolean isplay;

    ImageButton btn_replay;
    ImageButton btn_forward;
    ImageButton btn_play;
    ImageButton btn_stop;
    SeekBar seekBar;
    TextView text_bar_end;

    ArrayList<Integer> arrayList = new ArrayList();

    public MeditationPlay() {
    }

    class MyThread extends Thread {
        @Override
        public void run() { // 쓰레드가 시작되면 콜백되는 메서드
            // 씨크바 막대기 조금씩 움직이기 (노래 끝날 때까지 반복)
            while(mediaPlayer.isPlaying()&& isplay) {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation_play);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isplay=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(isplay){
        mediaPlayer.stop();
        }
        if(mediaPlayer!=null){
        mediaPlayer.release();
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        isplay = false;
    }
    @Override
    protected void onResume() {
        super.onResume();

        isplay = false;

        seekBar = findViewById(R.id.seekBar);
        text_bar_end = findViewById(R.id.text_bar_end);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                int ttt = seekBar.getProgress(); // 사용자가 움직여놓은 위치
                mediaPlayer.seekTo(ttt);
                mediaPlayer.start();
                new MyThread().start();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
            }
            public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser) {
                if (seekBar.getMax()==progress) {
                    mediaPlayer.stop();
                }
            }
        });

        btn_replay = findViewById(R.id.btn_replay);
        btn_forward = findViewById(R.id.btn_forward);
        btn_play = findViewById(R.id.btn_play);
        btn_stop = findViewById(R.id.btn_stop);

        Intent intent = getIntent();
        mp3 = intent.getIntExtra("activity",0);
        Log.i("service get", String.valueOf(mp3));


            btn_play.setOnClickListener(v -> {
                if(!isplay){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.whispering);
                    mediaPlayer.setLooping(false);
                    isplay=true;
                }

                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }else {
                    mediaPlayer.start();
                }

                int a = mediaPlayer.getDuration();
                seekBar.setMax(a);
                new MyThread().start();

                int x = mediaPlayer.getDuration()/60/1000;
                int y = (mediaPlayer.getDuration()-x*60*1000)/1000;
                Log.i("getDuration : ", String.valueOf(x));
                Log.i("getDuration : ", String.valueOf(y));
                String str = x+":"+y;
                text_bar_end.setText(str);
            });

        btn_stop.setOnClickListener(v -> {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                isplay = false;
            }
        });

        btn_replay.setOnClickListener(v -> {
            if(mediaPlayer.isPlaying()){
                if(mediaPlayer.getDuration()<mediaPlayer.getCurrentPosition()-10000){
                   mediaPlayer.stop();
                   isplay = false;
                }

            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-10000);
            }

        });

        btn_forward.setOnClickListener(v -> {
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 10000);
            }
        });




    }



}