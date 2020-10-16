package com.example.meditation;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MusicService extends Service {
    MediaPlayer mediaPlayer;
    private int mCurrentPosition=0;
    String order;
    int mp3;
    String str = "null";

    public static final int MSG_REGISTER_CLIENT = 1;
    public static final int MSG_SEND_TO_SERVICE = 2;
    public static final int MSG_SEND_TO_ACTIVITY = 3;
    public static final int MSG_ISPLAY_ON = 1;
    public static final int MSG_ISPLAY_OFF = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(R.raw.coldblue);
        arrayList.add(R.raw.enviroment);
        arrayList.add(R.raw.etenalgarden);
        arrayList.add(R.raw.nebularfocus);
        arrayList.add(R.raw.whispering);

        mp3 = intent.getIntExtra("activity",0);
        Log.i("service get", String.valueOf(mp3));

        if(mCurrentPosition==0){
        mediaPlayer = MediaPlayer.create(this,arrayList.get(mp3));
        mediaPlayer.setLooping(false);
        mCurrentPosition++;
        }
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else {
        mediaPlayer.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCurrentPosition=0;
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
