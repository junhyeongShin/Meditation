package com.example.meditation;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ContactInput extends AppCompatActivity {
    Button btn_input;
    Button btn_edit;
    EditText phone;
    EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_input);

        btn_edit=findViewById(R.id.btn_edit);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        final String contact = getIntent().getStringExtra("contact");
        Log.i("contact",contact);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editContact(Uri.parse(contact),email.getText().toString(),phone.getText().toString());
            }
        });

    }


    public void editContact(Uri contactUri, String email, String phone) {
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setData(contactUri);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
        startActivity(intent);
    }
}