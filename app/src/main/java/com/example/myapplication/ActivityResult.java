package com.example.myapplication;


import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

import java.io.IOException;

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
    String visited;

    String buildingClass;
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
        visited = userCursor.getString(4);
        textView.setText(userCursor.getString(2));
        buildingClass = userCursor.getString(3);

        Picasso.get()
                .load(userCursor.getString(5))
                .resize(800, 800)
                .centerCrop()
                .into(webView);
        if (visited.equals("-")){
            header.setText("Поздавляем вы открыли новое здание!!!\n" + userCursor.getString(1));
            header.setTextColor(Color.RED);
            try {
                setVisited();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            header.setText(userCursor.getString(1));
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                Intent intent = new Intent(ActivityResult.this, MainActivity.class);
                if (visited.equals("+")) {
                    intent.putExtra("acive",buildingClass);
                }
                db.close();
                userCursor.close();
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void setVisited() throws IOException {
        ContentValues cv = new ContentValues();
        cv.put("visited","+");
        String id = "_id";
        db.update("DT", cv,id + "=" + scannedBytes, null);
    }
}