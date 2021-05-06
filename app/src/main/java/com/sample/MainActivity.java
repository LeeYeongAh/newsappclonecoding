package com.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText textinputeditor_password,textinputeditor_email;
    RelativeLayout relative_loginwithyoutube, relative_login;
    String emailOK = "1@gmail.com";
    String passwordOK = "12";
    String inputEmail = "";
    String inputPassword = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textinputeditor_email=findViewById(R.id.textinputeditor_email);
        textinputeditor_password=findViewById(R.id.textinputeditor_password);
        relative_login=findViewById(R.id.relative_login);
        relative_loginwithyoutube=findViewById(R.id.relative_loginwithyoutube);

        relative_login.setClickable(false);
        //1. 1@gmail.com / 12

        textinputeditor_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // Log.d("SENTT", s);
                if(s!=null) {
                    inputEmail = s.toString();
                    relative_login.setClickable(validation());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textinputeditor_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d("SENTT", s);
                if (s != null) {
                    inputPassword = s.toString();
                    relative_login.setClickable(validation());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if(relative_login.isClickable()) {

            relative_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = textinputeditor_email.getText().toString();
                    String password = textinputeditor_password.getText().toString();

                    Intent intent = new Intent(MainActivity.this, LoginResultActivity.class);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
            });
        }
    }
    public boolean validation(){
        return inputEmail.equals(emailOK) && inputPassword.equals(passwordOK);
    }

}