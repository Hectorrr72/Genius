package hectorotero.com.rapgenius.Adapters;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

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

    @Override
    public int getItemPosition(Object object) {

        return super.getItemPosition(object);
    }

    public ArrayList<Fragment> getFragmentArrayList() {
        return fragmentArrayList;
    }

    public void replaceFragment(int index, Fragment fragment){
        fragmentArrayList.set(index, fragment);
        this.notifyDataSetChanged();

    }

    public void addFragment(Fragment fragment){

        fragmentArrayList.add(fragment);
        this.notifyDataSetChanged();

    }



}
