// Copyright (c) SmartLab Solutions. All rights reserved.
// Authors: Vladimir Pogiba

package team.sls.demoqr;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FragmentBase extends Fragment
{
	protected ActivityDemoQr activity;

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void onAttach( @NonNull Context context )
	{
		super.onAttach( context );
		activity = (ActivityDemoQr)context;
	}
}
