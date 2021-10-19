// Copyright (c) SmartLab Solutions. All rights reserved.
// Authors: Vladimir Pogiba

package team.sls.demoqr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragmentStart extends FragmentBase
{
	// -----------------------------------------------------------------------------------------------------------------
	@Nullable
	@Override
	public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState )
	{
		View rootView = inflater.inflate( R.layout.fragment_start, container, false );

		View textFragmentB = rootView.findViewById( R.id.textFragmentB );
		textFragmentB.setOnClickListener( v -> activity.showFragmentQrScanWithPermission() );

		return rootView;
	}
}
