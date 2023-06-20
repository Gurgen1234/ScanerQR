package com.example.myapplication;


import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.denom.Binary;

public class ActivityResult extends AppCompatActivity
{
    TextView header;
    ImageView webView;
    TextView textView;
    DBHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    String scannedBytes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        scannedBytes = getIntent().getExtras().getString("res");
        header = (TextView)findViewById(R.id.textViewTitle);
        webView = (ImageView) findViewById(R.id.imageViewObject);
        textView = (TextView)findViewById(R.id.textViewAddress);
        databaseHelper = new DBHelper(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        db = databaseHelper.getReadableDatabase();;

        //получаем данные из бд в виде курсора
        String a = "SELECT * FROM DT WHERE _id = " + scannedBytes + ";";
        userCursor = db.rawQuery(a, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        // создаем адаптер, передаем в него курсор
        userCursor.moveToFirst();
//        int id = Integer.getInteger(userCursor.getString(0));
        header.setText(userCursor.getString(1));
        textView.setText(userCursor.getString(2));
        Picasso.get()
                .load(userCursor.getString(5))
                .resize(800, 800)
                .centerCrop()
                .into(webView);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(ActivityResult.this, MainActivity.class);
//                intent.addFlags();
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}