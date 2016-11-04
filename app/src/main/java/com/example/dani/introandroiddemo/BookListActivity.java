package com.example.dani.introandroiddemo;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.dani.introandroiddemo.book.Book;
import com.example.dani.introandroiddemo.book.BookAdapter;
import com.example.dani.introandroiddemo.book.BookClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class BookListActivity extends AppCompatActivity {
    public static final String BOOK_DETAIL_KEY = "com.example.dani.introandroiddemo.BOOK_DETAIL";

    private ListView lvBooks;
    private ProgressBar progressBar;
    private BookAdapter bookAdapter;

    private BookClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        lvBooks = (ListView) findViewById(R.id.lvBooks);
        progressBar = (ProgressBar) findViewById(R.id.pbBooks);

        ArrayList<Book> books = new ArrayList<Book>();
        bookAdapter = new BookAdapter(this, books);
        lvBooks.setAdapter(bookAdapter);

        setupBookSelectedListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_list, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Fetch the data remotely
                fetchBooks(query);

                // Reset SearchView
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);

                searchItem.collapseActionView();

                // Set activity title to search query
                BookListActivity.this.setTitle(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    // Executes an API call to the OpenLibrary search endpoint, parses the results
    // Converts them into an array of book objects and adds them to the adapter
    private void fetchBooks(String query) {
        // Show progress bar before making network request
        progressBar.setVisibility(ProgressBar.VISIBLE);

        client = new BookClient();

        client.getBooks(query, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray docs = null;

                    if (response != null) {
                        // Get the docs json array
                        docs = response.getJSONArray("docs");

                        // Parse json array into array of model objects
                        final ArrayList<Book> books = Book.fromJson(docs);

                        // Remove all books from the adapter
                        bookAdapter.clear();

                        // Load model objects into the adapter
                        for (Book book : books) {
                            bookAdapter.add(book);
                        }

                        bookAdapter.notifyDataSetChanged();
                    }

                    // hide progress bar
                    progressBar.setVisibility(ProgressBar.GONE);
                } catch (JSONException e) {
                    // Invalid JSON format, show appropriate error.
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progressBar.setVisibility(ProgressBar.GONE);
            }
        });
    }

    public void setupBookSelectedListener() {
        lvBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("DEBUG", "position " + position);
                // Launch the detail view passing book as an extra
                Intent intent = new Intent(BookListActivity.this, BookDetailActivity.class);
                intent.putExtra(BOOK_DETAIL_KEY, bookAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }
}
