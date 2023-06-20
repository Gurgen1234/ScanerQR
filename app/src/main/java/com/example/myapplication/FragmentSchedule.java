package com.example.myapplication;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.loader.AssetsProvider;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;

public class FragmentSchedule extends FragmentBase {
    private static String filePath = null;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "filePath";

    @SuppressLint({"SuspiciousIndentation", "UseCompatLoadingForDrawables"})
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        TextView text = (TextView) root.findViewById(R.id.textView2);
        text.setText("Добро пожаловать!");
        text.setTextSize(18);
        text.setGravity(1);
        Button button = (Button) root.findViewById(R.id.button);
        ImageView img = (ImageView) root.findViewById(R.id.imageView);
        img.setImageDrawable(root.getContext().getDrawable(R.drawable.hello));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.timacad.ru/about/sveden/document/rezhim-zaniatii-obuchaiushchikhsia"));
                activity.startActivity(intent);
            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Запоминаем данные

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

    }
}
