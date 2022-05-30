package com.example.gamelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gamelist.transactions.Game;

public class GamesActivity extends AppCompatActivity {

    public static int RESULT_ADD = 1;
    public static int RESULT_CANCEL = 2;

    EditText edtTitle;
    EditText edtPubli;
    EditText edtPlatf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        edtTitle = findViewById(R.id.editTextTitle);
        edtPubli = findViewById(R.id.editTextPubli);
        edtPlatf = findViewById(R.id.editTextPlatf);

        if (getIntent().getExtras() != null){
            String title = (String) getIntent().getExtras().get("title");
            String publi = (String) getIntent().getExtras().get("publi");
            String platf = (String) getIntent().getExtras().get("platf");

            edtTitle.setText(title);
            edtPubli.setText(publi);
            edtPlatf.setText(platf);

        }
    }

    public void clickAdd (View view){
        Intent intent = new Intent();

        String title = edtTitle.getText().toString();
        String publi = edtPubli.getText().toString();
        String platf = edtPlatf.getText().toString();

        intent.putExtra("title", title);
        intent.putExtra("publi", publi);
        intent.putExtra("platf", platf);

        setResult(RESULT_ADD, intent);
        finish();
    }

    public void clickCancel (View view){
        setResult(RESULT_CANCEL);
        finish();
    }

}