package com.example.lab5_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_notes", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString(usernameKey, "").equals("")){
            String username = sharedPreferences.getString(usernameKey,"");
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_main);
        }

    }

    public void clickFuntion(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("username", username.getText().toString());
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_notes", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", username.getText().toString()).apply();
        startActivity(intent);
    }
}
