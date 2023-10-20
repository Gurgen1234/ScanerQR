package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.sql.ResultSet;

public class FragmentAchievements extends FragmentBase {
    TextView header;
    ImageView webView;
    TextView textViewAll;
    TextView textViewHotel;
    TextView textViewSince;
    TextView textViewCulture;
    DBHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    ResultSet rs;
    SimpleCursorAdapter userAdapter;
    String scannedBytes;
    String visited;
    ProgressBar pbHorizontalAllBuild;
    ProgressBar pbHorizontalHotel;
    ProgressBar pbHorizontalSince;
    ProgressBar pbHorizontalCulture;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_achievements, container, false);
        rootView.setBackgroundColor(Color.WHITE);
        TextView bt = (TextView) rootView.findViewById(R.id.textView6);
        bt.setText("100");
        bt.setTextColor(Color.BLACK);
        Toast.makeText(this.getContext(), "Вы можете увидеть пройденныеквесты на вкладке 'Пройденные'", Toast.LENGTH_LONG).show();
        pbHorizontalAllBuild = (ProgressBar) rootView.findViewById(R.id.progressBar);
        pbHorizontalHotel = (ProgressBar) rootView.findViewById(R.id.progressBar2);
        pbHorizontalSince = (ProgressBar) rootView.findViewById(R.id.progressBar3);
        pbHorizontalCulture = (ProgressBar) rootView.findViewById(R.id.progressBar4);
        databaseHelper = new DBHelper(this.getContext());
        textViewAll = (TextView) rootView.findViewById(R.id.textView);
        textViewAll.setTextColor(Color.BLACK);
        textViewHotel = (TextView) rootView.findViewById(R.id.textView3);
        textViewHotel.setTextColor(Color.BLACK);
        textViewSince = (TextView) rootView.findViewById(R.id.textView5);
        textViewSince.setTextColor(Color.BLACK);
        textViewCulture = (TextView) rootView.findViewById(R.id.textView4);
        textViewCulture.setTextColor(Color.BLACK);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        db = databaseHelper.getReadableDatabase();
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pbHorizontalAllBuild.setMax(getMaxCount(""));
        pbHorizontalAllBuild.setProgress(getVisitedCount(""));
        textViewAll.setText("Посещенные объекты" + getVisitedCount("") + "/"+ getMaxCount(""));

        pbHorizontalHotel.setMax(getMaxCount("WHERE class = 'Общежитие'"));
        pbHorizontalHotel.setProgress(getVisitedCount("class = 'Общежитие' AND"));
        textViewHotel.setText("Посещено общежитий "+ getVisitedCount("class = 'Общежитие' AND") +"/"+ getMaxCount("WHERE class = 'Общежитие'"));

        pbHorizontalSince.setMax(getMaxCount("WHERE class = 'Корпус'"));
        pbHorizontalSince.setProgress(getVisitedCount("class = 'Корпус' AND"));
        textViewSince.setText("Посещено корпусов "+ getVisitedCount("class = 'Корпус' AND") +"/" + getMaxCount("WHERE class = 'Корпус'") );

        pbHorizontalCulture.setMax(getMaxCount("WHERE class = 'Культура'"));
        pbHorizontalCulture.setProgress(getVisitedCount("class = 'Культура' AND"));
        textViewCulture.setText("Посещено кульурных объектов "+ getVisitedCount("class = 'Культура' AND") +"/"+ getMaxCount("WHERE class = 'Культура'"));

        databaseHelper.close();
    }


    public Integer getMaxCount(String buildClass) {
        String a = "SELECT COUNT() FROM DT " + buildClass + ";";
        userCursor = db.rawQuery(a, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        // создаем адаптер, передаем в него курсор
        userCursor.moveToFirst();
        String b = userCursor.getString(0);
        return Integer.parseInt(userCursor.getString(0));
    }
    public Integer getVisitedCount(String buildClass) {
        String a = "SELECT COUNT() FROM DT WHERE " + buildClass + " visited = '+'";
        userCursor = db.rawQuery(a, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        // создаем адаптер, передаем в него курсор
        userCursor.moveToFirst();
        return Integer.parseInt(userCursor.getString(0));
    }
}

