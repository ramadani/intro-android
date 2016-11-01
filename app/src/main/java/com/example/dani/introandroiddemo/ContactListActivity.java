package com.example.dani.introandroiddemo;

import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        loadContacts();
        populateListView();
    }

    private void loadContacts() {
        Uri allContacts = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        CursorLoader cursorLoader = new CursorLoader(this, allContacts, null, null, null, null);

        Cursor cursor = cursorLoader.loadInBackground();

        if (cursor.moveToFirst()) {
            do {
                // get contact id
                int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                String contactID = cursor.getString(idIndex);

                // get contact name
                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String contactDisplayName = cursor.getString(nameIndex);
                names.add(contactDisplayName);

                Log.d("debug", contactID + ", " + contactDisplayName);
            } while (cursor.moveToNext());
        }
    }

    private void populateListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        ListView listView = (ListView) findViewById(R.id.lvContacts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ContactListActivity.this, names.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
