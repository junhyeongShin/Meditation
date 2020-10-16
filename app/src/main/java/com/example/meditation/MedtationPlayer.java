package com.example.meditation;

import android.content.Intent;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MedtationPlayer extends AppCompatActivity {

    ImageButton btn_replay_10;
    ImageButton btn_forward_10;
    ImageButton btn_heart_player;
    ImageButton btn_back_play;
    ImageButton btn_back_stop;
    ImageView img_profile_player;
    TextView title_profile_player;
    TextView text_profile_player;
    TextView introduce_profile_player;
    SeekBar seekBar;


//    private Messenger mServiceMessenger = null;
//    private boolean mIsBound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent getIntent = getIntent();
        final int a = getIntent.getIntExtra("contents ID",99);
        Log.i("contents ID", String.valueOf(a));


        setContentView(R.layout.activity_medtation_player);

        title_profile_player=findViewById(R.id.title_profile_player);
        text_profile_player=findViewById(R.id.text_profile_player);
        introduce_profile_player=findViewById(R.id.introduce_profile_player);

        img_profile_player=findViewById(R.id.img_profile_player);

        btn_heart_player= findViewById(R.id.btn_heart);
        btn_forward_10 = findViewById(R.id.btn_forward);
        btn_replay_10 = findViewById(R.id.btn_replay);
        btn_back_play = findViewById(R.id.btn_play);
        btn_back_stop = findViewById(R.id.btn_stop);

//        seekBar = findViewById(R.id.seekbar);

        btn_back_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MusicService.class);
                intent.putExtra("activity",a);
                startService(intent);
//                bindService(new Intent(getApplicationContext(), MusicService.class), mConnection, Context.BIND_AUTO_CREATE);
            }
        });

        btn_back_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(),MusicService.class));

            }
        });

        btn_forward_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_replay_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStart();

    }

}

