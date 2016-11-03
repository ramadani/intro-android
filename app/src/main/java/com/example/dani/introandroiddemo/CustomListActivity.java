package com.example.dani.introandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dani.introandroiddemo.helper.User;
import com.example.dani.introandroiddemo.helper.UsersAdapter;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        populateUsersList();
    }

    private void populateUsersList() {
        // Construct the data source
        ArrayList<User> users = User.getUsers();

        // Create the adapter to convert the array to views
        UsersAdapter adapter = new UsersAdapter(this, users);

        // Attach the adapter to a Listview
        ListView listView = (ListView) findViewById(R.id.lvUsers);
        listView.setAdapter(adapter);
    }
}
