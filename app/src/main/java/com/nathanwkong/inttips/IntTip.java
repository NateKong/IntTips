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

import java.util.ArrayList;


public class IntTip extends Activity {
    private View mMainView;
    private final String totalErrorString = "please check your total price";
    private final String percentErrorString = "Please check your custom percent";
    private double bill;
    private ArrayList<Double> percent;
    private ArrayList<Double> tip;
    private ArrayList<Double> total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get rid of title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //if lower than Jellybean remove status bar
        if(Build.VERSION.SDK_INT < 16){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.layout_tip_main);//sets view to layout_tip.xml
        mMainView = getWindow().getDecorView();//gets the current window.retrieve top level window
        mMainView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);//request the bisibility of the status bar (request full screen mode)

        //constructor
        bill = 0;
        percent = new ArrayList<Double>();
        tip = new ArrayList<Double>();
        total = new ArrayList<Double>();

        //create a toast
        Context context = getApplicationContext();
        String text = "clink";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    /**
     * Calculates the total bill with tip
     * @param v
     */
    public void email(View v){
        EditText tBill = (EditText)findViewById(R.id.totalBill);
        bill = Double.parseDouble(tBill.getText().toString());

        //calculate 15%
        percent.add(15.0);
        tip.add(calculateTip(percent.get(0)));

        //round up by $1

        //round down by $1

        //calculate custom
        EditText customPercent = (EditText)findViewById(R.id.cPercent);
        double percent = Double.parseDouble(customPercent.getText().toString());

        //display

    }

    /**
     * Sets the tip or total amount to the screen
     * @param textView the view/id to write to
     * @param money the amount to tip to the total
     */
    private void displayToScreen(int textView, double money){
        String moneyString = "$" + String.format("%0.2f", money);
        TextView v = (TextView) findViewById(textView);
        v.setText(moneyString);
    }

    /**
     * Sets the percentage to the screen
     * @param textView
     * @param percent
     */
    private void displayPercentToScreen(int textView, int percent){
        String percentString = String.format("%0.2f", percent) + "%";
        TextView v = (TextView) findViewById(textView);
        v.setText(percentString);
    }

    /**
     * Calculates the percent of a tip
     * @param percent of a tip e.g. 33.3 = 33.3%
     * @return the dollar amount of a tip
     */
    private double calculateTip(double percent){
        return bill * (percent/100);

    }

    /**
     * Calcuates the dollar amount of a tip
     * @param tip the dollar amount
     * @return the percent e.g. 20 = 20%
     */
    private double calculatePercent(double tip) {
        return (tip/bill)*100;
    }


}
