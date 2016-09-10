package com.mfeldsztejn.rxjavatest.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mfeldsztejn.rxjavatest.main.fragments.PeopleListFragment;
import com.mfeldsztejn.rxjavatest.main.fragments.StartShipsListFragment;

/**
 * Created by mfeldsztejn on 9/5/16.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "People";
            case 1:
                return "Star Ships";
            case 2:
                return "Planets";
        }
        return super.getPageTitle(position);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PeopleListFragment();
            case 1:
                return new StartShipsListFragment();
        }
        return new PeopleListFragment();
    }
}
