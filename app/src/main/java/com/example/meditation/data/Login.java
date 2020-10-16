package com.example.meditation.data;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.meditation.MainActivity;
import com.example.meditation.R;

public class Login extends AppCompatActivity {

    SharedPreferences pref;
    Button button17;
    Button button16;
    EditText edit_text_id;
    EditText edit_text_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button16 = findViewById(R.id.button16);
        button17 = findViewById(R.id.button17);
        edit_text_id=findViewById(R.id.edit_text_id);
        edit_text_pw=findViewById(R.id.edit_text_pw);

        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id;
                String pw;
                id = edit_text_id.getText().toString();
                pw = edit_text_pw.getText().toString();
                Log.i("login_id : ",id);
                Log.i("login_pw : ",pw);
                boolean check = loginValidation(id,pw);
                if(check){
                   Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(intent);


                }else {
                    Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_SHORT).show();
                }
            }
        });

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Sign.class);
                startActivity(intent);

                overridePendingTransition(R.anim.in_left,R.anim.out_right);

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private boolean loginValidation(String id, String password) {
        pref = getSharedPreferences("rebuild",MODE_PRIVATE);
        Log.i("pref id : ",pref.getString("id",""));
        Log.i("pref pw : ",pref.getString("pw",""));

        if(pref.getString("id","").equals(id) && pref.getString("pw","").equals(password)) {
            // login success
            return true;
        } else if (pref.getString("id", "") == null){
            // sign in first
            Toast.makeText(getApplicationContext(), "없는 ID입니다. 회원가입부터 하시기 바랍니다.", Toast.LENGTH_LONG).show();
            return false;
        } else {
            // login failed
            return false;
        }
    }


}