package com.talat.i170213_i170208;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Screen3 extends AppCompatActivity {
    EditText field1, field2, field3;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_screen3);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        field1 = findViewById(R.id.email);
        field2 = findViewById(R.id.password);
        field3 = findViewById(R.id.confirmpassword);

        TextView login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen3.this, Screen2.class);
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.signupBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = field1.getText().toString();
                String password = field2.getText().toString();
                String confirmPassword = field3.getText().toString();

                if (!email.equals("") && !password.equals("") && !confirmPassword.equals("")) {
                    if (password.equals(confirmPassword)) {
                        reference.push().setValue(new User(email, password));
                        Intent intent = new Intent(Screen3.this, Screen2.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Screen3.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Screen3.this, "Please fill out all fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}