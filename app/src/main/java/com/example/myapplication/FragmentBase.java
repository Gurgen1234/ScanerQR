// Copyright (c) SmartLab Solutions. All rights reserved.
// Authors: Vladimir Pogiba

package com.example.myapplication;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class FragmentBase extends Fragment
{
	protected MainActivity activity;

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void onAttach( @NonNull Context context)
	{
		super.onAttach( context );
		activity = (MainActivity)context;
	}

	@Override
	public void onResume(){
		super.onResume();
	}
}
