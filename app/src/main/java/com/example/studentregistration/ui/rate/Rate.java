package com.example.studentregistration.ui.rate;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.studentregistration.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

import hotchemi.android.rate.AppRate;


public class Rate extends Fragment {
    View view;
    private ReviewManager reviewManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_rate, container, false);
        AppRate.with(getContext())
                .setInstallDays(0)
                .setLaunchTimes(0)
                .setRemindInterval(0)
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(getActivity());
        AppRate.with(getContext()).clearAgreeShowDialog();

        return view;
    }

}