package com.giocom.sharingit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllItemsFragment all_items_fragment = new AllItemsFragment();
                return all_items_fragment;
            case 1:
                AvailableItemsFragment available_items_fragment = new AvailableItemsFragment();
                return available_items_fragment;
            case 2:
                BorrowedItemsFragment borrowed_items_fragment = new BorrowedItemsFragment();
                return borrowed_items_fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3; // Three pages
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Items";
            case 1:
                return "Disponible";
            case 2:
                return "Prestado";
        }
        return null;
    }
}