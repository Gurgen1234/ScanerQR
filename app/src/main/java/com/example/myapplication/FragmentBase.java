// Copyright (c) SmartLab Solutions. All rights reserved.
// Authors: Vladimir Pogiba

package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FragmentBase extends Fragment
{
	protected MainActivity activity;

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void onAttach( @NonNull Context context )
	{
		super.onAttach( context );
		activity = (MainActivity)context;
	}
}
