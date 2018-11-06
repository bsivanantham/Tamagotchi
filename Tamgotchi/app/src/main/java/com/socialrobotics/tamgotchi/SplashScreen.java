package com.socialrobotics.tamgotchi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


/**
 * Created by Balavivek on 21-June-2018.
 */

public class SplashScreen extends NAO {
    Handler handler;
    private Button next;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this.getApplicationContext();
        setContentView(R.layout.splash);
        this.next = findViewById(R.id.button);
        this.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.class,null,context);

            }
        });
    }
}
