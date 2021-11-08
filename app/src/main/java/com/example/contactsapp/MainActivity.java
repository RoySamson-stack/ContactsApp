package com.example.contactsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.midi.MidiDeviceService;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCreate;
    ImageView ivEmoji, ivPhone, ivWeb, ivLocation;
    final int CREATE_CONTACT = 1;
    String name = " ", number = " ", web = "", location = "", mood = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreate);
        ivEmoji = findViewById(R.id.ivEmoji);
        ivPhone = findViewById(R.id.ivPhone);
        ivWeb = findViewById(R.id.ivWeb);
        ivLocation = findViewById(R.id.ivLocation);


        ivEmoji.setVisibility(View.GONE);
        ivPhone.setVisibility(View.GONE);
        ivWeb.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConactPage.class);
                startActivityForResult(intent, CREATE_CONTACT);
            }
        });
        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
            startActivity(intent);
            }
        });
        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + web));
            startActivity(intent);
            }
        });
        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0, 0?q=" + location));
            startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if(requestCode == CREATE_CONTACT){
            if(resultCode == RESULT_OK){
                ivEmoji.setVisibility(View.VISIBLE);
                ivPhone.setVisibility(View.VISIBLE);
                ivWeb.setVisibility(View.VISIBLE);
                ivLocation.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                number = data.getStringExtra("number");
                web = data.getStringExtra("web");
                location = data.getStringExtra("location");
                mood = data.getStringExtra("mood");

                if(mood.equals("happy")){
                    ivEmoji.setImageResource(R.drawable.happy);
                }else if(mood.equals("sad")){
                    ivEmoji.setImageResource(R.drawable.sad);
                }else{
                    ivEmoji.setImageResource(R.drawable.middle);
                }
            }
            else{
                Toast.makeText(this, "Np data passed through", Toast.LENGTH_SHORT).show();
            }
         }
    }
}