package com.example.myapplication;
import org.denom.Binary;
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
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_cam, R.id.navigation_map)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }
        public void showFragmentScanQrResult(Binary qrBytes)
        {
            Bundle args = new Bundle();
            args.putString( FragmentScanQrResult.SCAN_QR_RESULT, qrBytes.Hex() );
            FragmentScanQrResult fragmentScanQrResult = new FragmentScanQrResult();
            fragmentScanQrResult.setArguments( args );
            showFragment( fragmentScanQrResult );
        }

        // -----------------------------------------------------------------------------------------------------------------
        private void showFragment( Fragment fragment )
        {
            String tag = fragment.getTag();
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(tag)
                    .add( R.id.container, fragment)
                    .commit();
        }

        // ----------------------------------------------------------------------------------------------------------------


}