/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {
  Button login;
  TextView signUp;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


//    getSupportActionBar().hide();

    ImageView icon = findViewById(R.id.imageView);
    icon.setImageResource(R.mipmap.instagram);

    final EditText username = findViewById(R.id.username);
    final EditText password = findViewById(R.id.password);
    login = findViewById(R.id.login);
    signUp  = findViewById(R.id.signup);

    signUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(signUp.getText().equals("Or Sign Up")) {
          signUp.setText("Or Login");
          login.setText("Sign Up");
        }else {
          signUp.setText("Or Sign Up");
          login.setText("Login");
        }
      }
    });

    login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (login.getText().equals("Login")) {
          ParseUser user = new ParseUser();
          if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter username and password", Toast.LENGTH_SHORT).show();
          } else {
            user.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
              @Override
              public void done(ParseUser user, ParseException e) {
                if (e == null) {
                  Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                } else {
                  e.printStackTrace();
                }
              }
            });
          }
        } else {
          ParseUser user = new ParseUser();
          if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter username and password", Toast.LENGTH_SHORT).show();
          } else {
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
              @Override
              public void done(ParseException e) {
                if (e == null) {
                  Toast.makeText(getApplicationContext(), "Sign UP success", Toast.LENGTH_SHORT).show();
                } else {
                  Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
                  e.printStackTrace();
                }
              }
            });
          }
        }
      }
    });



    
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}