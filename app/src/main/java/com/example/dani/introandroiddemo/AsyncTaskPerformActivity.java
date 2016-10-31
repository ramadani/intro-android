package com.example.dani.introandroiddemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AsyncTaskPerformActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_perform);

        new MyAsyncTask().execute();
    }

    public void doneCounting() {
        Toast.makeText(this, "Done Counting to 100000", Toast.LENGTH_SHORT).show();
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            for (long i = 0; i < 100000; i++) {
//                System.out.println(i);
                Log.d("DEBUG", String.valueOf(i));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            doneCounting();
        }
    }
}
