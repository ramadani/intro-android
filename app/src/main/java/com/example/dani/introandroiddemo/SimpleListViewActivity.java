package com.example.dani.introandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SimpleListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_view);

        String[] myStringArray = {"Laravel", "React", "Vue", "Android"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_list_view_item, myStringArray);

        ListView listView = (ListView) findViewById(R.id.lvDemo);
        listView.setAdapter(adapter);
    }
}
