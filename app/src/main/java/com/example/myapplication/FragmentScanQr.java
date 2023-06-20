 package com.example.myapplication;

 import android.Manifest;
 import android.content.Context;
 import android.content.Intent;
 import android.content.pm.PackageManager;
 import android.net.Uri;
 import android.os.Bundle;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;

 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;
 import androidx.core.app.ActivityCompat;
 import androidx.core.content.ContextCompat;
 import androidx.fragment.app.FragmentManager;

 import org.denom.Binary;

 import team.sls.camera.CameraParameters;
 import team.sls.camera.CameraView;
 import team.sls.zxing.ZXing;

public class FragmentScanQr extends FragmentBase {
    CameraView cameraView;
    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState )
    {
        View rootView = inflater.inflate(R.layout.fragment_scan_qr, container, false );
        int permissionStatus = ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.CAMERA);
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[] {Manifest.permission.CAMERA},
                    0);
        }
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            cameraView = rootView.findViewById( R.id.cameraView);
        }


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        cameraView.start(CameraParameters.Mode.CONTINUE, CameraParameters.PreviewQuality.STANDARD, 1, 1, false, false, false, overlayBitmap ->
        {
            try {
                Binary qrBytes = ZXing.qrToBinary(overlayBitmap);
                activity.showFragmentScanQrResult(qrBytes);
                return true;
            } catch (Throwable ignored) {
                return false;
            }

        });
    }
}