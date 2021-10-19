// Copyright (c) SmartLab Solutions. All rights reserved.
// Authors: Vladimir Pogiba

package team.sls.demoqr;

import org.denom.Binary;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

import androidx.annotation.*;

public class FragmentScanQrResult extends FragmentBase
{
	public static String SCAN_QR_RESULT = "ScanQrResult";

	// -----------------------------------------------------------------------------------------------------------------
	@Nullable
	@Override
	public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState )
	{
		View rootView = inflater.inflate( R.layout.fragment_scan_qr_result, container, false );

		View back = rootView.findViewById( R.id.back );
		back.setOnClickListener( v -> activity.onBack() );

		TextView scanQrResult = rootView.findViewById( R.id.scanQrResult );

		Bundle bundle = getArguments();
		if( bundle != null )
		{
			Binary scannedBytes = new Binary( bundle.getString( SCAN_QR_RESULT, "" ) );
			scanQrResult.setText( scannedBytes.asUTF8() );
		}

		return rootView;
	}
}
