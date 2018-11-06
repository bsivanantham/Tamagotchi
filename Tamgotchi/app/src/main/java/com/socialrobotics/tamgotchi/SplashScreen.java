package com.socialrobotics.tamgotchi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


/**
 * Created by Balavivek on 21-June-2018.
 */

public class SplashScreen extends Activity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

       /* this.handler = new Handler();
        this.handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);*/

    }
}
