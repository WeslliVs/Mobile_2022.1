package com.example.gamelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gamelist.transactions.Game;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static int REQUEST_ADD = 1;
    public static int REQUEST_EDIT = 2;

    int selected;
    ArrayList<Game> listGames;
    ArrayAdapter adapter;
    ListView listViewGames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = -1;
        listGames = new ArrayList<Game>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listGames);
        listViewGames = (ListView) findViewById(R.id.listViewGames);
        listViewGames.setAdapter(adapter);
        listViewGames.setSelector(android.R.color.holo_blue_light);

        listViewGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(MainActivity.this, "" + listGames.get(position).toString(), Toast.LENGTH_SHORT).show();
                selected = position;
            }
        });

    }

    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected (MenuItem item){
        Toast.makeText(MainActivity.this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){
            case R.id.add:
                addItemList();
                break;
            case R.id.edit:
                editItemList();
                break;
            case R.id.delete:
                deleteItemList();
                break;
        }
        return true;
    }

    public void addItemList(){
        Intent intent = new Intent(this, GamesActivity.class);
        startActivityForResult(intent, REQUEST_ADD);
    }

    public void editItemList(){
        Intent intent = new Intent(this, GamesActivity.class);

        Game game = listGames.get(selected);
        intent.putExtra("title", game.getTitle());
        intent.putExtra("publi", game.getPubli());
        intent.putExtra("platf", game.getPlatf());

        startActivityForResult(intent, REQUEST_EDIT);
    }

    private void deleteItemList(){
        if (selected >= 0) {
            listGames.remove(selected);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Selecione uma opção", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = "Resultado: " + requestCode + " - " + resultCode;

        if (requestCode == REQUEST_ADD && resultCode == GamesActivity.RESULT_ADD){
            String title = (String) data.getExtras().get("title");
            String publi = (String) data.getExtras().get("publi");
            String platf = (String) data.getExtras().get("platf");

            Game game = new Game (title, publi, platf);

            listGames.add (game);
            adapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_EDIT && resultCode == GamesActivity.RESULT_ADD){
            String title = (String) data.getExtras().get("title");
            String publi = (String) data.getExtras().get("publi");
            String platf = (String) data.getExtras().get("platf");

            listGames.remove(selected);

            Game game = new Game();
            game.setTitle(title);
            game.setPubli(publi);
            game.setPlatf(platf);

            listGames.add (game);
            adapter.notifyDataSetChanged();
        }

        if (resultCode == GamesActivity.RESULT_ADD) {
            if (data.getExtras() != null) {
                String title = (String) data.getExtras().get("title");
                String publi = (String) data.getExtras().get("publi");
                String platf = (String) data.getExtras().get("platf");

                result += " - " + title + " - " + publi + " - " + platf;

            }
            Toast.makeText(this, "ADDED", Toast.LENGTH_SHORT).show();

        } else if (resultCode == GamesActivity.RESULT_CANCEL){
            Toast.makeText(this, "CANCELLED", Toast.LENGTH_SHORT).show();
        }

    }

}