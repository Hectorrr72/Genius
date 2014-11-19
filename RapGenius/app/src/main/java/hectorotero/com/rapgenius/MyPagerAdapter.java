package hectorotero.com.rapgenius;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by hectoroteromediero on 19/11/14.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {


    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
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

    public ArrayList<Fragment> getFragmentArrayList() {
        return fragmentArrayList;
    }


    public void replaceFragment(int index, Fragment fragment){

        fragmentArrayList.set(index, fragment);

    }

}
