 // Copyright (c) SmartLab Solutions. All rights reserved.
// Authors: Vladimir Pogiba

package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import org.denom.Binary;

public class FragmentScanQrResult extends FragmentBase
{
	public static String SCAN_QR_RESULT = "ScanQrResult";
	TextView header;
	ImageView webView;
	TextView textView;
	DBHelper databaseHelper;
	SQLiteDatabase db;
	Cursor userCursor;
	SimpleCursorAdapter userAdapter;
	Binary scannedBytes;
	// -----------------------------------------------------------------------------------------------------------------
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState )
	{
		View rootView = inflater.inflate( R.layout.fragment_scan_qr_result, container, false );

		Bundle bundle = getArguments();
		if( bundle != null )
		{
			scannedBytes = new Binary( bundle.getString( SCAN_QR_RESULT, "" ) );
		}
		header = (TextView)rootView.findViewById(R.id.name);
		webView = (ImageView) rootView.findViewById(R.id.pic);
		textView = (TextView)rootView.findViewById(R.id.history);
		databaseHelper = new DBHelper(rootView.getContext());
		return rootView;
	}
	@Override
	public void onResume() {
		super.onResume();
		// открываем подключение
		db = databaseHelper.getReadableDatabase();;

		//получаем данные из бд в виде курсора
		String a = "SELECT * FROM EX WHERE _id = "+ scannedBytes.asUTF8()+ ";";
		userCursor =  db.rawQuery(a, null);
		// определяем, какие столбцы из курсора будут выводиться в ListView
		// создаем адаптер, передаем в него курсор
		userCursor.moveToFirst();
		header.setText(userCursor.getString(1));
		textView.setText(userCursor.getString(2));
		Picasso.get ()
				.load (userCursor.getString(3))
				.resize (800, 800)
				.centerCrop ()
				.into (webView);
	}

}
