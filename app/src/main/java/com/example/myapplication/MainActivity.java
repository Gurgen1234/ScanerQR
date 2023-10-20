package com.example.myapplication;

import org.denom.Binary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {
    public static int REQUEST_CODE_CAMERA_READ_QR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_cam, R.id.navigation_map, R.id.navigation_schedule, R.id.navigation_achievements)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    public void showFragmentScanQrResult(Binary qrBytes) {
        Intent intj = new Intent(this, ActivityResult.class);
        intj.putExtra("res", qrBytes.asUTF8());
        startActivity(intj);
        finish();
    }

    public void showFragmentBr(String href) {
        Intent intj = new Intent(this, BrActivity.class);
        intj.putExtra("href", href);
        startActivity(intj);
        finish();
    }
    public void showFragmenNote() {
        Intent intj = new Intent(this, NoteActivity.class);
        startActivity(intj);
        finish();
    }
    public String getAchive(){
        try {
            return getIntent().getExtras().getString("achiv");
        }
        catch (NullPointerException e){
          return null;
        }
    }

    // ----------------------------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------


}




































































