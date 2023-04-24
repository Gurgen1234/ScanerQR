package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragmentAchievements extends FragmentBase{

    ProgressBar pbHorizontal;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState )
    {
        View rootView = inflater.inflate(R.layout.fragment_achievements, container, false );
         pbHorizontal = (ProgressBar) rootView.findViewById(R.id.progressBar);

         pbHorizontal.setMax(30);
         pbHorizontal.setProgress(22);
        return rootView;
    }

}
