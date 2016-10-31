package com.example.dani.introandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewClicksActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_clicks);

        ArrayList<String> myAnimes = new ArrayList<String>();
        myAnimes.add("Fairy Tail");
        myAnimes.add("Naruto Shipuuden");
        myAnimes.add("One Piece");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myAnimes);

        ListView listView = (ListView) findViewById(R.id.lvDemoClick);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String anime = adapter.getItem(i);
                SimpleAlertDialog.displayWithOk(ListViewClicksActivity.this, anime);

                Toast.makeText(ListViewClicksActivity.this, anime, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
