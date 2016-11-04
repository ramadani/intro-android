package com.example.dani.introandroiddemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dani.introandroiddemo.book.Book;
import com.example.dani.introandroiddemo.book.BookClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class BookDetailActivity extends AppCompatActivity {
    private ImageView ivBookCover;
    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvPublisher;
    private TextView tvPageCount;
    private BookClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // fetch views
        ivBookCover = (ImageView) findViewById(R.id.ivBookCoverDetail);
        tvTitle = (TextView) findViewById(R.id.tvBookTitleDetail);
        tvAuthor = (TextView) findViewById(R.id.tvBookAuthorDetail);
        tvPublisher = (TextView) findViewById(R.id.tvBookPublisherDetail);
        tvPageCount = (TextView) findViewById(R.id.tvPageCountDetail);

        // use the book to populate the data into our views
        Book book = (Book) getIntent().getSerializableExtra(BookListActivity.BOOK_DETAIL_KEY);
        loadBook(book);
    }

    // populate data for the book
    private void loadBook(Book book) {
        // change activity title
        this.setTitle(book.getTitle());

        // populate data
        Picasso.with(this).load(Uri.parse(book.getLargeCoverUrl())).error(R.drawable.ic_nocover).into(ivBookCover);
        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());

        // fetch extra book data from books api
        client = new BookClient();
        client.getExtraBookDetails(book.getOpenLibraryId(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.has("publishers")) {
                        // display comma separated list of publishers
                        final JSONArray publisher = response.getJSONArray("publishers");
                        final int numPublishers = publisher.length();
                        final String[] publishers = new String[numPublishers];

                        for (int i = 0; i < numPublishers; i++) {
                            publishers[i] = publisher.getString(i);
                        }

                        tvPublisher.setText(TextUtils.join(", ", publishers));
                    }

                    if (response.has("number_of_pages")) {
                        String numOfPages = Integer.toString(response.getInt("number_of_pages")) + " pages";
                        tvPageCount.setText(numOfPages);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_share) {
            setShareIntent();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setShareIntent() {
        ImageView ivCover = (ImageView) findViewById(R.id.ivBookCoverDetail);
        final TextView tvTitle = (TextView) findViewById(R.id.tvBookTitleDetail);

        // Get access to the URI for the bitmap
        Uri bmpUri = getLocalBitmapUri(ivCover);

        // Construct a ShareIntent with link to image
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("*/*");
        shareIntent.putExtra(Intent.EXTRA_TEXT, (String) tvTitle.getText());
        shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);

        // launch share menu
        startActivity(Intent.createChooser(shareIntent, "Share Book"));
    }

    // Returns the URI path to the Bitmap displayed in cover imageview
    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();

        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }

        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.close();

            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmpUri;
    }
}
