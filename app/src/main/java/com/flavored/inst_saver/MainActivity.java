package com.flavored.inst_saver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    /**
     * the shared preference declaration
     * @param savedInstanceState
     */

    SharedPreferences downloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Implementing the splash screen implementation
         */

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                downloader = getSharedPreferences("downloader", MODE_PRIVATE);
                boolean install  = downloader.getBoolean("install", true);

                if (install){
                    /**
                     * Edit the shared preference
                     */
                    SharedPreferences.Editor editor = downloader.edit();
                    editor.putBoolean("install", false);
                    editor.commit();
                    /**
                     * We declare the intent of moving from the current activity to te next Activity
                     */
                    Intent intent = new Intent(getApplicationContext(), Downloader.class);

                    /**
                     * We call the finish method to destroy or kill the activity
                     */
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                    /**
                     * We call the startActivity method and pass to it out intent
                     */
                    startActivity(intent);
                    /**
                     * we call the finish method to destory or kill the activity
                     */
                    finish();
                }
            }
        }, 2500);
    }
}