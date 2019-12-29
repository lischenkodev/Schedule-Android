package ru.melod1n.schedule.adapter;


import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import ru.melod1n.schedule.R;
import ru.melod1n.schedule.fragment.ScheduleFragment;

public class ScheduleParentAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_ITEMS = 6;

    private Context context;

    public ScheduleParentAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        return new ScheduleFragment(position);
    }

    @Override
    public int getItemPosition(@NotNull Object object) {
        return ((ScheduleFragment) object).getDay();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getStringArray(R.array.days)[position];
    }

}
