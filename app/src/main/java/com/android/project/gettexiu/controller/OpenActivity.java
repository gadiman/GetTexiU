package com.android.project.gettexiu.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.android.project.gettexiu.R;
import com.spark.submitbutton.SubmitButton;

public class OpenActivity extends Activity {

    //---------------------------------- Fields ------------------------------------------//
    SubmitButton submitButton;
    EditText phoneNumber;

    //---------------------------------- Functions --------------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screen);
        findViews();
    }

    //This function load all the xml Objects and create Listeners
    private void findViews() {
        final Intent nextIntent=new Intent( OpenActivity.this, MainActivity.class);
        submitButton = (SubmitButton) findViewById(R.id.sendButton);
        phoneNumber= (EditText) findViewById(R.id.PhoneNumber) ;
        submitButton.setVisibility(View.GONE);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String str= phoneNumber.getText().toString();
                        nextIntent.putExtra("Phone",str);
                        startActivity(nextIntent);
                    }
                }, 3000);
            }
        });

        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()== 10) {
                    submitButton.setVisibility(View.VISIBLE);
                    View view = getCurrentFocus();
                    if(view != null)
                    {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()> 10 || s.length() <10)
                    submitButton.setVisibility(View.GONE);

            }
        });

    }

}
