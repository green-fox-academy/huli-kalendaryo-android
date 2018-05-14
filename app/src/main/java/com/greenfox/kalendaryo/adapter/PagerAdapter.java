package com.greenfox.kalendaryo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.greenfox.kalendaryo.fragments.AccountsFragment;
import com.greenfox.kalendaryo.fragments.KalendarFragment;

/**
 * Created by nezih on 09-Jan-18.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                KalendarFragment tab1 = new KalendarFragment();
                return tab1;
            case 1:
                AccountsFragment tab2 = new AccountsFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
