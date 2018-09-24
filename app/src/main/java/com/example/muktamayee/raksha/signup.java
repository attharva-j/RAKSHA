package com.example.muktamayee.raksha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button next =  findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data db = new data(getApplicationContext());
                EditText username = findViewById(R.id.username);
                EditText email = findViewById(R.id.email);
                EditText setpassword = findViewById(R.id.setpassword);
                EditText add = findViewById(R.id.add);
                EditText phonenumber = findViewById(R.id.phonenumber);
                User u;
                u = new User();
                u.setName(username.getText().toString());
                u.setEmail(email.getText().toString());
                u.setPassword(setpassword.getText().toString());
                u.setAddress(add.getText().toString());
                u.setPhonenumber(phonenumber.getText().toString());
                db.addUser(u);
                Intent i=new Intent(getApplicationContext(),Emergency_Contacts.class);
                startActivity(i);
            }
        });
    }
}
