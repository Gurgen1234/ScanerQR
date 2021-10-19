// Copyright (c) SmartLab Solutions. All rights reserved.
// Authors: Vladimir Pogiba

package team.sls.demoqr;

import org.denom.Binary;

import android.os.Bundle;
import android.view.*;

import androidx.annotation.*;
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

		View back = rootView.findViewById( R.id.back );
		back.setOnClickListener( v -> activity.onBack() );

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
