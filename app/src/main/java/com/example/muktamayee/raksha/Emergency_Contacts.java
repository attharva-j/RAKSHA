package com.example.muktamayee.raksha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Emergency_Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency__contacts);
        Button register =  findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data db = new data(getApplicationContext());
                EditText name1 = findViewById(R.id.name1);
                EditText name2 = findViewById(R.id.name2);
                EditText name3 = findViewById(R.id.name3);
                EditText name4 = findViewById(R.id.name4);
                EditText name5 = findViewById(R.id.name5);
                EditText phone1 = findViewById(R.id.phone1);
                EditText phone2 = findViewById(R.id.phone2);
                EditText phone3 = findViewById(R.id.phone3);
                EditText phone4 = findViewById(R.id.phone4);
                EditText phone5 = findViewById(R.id.phone5);
                Emer emer;
                emer = new Emer();
                emer.setName1(name1.getText().toString());
                emer.setName2(name2.getText().toString());
                emer.setName3(name3.getText().toString());
                emer.setName4(name4.getText().toString());
                emer.setName5(name5.getText().toString());
                emer.setPhone1(phone1.getText().toString());
                emer.setPhone2(phone2.getText().toString());
                emer.setPhone3(phone3.getText().toString());
                emer.setPhone4(phone4.getText().toString());
                emer.setPhone5(phone5.getText().toString());
                db.addEmer(emer);

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}

