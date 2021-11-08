package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ConactPage extends AppCompatActivity implements View.OnClickListener{

    EditText etName, etNumber, etWebsite, etLocation;
    ImageView ivHappy , ivMid, ivSad;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conact_page);

        Intent intent = getIntent();
        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etWebsite = findViewById(R.id.etWebsite);
        etLocation = findViewById(R.id.etLocation);
        ivHappy = findViewById(R.id.ivHappy);
        ivMid = findViewById(R.id.ivMid);
        ivSad = findViewById(R.id.ivSad);

        ivHappy.setOnClickListener(this);
        ivMid.setOnClickListener(this);
        ivSad.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(etName.getText().toString().isEmpty() || etNumber.getText().toString().isEmpty() || etWebsite.getText().toString().isEmpty()
        || etLocation.getText().toString().isEmpty()){
            Toast.makeText(this, "Plase enter all fields", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent();
            intent.putExtra("name", etName.getText().toString().trim());
            intent.putExtra("number", etNumber.getText().toString().trim());
            intent.putExtra("web", etWebsite.getText().toString().trim());

            if(view.getId() == R.id.ivHappy){
                 intent.putExtra("mood", "happy");
            }else if(view.getId() == R.id.ivMid){
                intent.putExtra("mood", "Ok");
            }else{
                intent.putExtra("mood", "Sad");
            }
            setResult(RESULT_OK, intent);
            ConactPage.this.finish();

        }
    }
}