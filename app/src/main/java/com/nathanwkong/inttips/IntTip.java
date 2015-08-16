package com.nathanwkong.inttips;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class IntTip extends Activity {
    private View mMainView;
    private double bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get rid of title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //if lower than Jellybean remove status bar
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.layout_tip_main);//sets view to layout_tip.xml
        mMainView = getWindow().getDecorView();//gets the current window.retrieve top level window
        mMainView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);//request the bisibility of the status bar (request full screen mode)

        //constructor
        bill = 10;

        //create a toast
        toast();

    }

    /**
     * Calculates the total bill with tip
     *
     * @param v
     */
    public void calculate(View v) {
        EditText tBill = (EditText) findViewById(R.id.totalBill);
        String theBill = tBill.getText().toString();
        if (!theBill.matches("")){
            bill = Double.parseDouble(theBill);
        }

        //calculate 15%
        double tip15 = calculate15();
/*
        //15%+ round the tip to the nearest dollar
        calculate15p(Math.ceil(tip15));

        //18%
        double tip18 = calculate18();
        //18%- round down to nearest dollar
        double tip18 = calculateTip(18.0);
        double tip18m = Math.floor(tip18);
        tip.add(tip18m);
        double total18m = tip18m + bill;
        total.add(total18m);
        percent.add(calculatePercent(tip18m));

        //18%+
        double tip18p = Math.ceil(tip18);
        tip.add(tip18p);
        double total18p = tip18p + bill;
        total.add(total18p);
        percent.add(calculatePercent(tip18p));

        //calculate custom
        EditText customPercent = (EditText) findViewById(R.id.cPercent);
        double cPercent = Double.parseDouble(customPercent.getText().toString());
        percent.add(cPercent);
        double cTip = calculateTip(cPercent);
        tip.add(cTip);
        total.add(cTip + bill);
        */
        toast();
    }

    private double calculate15(){
       double tip15 = calculateTip(15.0);
       displayToScreen(R.id.tip15, tip15);
       displayToScreen(R.id.total15, tip15+bill);
       return tip15;
    }

    private double calculate18(){
        double tip18 = calculateTip(18.0);
        displayToScreen(R.id.tip18, tip18);
        displayToScreen(R.id.total18, tip18 + bill);
        return tip18;
    }

    private void calculate15p(double tip15p){
        displayToScreen(R.id.tip15Plus, tip15p);
        double total15p = tip15p + bill;
        displayToScreen(R.id.total15Plus, total15p );
        displayPercentToScreen(R.id.plus15, calculatePercent(tip15p * 100));
    }
    /**
     * Sets the tip or total amount to the screen
     *
     * @param textView the view/id to write to
     * @param money    the amount to tip to the total
     */
    private void displayToScreen(int textView, double money) {
        String moneyString = NumberFormat.getCurrencyInstance().format(money); //+ String.format("%0.2lf", money);
        TextView v = (TextView) findViewById(textView);
        v.setText(moneyString);
    }

    /**
     * Sets the percentage to the screen
     *
     * @param textView
     * @param percent
     */
    private void displayPercentToScreen(int textView, double percent) {
        String percentString = NumberFormat.getCurrencyInstance().format(percent) + "%";
        TextView v = (TextView) findViewById(textView);
        v.setText(percentString);
    }

    /**
     * Calculates the percent of a tip
     *
     * @param percent of a tip e.g. 33.3 = 33.3%
     * @return the dollar amount of a tip
     */
    private double calculateTip(double percent) {
        return bill * (percent / 100);
    }

    /**
     * Calcuates the dollar amount of a tip
     *
     * @param tip the dollar amount
     * @return the percent e.g. 20 = 20%
     */
    private double calculatePercent(double tip) {
        return (tip / bill) * 100;
    }

    private void toast(){
        Context context = getApplicationContext();
        String text = "cha ching" + "\n" + "cha ching";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
