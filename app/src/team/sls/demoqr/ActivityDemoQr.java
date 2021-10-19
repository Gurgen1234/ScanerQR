// Copyright (c) SmartLab Solutions. All rights reserved.
// Authors: Vladimir Pogiba

package team.sls.demoqr;

import org.denom.Binary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.*;

public class ActivityDemoQr extends AppCompatActivity
{
	public static int REQUEST_CODE_CAMERA_READ_QR = 1;

	private FragmentManager fragmentManager;

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_demo_qr );
		fragmentManager = getSupportFragmentManager();
		showFragmentStart();
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void onBackPressed()
	{
		onBack();
	}

	// -----------------------------------------------------------------------------------------------------------------
	void onBack()
	{
		Fragment topFragment = getTopFragment();
		if( topFragment == null )
		{
			finish();
			return;
		}

		if( fragmentManager.getBackStackEntryCount() > 1 )
		{
			fragmentManager.popBackStack();
		}
		else
		{
			finish();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------
	void showFragmentStart()
	{
		showFragment( new FragmentStart() );
	}

	// -----------------------------------------------------------------------------------------------------------------
	void showFragmentScanQr()
	{
		showFragment( new FragmentScanQr() );
	}

	// -----------------------------------------------------------------------------------------------------------------
	void showFragmentScanQrResult( Binary qrBytes )
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
		String tag = fragment.getClass().getName();

		// запрещаем открытие двух одинаковых фрагментов подряд
		Fragment lastCopyOfFragment = fragmentManager.findFragmentByTag(tag);
		if((lastCopyOfFragment != null) && lastCopyOfFragment.isVisible())
		{
			return;
		}

		fragmentManager.beginTransaction()
			.addToBackStack( tag )
			.replace( R.id.fragmentContainer, fragment, tag )
			.commit();
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * Получить текущий фрагмент (Верхний фрагмент с бэкстэка)
	 */
	private Fragment getTopFragment()
	{
		int index = fragmentManager.getBackStackEntryCount() - 1;
		if( index < 0 )
		{
			return null;
		}
		FragmentManager.BackStackEntry backEntry = fragmentManager.getBackStackEntryAt( index );
		String tag = backEntry.getName();
		return fragmentManager.findFragmentByTag( tag );
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * Закрыть все открытые экраны (Очистить бэкстэк)
	 */
	private void clearBackStack()
	{
		fragmentManager.popBackStackImmediate( null, FragmentManager.POP_BACK_STACK_INCLUSIVE );
	}

	// -----------------------------------------------------------------------------------------------------------------
	public void showFragmentQrScanWithPermission()
	{
		if( checkSelfPermission( Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED )
		{
			requestPermissions( new String[]{ Manifest.permission.CAMERA }, REQUEST_CODE_CAMERA_READ_QR );
		}
		else
		{
			showFragmentScanQr();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void onRequestPermissionsResult( int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults )
	{
		super.onRequestPermissionsResult( requestCode, permissions, grantResults );

		if( grantResults.length < 1 )
		{
			return;
		}

		if( (requestCode == REQUEST_CODE_CAMERA_READ_QR) && (grantResults[ 0 ] == PackageManager.PERMISSION_GRANTED) )
		{
			showFragmentScanQr();
		}
	}
}
