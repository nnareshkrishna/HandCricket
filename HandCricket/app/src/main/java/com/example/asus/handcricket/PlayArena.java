package com.example.asus.handcricket;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;

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
                Toast.makeText(this,"CPU Won",Toast.LENGTH_SHORT).show();
            }
            txtView4.setText("Score:"+current_score);
        }
    }
    public void computerOut(){
        if(current_score < target-1){
            Toast.makeText(this,"You Won!!",Toast.LENGTH_SHORT).show();
        }
        else if(current_score == target-1){
            Toast.makeText(this,"Match ended in a draw",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"CPU Won",Toast.LENGTH_SHORT).show();
        }
    }
    public void userOut(){
        Toast.makeText(this,"You got out",Toast.LENGTH_LONG).show();
        SystemClock.sleep(500);
        target = current_score + 1 ;
        //current_score+=1 ;
        txtView9.setText("Target:"+current_score.toString());
        //txtView8.setText("Score:0") ;
        txtView.setText("Bat:Computer");
        txtView3.setText("");
        txtView8.setText("");
        //total_score = current_score ;
        current_score = 0 ;
        txtView4.setText("Score:"+current_score);
        isUser = false ;

    }
}
