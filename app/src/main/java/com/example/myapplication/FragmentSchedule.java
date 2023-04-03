package com.example.myapplication;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

public class FragmentSchedule extends FragmentBase {
    private static String filePath = null;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "filePath";

    @SuppressLint("SuspiciousIndentation")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        Button button = (Button) root.findViewById(R.id.button);
        if (filePath != null){
            button.setVisibility(View.GONE);
        }
        mSettings =
                activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("text/*");
                startActivityForResult(intent, 1001);


            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
            // Получаем число из настроек
            filePath = mSettings. getString(APP_PREFERENCES_COUNTER, filePath);
            // Выводим на экран данные из настроек

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Запоминаем данные
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_COUNTER, filePath);
        editor.apply();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            if (resultData != null) {
                filePath = resultData.getData().toString();
            }
        }
    }

    @Override
    public void onResume(View view) {

    }
}
