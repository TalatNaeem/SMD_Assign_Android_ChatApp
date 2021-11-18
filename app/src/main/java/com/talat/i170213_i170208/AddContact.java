package com.talat.i170213_i170208;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_add_contact);

        EditText text1 = findViewById(R.id.name);
        EditText text2 = findViewById(R.id.number);

        Button btn = findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = text1.getText().toString();
                String number = text2.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("contacts");
                String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                reference.push().setValue(new Contact(R.drawable.no_dp, currentTime, "This is a sample message", name, number));
                Intent intent = new Intent(AddContact.this, Home.class);
                startActivity(intent);
            }
        });
    }
}