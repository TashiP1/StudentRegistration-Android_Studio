package com.example.studentregistration.ExitStudent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.studentregistration.NewStudent.NewRegistration;
import com.example.studentregistration.NewStudent.Payment;

public class ourFragmentAdapter extends FragmentStateAdapter {

    com.example.studentregistration.NewStudent.NewRegistration NewRegistration = new NewRegistration();

    public ourFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new Payment();
        }
        return new ExitRegistration();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
