package com.example.umeed;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.umeed.Map.MapsActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    double lat = 12.0, lon = 77.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //First fragment should be selected by default
        navigationView.getMenu().getItem(0).setChecked(true);




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

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fm =  getSupportFragmentManager();

        if (id == R.id.nav_home) {

            Intent intent2 = new Intent(MainActivity.this, DashBoard.class);
            startActivity(intent2);
        }
        else if (id == R.id.nav_map) {
            Intent intent2 = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent2);
        }
        else if (id == R.id.nav_emergency) {
            Intent intent2 = new Intent(MainActivity.this, sos.class);
            startActivity(intent2);
        }
        else if (id == R.id.tips) {
            Intent intent2 = new Intent(MainActivity.this, Tips.class);
            startActivity(intent2);
        }
        else if (id == R.id.nav_aid) {
            Intent intent2 = new Intent(MainActivity.this, aid_fragment.class);
            startActivity(intent2);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}