package com.scott.assignment1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences sharedPreferences;
    private TextView welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


   /*     FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        welcomeMessage = (TextView) findViewById(R.id.textView_welcome_user_email);
        sharedPreferences = getSharedPreferences("UserInfoData", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("userEmail", "Couldn't find user email");

        welcomeMessage.setText("Welcome, " + userEmail.toString());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button event1Button = (Button) findViewById(R.id.buttonEvent1ViewSpeakers);
        event1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =  getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SpeakersListFragment speakersListFragment = new SpeakersListFragment();

                fragmentTransaction.add(R.id.speakers_frag_1_container, speakersListFragment);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_general_schedule) {
            startActivity(new Intent(MainNavigationActivity.this, MainNavigationActivity.class));
        } else if (id == R.id.nav_my_schedule) {
            startActivity(new Intent(MainNavigationActivity.this, MyScheduleActivity.class));
        } else if (id == R.id.nav_venue_map) {
            startActivity(new Intent(MainNavigationActivity.this, VenueMapActivity.class));
        } else if (id == R.id.nav_live_feed) {
            startActivity(new Intent(MainNavigationActivity.this, LiveFeedActivity.class));
        } else if (id == R.id.nav_event_sponsors) {
            startActivity(new Intent(MainNavigationActivity.this, SponsorsActivity.class));
        } else if (id == R.id.nav_surveys) {
            startActivity(new Intent(MainNavigationActivity.this, SurveysActivity.class));
        }else if(id == R.id.nav_leaderboards){
            startActivity(new Intent(MainNavigationActivity.this, LeaderboardsActivity.class));
        }else if(id == R.id.nav_attendees_list) {
            startActivity(new Intent(MainNavigationActivity.this, AttendeesListActivity.class));
        }else if(id == R.id.nav_logout){
          Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
