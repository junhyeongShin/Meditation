package com.example.meditation.data;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.meditation.R;

public class Sign extends AppCompatActivity {

    SharedPreferences pref;
    Button btn_sign;
    EditText edit_text_name;
    EditText edit_text_id;
    EditText edit_text_pw;
    TextView text_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        btn_sign=findViewById(R.id.btn_sign);
        edit_text_name=findViewById(R.id.edit_text_name);
        edit_text_id=findViewById(R.id.edit_text_id);
        edit_text_pw=findViewById(R.id.edit_text_pw);
        text_sign = findViewById(R.id.text_sign);

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "";
                String id = "";
                String pw = "";
                name = edit_text_name.getText().toString();
                id= edit_text_id.getText().toString();
                pw = edit_text_pw.getText().toString();

                if(name.equals("")){
                    Toast.makeText(getApplicationContext(),"이름을 입력하세요",Toast.LENGTH_SHORT).show();
                }else if(id.equals("")){
                    Toast.makeText(getApplicationContext(),"ID를 입력하세요",Toast.LENGTH_SHORT).show();
                }else if(pw.equals("")){
                    Toast.makeText(getApplicationContext(),"비밀번호를 입력하세요",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();

                    SharedPreferences pref = getSharedPreferences("rebuild",MODE_PRIVATE);
                    SharedPreferences.Editor editor= pref.edit();

                    editor.putString("name",name);
                    editor.putString("id",id);
                    editor.putString("pw",pw);

                    editor.commit();

                    Log.i(pref.getString("name",""),"log");
                    Log.i(pref.getString("id",""),"log");
                    Log.i(pref.getString("pw",""),"log");

                    Sign.this.finish();
                }
            }
        });
    }
}