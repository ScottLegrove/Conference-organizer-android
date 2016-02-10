package com.scott.assignment1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import org.w3c.dom.Text;

public class PresenterDetailsActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenter_details);

        TextView presenterName = (TextView) findViewById(R.id.textViewPresenterName);
        TextView presenterEmail = (TextView) findViewById(R.id.textViewPresenterEmail);
        TextView presenterAffiliation = (TextView) findViewById(R.id.textViewPresenterAffiliation);
        TextView presenterBio = (TextView) findViewById(R.id.textViewPresenterBio);

        Intent intent = getIntent();
        String name = intent.getStringExtra("presenterName");
        String email = intent.getStringExtra("presenterEmail");
        String affiliation = intent.getStringExtra("presenterAffiliation");
        String bio = intent.getStringExtra("presenterBio");



        presenterName.setText(name);
        presenterEmail.setText(email);
        presenterAffiliation.setText(affiliation);
        presenterBio.setText(bio);


    }
}
