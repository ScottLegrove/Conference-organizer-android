package com.scott.assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUserActivity extends AppCompatActivity {

    EditText userEmail;
    EditText userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        userEmail = (EditText) findViewById(R.id.editTextRegisterUserEmail);
        userPassword = (EditText) findViewById(R.id.editTextRegisterUserPassword);
    }

    public void registerUser(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("UserInfoData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userEmail", userEmail.getText().toString());

        if(!userEmail.getText().toString().isEmpty()) {
            editor.commit();
            Toast.makeText(this, "You have succesfully registered ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterUserActivity.this, LoginActivity.class));
        }else {
            Toast.makeText(this, "You must enter an email address", Toast.LENGTH_LONG).show();
        }
    }

}
