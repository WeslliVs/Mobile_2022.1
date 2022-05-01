package com.example.componentesbasicos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.view.View;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends AppCompatActivity {

    MediaPlayer startUp;
    Spinner spnr;
    EditText eText;
    Button btn;
    Button buttonpopup;

    @SuppressLint("CutPasteId")
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ToggleButton tb1 = (ToggleButton) findViewById(R.id.toggle1);
        eText = (EditText) findViewById(R.id.edittext1);
        btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String str = eText.getText().toString();
                Toast msg = Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG);
                msg.show();
            }
        });

        String str[] = {"Católica", "Cisne", "IFCE", "Outro(a)", "UFC"};
        String cursos[] = {"CC", "DD", "EC", "ES", "RC", "SI"};

        ArrayAdapter<String> cursosAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cursos);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(cursosAdapter);

        AutoCompleteTextView t1 = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView1);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, str);

        t1.setThreshold(1);
        t1.setAdapter(adp);

        spnr = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, str);

        spnr.setAdapter(adapter);
        spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int position = spnr.getSelectedItemPosition();

                Toast.makeText(getApplicationContext(),"Você escolheu "+str[+position],Toast.LENGTH_LONG).show();
                // TODO Auto-generated method stub
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        buttonpopup = (Button) findViewById(R.id.buttonpopup);
        buttonpopup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, buttonpopup);
                popup.getMenuInflater().inflate(R.menu.pop_up_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(MainActivity.this, "Você escolheu: " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });

        startUp = MediaPlayer.create(this, R.raw.startup);
    }

    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu(LayoutInflater layout) {
        getMenuInflater().inflate(R.layout.activity_main, (Menu) layout);
        return true;
    }

    public void onCheckedChanged(RadioButton radioButton1, RadioButton radioButton2, int checkedId1, int checkedId2) {
        RadioButton rb1 = (RadioButton) findViewById(checkedId1);
        RadioButton rb2 = (RadioButton) findViewById(checkedId2);
        if (null != rb1 && checkedId1 > -1) {
            Toast.makeText(MainActivity.this, rb1.getText(), Toast.LENGTH_SHORT).show();
        }
        if (null != rb2 && checkedId2 > -1) {
            Toast.makeText(MainActivity.this, rb2.getText(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void playMusic(View view) {
        startUp.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        startUp.release();
    }

}