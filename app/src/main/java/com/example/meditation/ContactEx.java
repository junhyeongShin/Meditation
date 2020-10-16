package com.example.meditation;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

public class ContactEx extends AppCompatActivity {

    Button button;
    Button button2;
    Button button3;
    TextView resultView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_ex);

        resultView= findViewById(R.id.resultView);
        button=findViewById(R.id.button);
        final String[] contact_data = new String[1];

        TedPermission.with(getApplicationContext())
                     .setPermissionListener(permissionLisntener)
                     .setRationaleMessage("연락처 권한이 필요합니다")
                     .setDeniedMessage("거부하셨습니다.")
                     .setPermissions(Manifest.permission.READ_CONTACTS)
                     .check();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setData(ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 10);
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse(resultView.getText().toString()));

                startActivity(intent);
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_put = new Intent(getApplicationContext(),ContactInput.class);
                intent_put.putExtra("contact",resultView.getText().toString());
                startActivity(intent_put);

                Intent intent = new Intent(getApplicationContext(),ContactInput.class);
                startActivity(intent);
            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==10 && resultCode==RESULT_OK){
            String result = data.getDataString();
            resultView.setText(result);
        }
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
}