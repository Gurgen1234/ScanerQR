 package com.example.myapplication;

 import android.os.Bundle;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;

 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;

 import org.denom.Binary;

 import team.sls.camera.CameraParameters;
 import team.sls.camera.CameraView;
 import team.sls.zxing.ZXing;

public class FragmentScanQr extends FragmentBase
{

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState )
    {
        View rootView = inflater.inflate( R.layout.fragment_scan_qr, container, false );

        CameraView cameraView = rootView.findViewById( R.id.cameraView );
        cameraView.start( CameraParameters.Mode.CONTINUE, CameraParameters.PreviewQuality.STANDARD, 1, 1, false, false, false, overlayBitmap ->
        {
            try
            {
                Binary qrBytes = ZXing.qrToBinary( overlayBitmap );

                activity.showFragmentScanQrResult( qrBytes );
                return true;
            }
            catch( Throwable ignored )
            {
                return false;
            }
        } );

        return rootView;
    }

}