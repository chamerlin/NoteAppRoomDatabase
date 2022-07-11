package com.example.noteappdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.HashSet;

public class AddNoteActivity extends AppCompatActivity {

    private EditText et_title;
    private EditText et_description;

    public static final String EXTRA_ID = "com.example.noteappdatabase.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.noteappdatabase.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.noteappdatabase.EXTRA_DESCRIPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            et_title.setText(intent.getStringExtra(EXTRA_TITLE));
            et_description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        } else {
            setTitle("Add Note");
        }
    }

    public void onClickBack(View view){
        Intent intent = new Intent(this, NotesFragment.class);
        startActivity(intent);
    }

    public void saveNote(View view) {
        String title = et_title.getText().toString();
        String description = et_description.getText().toString();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}