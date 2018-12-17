package com.socialrobotics.tamgotchi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends NAO {

    private String TAG = "MainActivity";
    private DatabaseReference myRef;
    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference battery_value;
    private DatabaseReference chatting;
    private DatabaseReference playing;
    private FirebaseDatabase mFirebaseInstance;
    private Button reset;
    private ArcProgress energyMeter;
    private ArcProgress foodMeter;
    private ArcProgress happinessMeter;
    private ArcProgress healthMeter;
    private DatabaseReference factor;
    private Boolean flag= false;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.energyMeter = findViewById(R.id.energyMeter);
        this.foodMeter = findViewById(R.id.foodMeter);
        this.happinessMeter = findViewById(R.id.happinessMeter);
        this.healthMeter = findViewById(R.id.healthMeter);
        this.reset = findViewById(R.id.reset);
        this.textView = findViewById(R.id.textView);

        setEnergyMeterLevel(100);
        setFoodMeterLevel(100);
        setHappinessMeterLevel(100);
        setHealthMeterLevel(100);

        textView.setText(new StringBuilder().append("Oncreate:").append("Energy:").append(getEnergyMeterLevel()).append("foodMeter:").append(getFoodMeterLevel()).append("happinessMeter:").append(getHappinessMeterLevel()).append("healthMeter:").append(getHealthMeterLevel()).toString());
        mFirebaseInstance = FirebaseDatabase.getInstance();

        this.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //foodMeter.setProgress(100);
                mFirebaseDatabase = mFirebaseInstance.getReference("agents").child("Lana").child("battery_value");
                mFirebaseDatabase.setValue("100");

                Thread thread = new Thread(){
                    public void run(){
                        setHappinessMeterLevel(100);
                        setEnergyMeterLevel(100);
                        setFoodMeterLevel(100);
                        setHealthMeterLevel(100);
                    }
                };

                thread.start();

                factor.child("happinessfactor").setValue("NORMAL");
                factor.child("healthfactor").setValue("NORMAL");
                factor.child("foodfactor").setValue("NORMAL");
                factor.child("energyfactor").setValue("NORMAL");
                textView.setText(new StringBuilder().append("reset:").append("Energy:").append(getEnergyMeterLevel()).append("foodMeter:").append(getFoodMeterLevel()).append("happinessMeter:").append(getHappinessMeterLevel()).append("healthMeter:").append(getHealthMeterLevel()).toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        energyMeter.setProgress(getEnergyMeterLevel());
                        foodMeter.setProgress(getFoodMeterLevel());
                        happinessMeter.setProgress(getHappinessMeterLevel());
                        healthMeter.setProgress(getHealthMeterLevel());
                    }
                });
            }
        });

        // get reference to 'users' node
        this.battery_value = mFirebaseInstance.getReference("agents").child("Lana").child("battery_value");
        this.chatting = mFirebaseInstance.getReference("agents").child("Lana").child("chatting");
        this.playing = mFirebaseInstance.getReference("agents").child("Lana").child("playing");
        this.mFirebaseDatabase = mFirebaseInstance.getReference("agents").child("Lana").child("battery_value");
        this.factor = mFirebaseInstance.getReference("agents").child("Lana");

        setBatteryValue("10");
        this.battery_value.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setBatteryValue(dataSnapshot.getValue(String.class));
                //energyMeter.setProgress(Integer.parseInt(dataSnapshot.getValue(String.class)));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        energyMeter.setProgress(getEnergyMeterLevel());
                        foodMeter.setProgress(getFoodMeterLevel());
                        happinessMeter.setProgress(getHappinessMeterLevel());
                        healthMeter.setProgress(getHealthMeterLevel());
                    }
                });

                adapter(textView);
                updateDatabase();
                textView.setText(new StringBuilder().append("battery_value:").append("Energy:").append(getEnergyMeterLevel()).append("foodMeter:").append(getFoodMeterLevel()).append("happinessMeter:").append(getHappinessMeterLevel()).append("healthMeter:").append(getHealthMeterLevel()).toString());

                factor.child("happinessfactor").setValue(getHappinessFactor());
                factor.child("healthfactor").setValue(getHealthFactor());
                factor.child("foodfactor").setValue(getFoodFactor());
                factor.child("energyfactor").setValue(getEnergyFactor());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        setChattingBool("True");
        this.chatting.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setChattingBool(dataSnapshot.getValue(String.class));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        energyMeter.setProgress(getEnergyMeterLevel());
                        foodMeter.setProgress(getFoodMeterLevel());
                        happinessMeter.setProgress(getHappinessMeterLevel());
                        healthMeter.setProgress(getHealthMeterLevel());
                    }
                });

                adapter(textView);
                updateDatabase();
                textView.setText(new StringBuilder().append("chatting:").append("Energy:").append(getEnergyMeterLevel()).append("foodMeter:").append(getFoodMeterLevel()).append("happinessMeter:").append(getHappinessMeterLevel()).append("healthMeter:").append(getHealthMeterLevel()).toString());

                factor.child("happinessfactor").setValue(getHappinessFactor());
                factor.child("healthfactor").setValue(getHealthFactor());
                factor.child("foodfactor").setValue(getFoodFactor());
                factor.child("energyfactor").setValue(getEnergyFactor());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

        setPlayingBool("True");
        this.playing.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setPlayingBool(dataSnapshot.getValue(String.class));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        energyMeter.setProgress(getEnergyMeterLevel());
                        foodMeter.setProgress(getFoodMeterLevel());
                        happinessMeter.setProgress(getHappinessMeterLevel());
                        healthMeter.setProgress(getHealthMeterLevel());
                    }
                });
                textView.setText(new StringBuilder().append("playing:").append("Energy:").append(getEnergyMeterLevel()).append("foodMeter:").append(getFoodMeterLevel()).append("happinessMeter:").append(getHappinessMeterLevel()).append("healthMeter:").append(getHealthMeterLevel()).toString());

                adapter(textView);
                updateDatabase();
                factor.child("happinessfactor").setValue(getHappinessFactor());
                factor.child("healthfactor").setValue(getHealthFactor());
                factor.child("foodfactor").setValue(getFoodFactor());
                factor.child("energyfactor").setValue(getEnergyFactor());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



    }

    @Override
    public void onStart(){
        super.onStart();

        setEnergyMeterLevel(this.energyMeter.getProgress());
        setFoodMeterLevel(this.foodMeter.getProgress());
        setHappinessMeterLevel(this.happinessMeter.getProgress());
        setHealthMeterLevel(this.healthMeter.getProgress());

            adapter(textView);
            updateDatabase();
            factor.child("happinessfactor").setValue(getHappinessFactor());
            factor.child("healthfactor").setValue(getHealthFactor());
            factor.child("foodfactor").setValue(getFoodFactor());
            factor.child("energyfactor").setValue(getEnergyFactor());
    }
}