package com.scott.assignment1;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SurveysActivity extends AppCompatActivity {


    private FileOutputStream fileOutputStream;
    private static final String SURVEY_FILE_NAME = "survey.txt";
    private String answerOne = "No Response";
    private String answerTwo = "No Response";
    private String answerThree = "No Response";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveys);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button submitSurveyButton = (Button) findViewById(R.id.btnSubmitSurvey);
        submitSurveyButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                RadioButton responseOneYes = (RadioButton) findViewById(R.id.radioButtonYesQuestion1);
                RadioButton responseOneNo = (RadioButton) findViewById(R.id.radioButtonNoQuestion1);
                RadioButton responseTwoYes = (RadioButton) findViewById(R.id.radioButtonYesQuestion2);
                RadioButton responseTwoNo = (RadioButton) findViewById(R.id.radioButtonNoQuestion2);
                RadioButton responseThreeYes = (RadioButton) findViewById(R.id.radioButtonYesQuestion3);
                RadioButton responseThreeNo = (RadioButton) findViewById(R.id.radioButtonNoQeustion3);

                if (responseOneYes.isChecked())
                    answerOne = "Yes";

                if (responseOneNo.isChecked())
                    answerOne = "No";

                if (responseTwoYes.isChecked())
                    answerTwo = "Yes";

                if (responseTwoNo.isChecked())
                    answerTwo = "No";

                if (responseThreeYes.isChecked())
                    answerThree = "Yes";

                if (responseThreeNo.isChecked())
                    answerThree = "No";


                submitSurvey(answerOne,answerTwo,answerThree);

                Toast.makeText(SurveysActivity.this, SURVEY_FILE_NAME + " Has been modified", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void submitSurvey(String answerOne, String answerTwo, String answerThree){

        try {


            File file = new File(this.getFilesDir(), SURVEY_FILE_NAME);

            if (!file.exists())
                file.createNewFile();

            String timeStamp = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());

            JSONObject surveyResponse = new JSONObject();
            surveyResponse.put("Time Stamp", timeStamp);
            surveyResponse.put("Question 1", answerOne);
            surveyResponse.put("Question 2", answerTwo);
            surveyResponse.put("Question 3", answerThree);

            fileOutputStream = openFileOutput(SURVEY_FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(surveyResponse.toString().getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
   }
}
