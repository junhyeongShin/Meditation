package com.example.meditation;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class Camera extends AppCompatActivity {
    ImageView image;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btn = findViewById(R.id.btn);
        image = findViewById(R.id.image);
        image.setClipToOutline(true);

        //권한설정
        TedPermission.with(getApplicationContext())
                     .setPermissionListener(permissionLisntener)
                     .setRationaleMessage("카메라 권한이 필요합니다")
                     .setDeniedMessage("거부하셨습니다.")
                     .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                     .setPermissions(Manifest.permission.CAMERA)
                     .check();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCameraBtn(v);
            }
        });
    }

    PermissionListener permissionLisntener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(getApplicationContext(),"권환이 허용됨.",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            Toast.makeText(getApplicationContext(),"권환이 거부됨.",Toast.LENGTH_SHORT).show();

        }

    };

    public void showCameraBtn(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK&&requestCode == 10){
            Bitmap bitmap = data.getParcelableExtra("data");
            image.setImageBitmap(bitmap);

            SharedPreferences sharedPreferences = getSharedPreferences("rebuild",MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit();



        }
    }
}