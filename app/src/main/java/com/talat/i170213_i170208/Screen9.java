package com.talat.i170213_i170208;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Screen9 extends AppCompatActivity {

    String name;
    Integer image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_screen9);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("Name");
            image = extras.getInt("Image", -1);
        }

        TextView textView = findViewById(R.id.name);
        textView.setText(name);

        ImageView imageView = findViewById(R.id.profile_image);
        imageView.setImageResource(image);

        ImageView dialer = findViewById(R.id.phoneCall);
        dialer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen9.this, Home.class);
                startActivity(intent);
            }
        });
    }
}