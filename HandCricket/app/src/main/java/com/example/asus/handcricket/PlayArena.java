package com.example.asus.handcricket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


/**
 * Created by ASUS on 17-01-2017.
 */

public class PlayArena extends Activity {
    boolean isUser ;
    Integer target,current_score,uScore ;

    Button btn1,btn2,btn3,btn4,btn5,btn6 ;
    TextView txtView3,txtView8,txtView4,txtView9,txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        btn1 = (Button)findViewById(R.id.button3) ;
        btn2 = (Button)findViewById(R.id.button4) ;
        btn3 = (Button)findViewById(R.id.button5) ;
        btn4 = (Button)findViewById(R.id.button6) ;
        btn5 = (Button)findViewById(R.id.button7) ;
        btn6 = (Button)findViewById(R.id.button8) ;
        txtView3 = (TextView)findViewById(R.id.textView3) ;
        txtView8 = (TextView)findViewById(R.id.textView8) ;
        txtView4 = (TextView)findViewById(R.id.textView4) ;
        txtView9 = (TextView)findViewById(R.id.textView9) ;
        txtView = (TextView)findViewById(R.id.textView) ;
        isUser = true ;
        target = 0 ;
        current_score = 0 ;
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Button",1+"") ;
                uScore = 1 ;
                validate() ;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uScore = 2 ;
                validate() ;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uScore = 3 ;
                validate() ;
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uScore = 4 ;
                validate() ;
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uScore = 5 ;
                validate() ;
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uScore = 6 ;
                validate() ;
            }
        });
    }

    public void validate(){
        if(isUser){
            Integer cScore ;
            Random rand = new Random();
            cScore = rand.nextInt(6)+1;
            txtView3.setText(uScore.toString());
            txtView8.setText(cScore.toString());
            if(cScore == uScore){
                userOut() ;
            }
            current_score += uScore ;
            txtView4.setText("Score:"+current_score);

        }
        else{
            Integer cScore ;
            Random rand = new Random();
            cScore = rand.nextInt(6)+1;
            txtView3.setText(uScore.toString());
            txtView8.setText(cScore.toString());
            if(cScore == uScore){
                computerOut();
            }
            current_score += cScore ;
            if(current_score >= target){
                SharedPreferences mPreference = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE) ;
                Integer matc = mPreference.getInt("Matches",0) ;
                Integer won = mPreference.getInt("Won",0) ;
                Integer los = mPreference.getInt("Loss",0) ;
                //Toast.makeText(this,"CPU Won",Toast.LENGTH_SHORT).show();
                matc+=1 ;
                los +=1 ;
                new AlertDialog.Builder(this)
                        .setTitle("CPU Won")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                disableAll() ;
                            }
                        }).show() ;
                writeValues(matc,won,los);
                disableAll();
            }
            txtView4.setText("Score:"+current_score);
        }
    }
    public void computerOut(){
        SharedPreferences mPreference = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE) ;
        Integer matc = mPreference.getInt("Matches",0) ;
        Integer won = mPreference.getInt("Won",0) ;
        Log.d("WON",won+"") ;
        Log.d("Match",matc+"") ;
        Integer los = mPreference.getInt("Loss",0) ;
        if(current_score < target-1){
            matc +=1 ;
            won +=1 ;
            new AlertDialog.Builder(this)
                    .setTitle("You Won")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            disableAll() ;
                        }
                    }).show() ;
            //Toast.makeText(this,"You Won!!",Toast.LENGTH_SHORT).show();
        }
        else if(current_score == target-1){
            matc +=1 ;
            new AlertDialog.Builder(this)
                    .setTitle("Match ended in a draw")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            disableAll() ;
                        }
                    }).show() ;
            //Toast.makeText(this,"Match ended in a draw",Toast.LENGTH_SHORT).show();
        }
        else {
            matc+=1 ;
            los +=1 ;
            new AlertDialog.Builder(this)
                    .setTitle("CPU Won")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            disableAll() ;
                        }
                    }).show() ;

            //Toast.makeText(this,"CPU Won",Toast.LENGTH_SHORT).show();
        }
        writeValues(matc,won,los) ;
    }
    public void disableAll(){
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
    }
    public void writeValues(Integer matches,Integer won,Integer loss){
        SharedPreferences mPreference = getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mPreference.edit() ;
        mEditor.putInt("Matches",matches) ;
        mEditor.putInt("Won",won) ;
        mEditor.putInt("Loss",loss) ;
        mEditor.commit() ;
        Log.d("Overall",matches+" "+won+" "+loss) ;
    }
    public void userOut(){
        new AlertDialog.Builder(this)
                .setTitle("You got out")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
        //Toast.makeText(this,"You got out",Toast.LENGTH_LONG).show();
        //SystemClock.sleep(500);
        target = current_score + 1 ;
        //current_score+=1 ;
        txtView9.setText("Target:"+current_score.toString());
        //txtView8.setText("Score:0") ;
        txtView.setText("Bat:Computer");
        txtView3.setText("");
        txtView8.setText("");
        //total_score = current_score ;
        current_score = 0 ;
        txtView4.setText("Score:0");
        isUser = false ;
        current_score = 0 ;

    }
}
