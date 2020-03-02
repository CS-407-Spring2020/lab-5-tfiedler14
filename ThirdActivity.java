package com.example.lab5_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.theme.MaterialComponentsViewInflater;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {
    int noteid = -1;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_notes", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        EditText editText = (EditText) findViewById(R.id.noteText);
        Intent intent = new Intent(this, Main2Activity.class);
        noteid = intent.getIntExtra("noteid", -1);

        if(noteid != -1){
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }
    }

    public void onClick(View view){

        EditText editText = (EditText) findViewById(R.id.noteText);
        Log.i("LookatMe!", editText.getText().toString());
        saveMethod(editText);
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void saveMethod(View view) {
        EditText content = (EditText) view;
        String contentText =  content.getText().toString();
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        String title;

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1){
            title = "NOTE_" + (Main2Activity.notes.size() +1);
            dbHelper.saveNotes(username, title, contentText, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, contentText, username);
        }

    }
}
