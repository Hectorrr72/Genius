package hectorotero.com.rapgenius.Activities;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import hectorotero.com.rapgenius.Adapters.MyPagerAdapter;
import hectorotero.com.rapgenius.Fragments.ExplanationFragment;
import hectorotero.com.rapgenius.Fragments.LyricsFragment;
import hectorotero.com.rapgenius.Interfaces.OnExplanationAsked;
import hectorotero.com.rapgenius.R;

/**
 * Created by hectoroteromediero on 21/01/15.
 */
public class SongActivity extends ActionBarActivity implements OnExplanationAsked {

    LyricsFragment lyricsFragment;
    ExplanationFragment explanationFragment;

    ViewPager myViewPager;
    MyPagerAdapter myPagerAdapter;
    OnExplanationAsked onExplanationAsked;

    String URL = "";
    String titleName;
    String artistName;
    Toolbar toolbar;
    TextView title_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_activity);

        toolbar = (Toolbar) findViewById(R.id.my_song_toolbar);
        toolbar.setTitle("");
        title_text_view = (TextView) toolbar.findViewById(R.id.main_toolbar_title);
        setSupportActionBar(toolbar);

        onExplanationAsked = this;

        Intent intent = getIntent();

        if (myPagerAdapter==null)
            myPagerAdapter = new MyPagerAdapter(getFragmentManager(), new ArrayList<Fragment>());

        if (myViewPager == null){
            myViewPager = (ViewPager) findViewById(R.id.pager);
            myViewPager.setAdapter(myPagerAdapter);
        }

        Log.v("argURL", URL+"9909");
        Log.v("intentURL", intent.getStringExtra("URL"));
        if (!URL.equals(intent.getStringExtra("URL"))){

            URL = intent.getStringExtra("URL");
            titleName = intent.getStringExtra("titleName");
            artistName = intent.getStringExtra("artistName");

            if (myPagerAdapter.getCount()==0) {

                lyricsFragment = new LyricsFragment();
                lyricsFragment.setOnExplanationAsked(onExplanationAsked);

                Bundle bundle = new Bundle();
                bundle.putString("URL", URL);
                bundle.putString("titleName", titleName);
                bundle.putString("artistName", artistName);

                lyricsFragment.setArguments(bundle);
                myPagerAdapter.addFragment(lyricsFragment);

            }else {

                if (myPagerAdapter.getCount() == 2) {
                    myPagerAdapter.removeFragment(1);
                }

                lyricsFragment.setNewURL(URL);
                lyricsFragment.setTitleName(titleName);
                lyricsFragment.setArtistName(artistName);

            }

        }

        myViewPager.setCurrentItem(0);

    }

    @Override
    public void onLyricsExplanationRequired(String URL) {

        if (myPagerAdapter.getCount() < 2) {
            explanationFragment = new ExplanationFragment();
            Bundle bundle = new Bundle();
            bundle.putString("URL", URL);
            explanationFragment.setArguments(bundle);
            myPagerAdapter.addFragment(explanationFragment);

        } else {

            if (!explanationFragment.getURL().equals(URL))
                explanationFragment.setNewURL(URL);
        }

        myViewPager.setCurrentItem(1);

    }

    @Override
    public void onBackPressed() {
        if(myViewPager.getCurrentItem()==0){

           super.onBackPressed();

        }else if (myViewPager.getCurrentItem()==1){

            myViewPager.setCurrentItem(0);

        }
    }


}
