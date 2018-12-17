package com.socialrobotics.tamgotchi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public abstract class NAO extends AppCompatActivity {
    private String TAG = this.getClass().getName();
    private String batteryValue;
    private String playingBool;
    private String chattingBool;
    private int energyMeterLevel;
    private int happinessMeterLevel;
    private int foodMeterLevel;
    private int healthMeterLevel;
    private String happinessFactor;
    private String foodFactor;
    private String healthFactor;
    private String energyFactor;
    private long millis = System.currentTimeMillis();


    public Intent startActivity(final Class activity , final Bundle bundle, Context context){
        Intent intent = new Intent(context,activity);
        intent.putExtra("bundle",bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return intent;
    }

    public String getBatteryValue() {
        return batteryValue;
    }

    public void setBatteryValue(String batteryValue) {
        this.batteryValue = batteryValue;
    }

    public String getPlayingBool() {
        return playingBool;
    }

    public void setPlayingBool(String playingBool) {
        this.playingBool = playingBool;
    }

    public String getChattingBool() {
        return chattingBool;
    }

    public void setChattingBool(String chattingBool) {
        this.chattingBool = chattingBool;
    }


    public void energy(TextView textView){
        textView.setText(new StringBuilder().append("energy:").append("Energy:").append(getEnergyMeterLevel()).append("foodMeter:").append(getFoodMeterLevel()).append("happinessMeter:").append(getHappinessMeterLevel()).append("healthMeter:").append(getHealthMeterLevel()).toString());
        int count = getEnergyMeterLevel();
        if(count > 0 && count < 100) {
            if ((getChattingBool().equalsIgnoreCase("True")) || (getPlayingBool().equalsIgnoreCase("True"))) {
                count = count - 2;
            } else if ((getPlayingBool().equalsIgnoreCase("False"))) {
                count = count ;
            }else {
                count = count - 2;
            }
        }
        else if(count == 100){
            count = count - 2;
        }
        setEnergyMeterLevel(count);
    }

    public void happiness(TextView textView) {
        //textView.setText(new StringBuilder().append("happiness:").append("Energy:").append(getEnergyMeterLevel()).append("foodMeter:").append(getFoodMeterLevel()).append("happinessMeter:").append(getHappinessMeterLevel()).append("healthMeter:").append(getHealthMeterLevel()).toString());

        int count = getHappinessMeterLevel();
        if(count > 0 && count < 100) {
            if ((getChattingBool().equalsIgnoreCase("True")) || (getPlayingBool().equalsIgnoreCase("True"))) {
                count = count + 2;
            } else {
                count = count - 2;
            }
        }
        else if(count == 100){
            count = count - 2;
        }
        setHappinessMeterLevel(count);
    }

    public void food(TextView textView){

        //textView.setText(new StringBuilder().append("food:").append("Energy:").append(getEnergyMeterLevel()).append("foodMeter:").append(getFoodMeterLevel()).append("happinessMeter:").append(getHappinessMeterLevel()).append("healthMeter:").append(getHealthMeterLevel()).toString());
        setFoodMeterLevel(Integer.parseInt(getBatteryValue()));
    }

    public void health(TextView textView){
        textView.setText(new StringBuilder().append("health:").append("Energy:").append(getEnergyMeterLevel()).append("foodMeter:").append(getFoodMeterLevel()).append("happinessMeter:").append(getHappinessMeterLevel()).append("healthMeter:").append(getHealthMeterLevel()).toString());
        int count = getHealthMeterLevel();
        if(count > 0 && count < 100) {
            if (getPlayingBool().equalsIgnoreCase("True")) {
                count = count + 2;
            } else if (getChattingBool().equalsIgnoreCase("True")) {
                count = count - 2;
            }else {
                count = count - 2;
            }
        }
        else if(count == 100){
            count = count - 2;
        }
        setHealthMeterLevel(count);
    }

    public void adapter(TextView textView){
        energy(textView);
        happiness(textView);
        health(textView);
        food(textView);
    }


/*    public void reset(int count){
        setEnergyMeterLevel(count);
        setFoodMeterLevel(count);
        setHappinessMeterLevel(count);
        setHealthMeterLevel(count);
    }*/

    public void updateDatabase(){
        if(getHappinessMeterLevel()<25 )
            setHappinessFactor("VERYSAD");
        else if(getHappinessMeterLevel()<50 && getHappinessMeterLevel()>25)
            setHappinessFactor("SAD");
        else if(getHappinessMeterLevel()>50)
            setHappinessFactor("NORMAL");

        if(getHealthMeterLevel()<25 )
            setHealthFactor("SICK");
        else if(getHealthMeterLevel()<50 && getHealthMeterLevel()>25)
            setHealthFactor("UNHEALTHY");
        else if(getHealthMeterLevel()>50)
            setHealthFactor("NORMAL");

        if(getFoodMeterLevel()<25 )
            setFoodFactor("VERYHUNGRY");
        else if(getFoodMeterLevel()<50 && getFoodMeterLevel()>25)
            setFoodFactor("HUNGRY");
        else if(getFoodMeterLevel()>50)
            setFoodFactor("NORMAL");

        if(getEnergyMeterLevel()<5 )
            setEnergyFactor("SLEEP");
        else if(getEnergyMeterLevel()<25 && getEnergyMeterLevel()>5)
            setEnergyFactor("UNHEALTHY");
        else if(getEnergyMeterLevel()>25)
            setEnergyFactor("NORMAL");

    }

    public int getEnergyMeterLevel() {
        return energyMeterLevel;
    }

    public void setEnergyMeterLevel(int energyMeterLevel) {
        this.energyMeterLevel = energyMeterLevel;
    }

    public int getHappinessMeterLevel() {
        return happinessMeterLevel;
    }

    public void setHappinessMeterLevel(int happinessMeterLevel) {
        this.happinessMeterLevel = happinessMeterLevel;
    }

    public int getFoodMeterLevel() {
        return foodMeterLevel;
    }

    public void setFoodMeterLevel(int foodMeterLevel) {
        this.foodMeterLevel = foodMeterLevel;
    }

    public int getHealthMeterLevel() {
        return healthMeterLevel;
    }

    public void setHealthMeterLevel(int healthMeterLevel) {
        this.healthMeterLevel = healthMeterLevel;
    }

    public String getHappinessFactor() {
        return happinessFactor;
    }

    public void setHappinessFactor(String happinessFactor) {
        this.happinessFactor = happinessFactor;
    }

    public String getFoodFactor() {
        return foodFactor;
    }

    public void setFoodFactor(String foodFactor) {
        this.foodFactor = foodFactor;
    }

    public String getHealthFactor() {
        return healthFactor;
    }

    public void setHealthFactor(String healthFactor) {
        this.healthFactor = healthFactor;
    }

    public String getEnergyFactor() {
        return energyFactor;
    }

    public void setEnergyFactor(String energyFactor) {
        this.energyFactor = energyFactor;
    }
}
