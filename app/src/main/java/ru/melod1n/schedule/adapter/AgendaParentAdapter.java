package ru.melod1n.schedule.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import ru.melod1n.schedule.R;
import ru.melod1n.schedule.common.AppGlobal;
import ru.melod1n.schedule.fragment.AgendaFragment;

public class AgendaParentAdapter extends FragmentStatePagerAdapter {

    private ArrayList<AgendaFragment> fragments;

    public AgendaParentAdapter(FragmentManager fragmentManager, ArrayList<AgendaFragment> fragments) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemPosition(@NotNull Object object) {
        return ((AgendaFragment) object).getType();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return AppGlobal.context.getString(position == 0 ? R.string.homework : R.string.events);
    }

}