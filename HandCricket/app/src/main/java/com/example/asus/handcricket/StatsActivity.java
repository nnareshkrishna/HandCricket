package com.example.asus.handcricket;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ASUS on 17-01-2017.
 */

public class StatsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);
        TextView txtView11, txtView12, txtView13 ;
        txtView11 = (TextView)findViewById(R.id.textView11) ;
        txtView12 = (TextView)findViewById(R.id.textView12) ;
        txtView13 = (TextView)findViewById(R.id.textView13) ;

        SharedPreferences mPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE) ;
        Integer mac = mPreferences.getInt("Matches",0) ;
        Integer won = mPreferences.getInt("Won",0) ;
        Integer loss = mPreferences.getInt("Loss",0) ;

        txtView11.setText(mac+"");
        txtView12.setText(won+"");
        txtView13.setText(loss+"");

    }
}
