package com.nathanwkong.inttips;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class IntTip extends Activity {
    private View mMainView;
    private final String totalErrorString = "please check your total price";
    private final String percentErrorString = "Please check your custom percent";
    private final double fifteenPercent = 0.15;
    private final double eightteenPercent = 0.18;

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

        //create a toast
        Context context = getApplicationContext();
        String text = "clink";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_int_tip, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void email(View v){
        EditText bill = (EditText)findViewById(R.id.totalBill);
        double totalBill = Integer.parseInt(bill.getText().toString());



    }

    /**
     * Sets the tip or total amount to the screen
     * @param textView the view/id to write to
     * @param money the amount to tip to the total
     */
    private void displayTipToScreen(int textView, double money){
        TextView v = (TextView) findViewById(textView);
        v.setText("$"+money);
    }

    /**
     * Sets the percentage to the screen
     * @param textView
     * @param percent
     */
    private void displayPercentToScreen(int textView, int percent){
        TextView v = (TextView) findViewById(textView);
        v.setText(percent+"%");
    }




}
