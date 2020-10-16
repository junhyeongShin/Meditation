package com.example.meditation;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class RingtonePlayingService extends Service {

    MediaPlayer mediaPlayer;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= 26) {

            PendingIntent mPendingIntent = PendingIntent.getActivity(
                    getApplicationContext(),
                    0, // 보통 default값 0을 삽입
                    new Intent(getApplicationContext(),View_Test.class),
                    PendingIntent.FLAG_UPDATE_CURRENT
            );

            String CHANNEL_ID = "default";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Meditation Alarm",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Meditation")
                    .setContentText("명상시간입니다.")
                    .setSmallIcon(R.drawable.meditation)
                    .setContentIntent(mPendingIntent)
                    .setAutoCancel(true)
                    .build();

            Log.i("알람시작","알람음이 재생됩니다.");

            startForeground(1, notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String getState = intent.getExtras().getString("state");
        Log.i("Ring / get state :",getState);

        assert getState != null;

        switch (getState) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                stopSelf();
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        // 알람음 재생 O , 알람음 시작 클릭
        if(!this.isRunning && startId == 1) {
            Log.i("알람음 O","시작 클릭");
            mediaPlayer = MediaPlayer.create(this,R.raw.sound);
            mediaPlayer.start();

            this.isRunning = true;
            this.startId = 0;
        }

        // 알람음 재생 X , 알람음 종료 버튼 클릭
        else if(this.isRunning && startId == 0) {
            Log.i("알람음 X","종료 클릭");
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();

            this.isRunning = false;
            this.startId = 0;
        }

        // 알람음 재생 X , 알람음 종료 버튼 클릭
        else if(!this.isRunning) {
            Log.i("알람음 X2","종료 클릭2");
            this.isRunning = false;
            this.startId = 0;
        }

        // 알람음 재생 O , 알람음 시작 버튼 클릭
        else {
            Log.i("알람음 O","시작 클릭");
            this.isRunning = true;
            this.startId = 1;
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestory() 실행", "서비스 파괴");
    }

}