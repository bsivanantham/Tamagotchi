package com.socialrobotics.tamgotchi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class NAO extends AppCompatActivity {
    private String TAG = this.getClass().getName();

    public Intent startActivity(final Class activity , final Bundle bundle, Context context){
        Intent intent = new Intent(context,activity);
        intent.putExtra("bundle",bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return intent;
    }
}
