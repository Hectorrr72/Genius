package hectorotero.com.rapgenius.Adapters;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import hectorotero.com.rapgenius.Fragments.ExplanationFragment;
import hectorotero.com.rapgenius.Fragments.LyricsFragment;

/**
 * Created by hectoroteromediero on 19/11/14.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentArrayList;

    public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList) {

        super(fm);
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentArrayList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position);
    }


    public void replaceFragment(int position, Fragment fragment) {

        if (position < fragmentArrayList.size())
            fragmentArrayList.set(position, fragment);

        this.notifyDataSetChanged();

    }

    public void addFragment(Fragment fragment) {
        fragmentArrayList.add(fragment);
        this.notifyDataSetChanged();
    }

    public void removeFragment(int position) {
        fragmentArrayList.remove(position);
        this.notifyDataSetChanged();

    }

    @Override
    public int getItemPosition(Object object) {
        if (object.equals(fragmentArrayList.get(0))
        ||object.equals(fragmentArrayList.get(1))){
            return PagerAdapter.POSITION_UNCHANGED;
        }

        if (object instanceof LyricsFragment){

            Log.v("HEY", "We made it");
            return 2;
        }

        if (object instanceof ExplanationFragment)
            return 3;

        return PagerAdapter.POSITION_NONE;
    }


}
