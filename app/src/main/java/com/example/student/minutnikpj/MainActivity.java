package com.example.student.minutnikpj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Timer t;
    private Boolean isTimerRuning = false;
    private Boolean isPaused = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView Counter = (TextView)findViewById(R.id.textViewCounter);
        Button start = (Button)findViewById(R.id.buttonStart);
        Button pause = (Button)findViewById(R.id.buttonPause);
        Button stop = (Button)findViewById(R.id.buttonStop);

        /*
        Declaring onClick events for add buttons
         */
        Button addMinutes = (Button)findViewById(R.id.buttonAddMinutes);
        addMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTimerRuning)
                Counter.setText(changeCounter(0,1, (String) Counter.getText()));
            }
        });
        Button addMinute = (Button)findViewById(R.id.buttonAddMinute);
        addMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTimerRuning)
                Counter.setText(changeCounter(1,1,(String) Counter.getText()));
            }
        });
        Button addSeconds = (Button)findViewById(R.id.buttonAddSeconds);
        addSeconds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTimerRuning)
                Counter.setText(changeCounter(5,1,(String)Counter.getText()));
            }
        });
        Button addSecond = (Button)findViewById(R.id.buttonAddSecond);
        addSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTimerRuning)
                Counter.setText(changeCounter(6,1,(String)Counter.getText()));
            }
        });
        /*
        Declaring onClick events on substract buttons
         */
        Button substractMinutes = (Button)findViewById(R.id.buttonSubstractMinutes);
        substractMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTimerRuning)Counter.setText(changeCounter(0,-1, (String) Counter.getText()));
            }
        });
        Button substractMinute = (Button)findViewById(R.id.buttonSubstracMinute);
        substractMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTimerRuning)Counter.setText(changeCounter(1,-1,(String) Counter.getText()));
            }
        });
        Button substractSeconds = (Button)findViewById(R.id.buttonSubstracSeconds);
        substractSeconds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTimerRuning)Counter.setText(changeCounter(5,-1,(String)Counter.getText()));
            }
        });
        Button substractSecond = (Button)findViewById(R.id.buttonSubstracSecond);
        substractSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTimerRuning)Counter.setText(changeCounter(6,-1,(String)Counter.getText()));
            }
        });

        /*
        Declaring onClick events on function buttons
         */
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if((!isTimerRuning || isPaused) && (String)Counter.getText()!="00 : 00") {
                    t.start();
                    isTimerRuning = true;
                    isPaused = false;
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTimerRuning ) {
                    t.cancel();
                    Counter.setText("00 : 00");
                    changeCounter(0, 0, "00 : 00");
                    isTimerRuning = false;

                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTimerRuning ) {
                    t.cancel();
                    String tmp = (String) Counter.getText();
                    if (tmp.length() < 7) tmp = "0" + tmp;
                    changeCounter(0, 0, tmp);
                    isPaused = true;

                }
            }
        });


    }
    /*
    @pos-> its a position of digit in our textview, could be:
        0 - tens of minutes
        1 - single digit minutes
        5 - tens of seconds
        6 - single digit seconds
    @sign-> +1 add, -1 substract, 0 do nothing
    @counter->reference of current state of textview
     */
    private String changeCounter(int pos, int sign, String counter)
    {
        //char a = counter.charAt(pos);
        int tmp = Character.getNumericValue(counter.charAt(pos));
        tmp += sign;
        if(pos==5)
        {
            if(tmp>5)tmp=0;
            else if(tmp<0)tmp=5;
        }
        else {
            if (tmp > 9) tmp = 0;
            else if (tmp < 0) tmp = 9;

        }
        String res = counter.substring(0, pos) + tmp + counter.substring(pos + 1);
        t = new Timer(this, getTime(res),1000);
        return res;
    }
    /*
        we calculate here the numerical value of our textview
    */
    public long getTime(String counter)
    {
        long result = Character.getNumericValue(counter.charAt(0))*600000 + Character.getNumericValue(counter.charAt(1))*60000+
                Character.getNumericValue(counter.charAt(5))*10000 + Character.getNumericValue(counter.charAt(6))*1000;
        return result;
    }
}
