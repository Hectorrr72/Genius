package hectorotero.com.rapgenius.Activities;



import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ProgressBar;


import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import hectorotero.com.rapgenius.Adapters.MyPagerAdapter;
import hectorotero.com.rapgenius.Fragments.ExplanationFragment;
import hectorotero.com.rapgenius.Interfaces.OnExplanationAsked;
import hectorotero.com.rapgenius.Interfaces.OnItemSelected;
import hectorotero.com.rapgenius.Interfaces.OnSearchPerformed;
import hectorotero.com.rapgenius.JsonRelated.CompleteJSON;
import hectorotero.com.rapgenius.JsonRelated.Hit;
import hectorotero.com.rapgenius.JsonRelated.Response;
import hectorotero.com.rapgenius.Fragments.LyricsFragment;
import hectorotero.com.rapgenius.R;
import hectorotero.com.rapgenius.Fragments.SearchBoxFragment;
import hectorotero.com.rapgenius.Fragments.SearchResultListFragment;


public class MainActivity extends FragmentActivity implements OnSearchPerformed, OnItemSelected, OnExplanationAsked {

    SearchBoxFragment searchBoxFragment;
    SearchResultListFragment searchResultListFragment;
    LyricsFragment lyricsFragment;
    ExplanationFragment explanationFragment;

    ViewPager myViewPager;
    MyPagerAdapter myPagerAdapter;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();

    final String URL_BEGINNING = "http://api.genius.com/search?q=";
    CompleteJSON JSONComplete;
    ArrayList<Hit> hitsArrayList = new ArrayList<>();
    ArrayList<String> resultArrayList;
    Gson gson;
    String completeURL;
    Response response;

    OnItemSelected onItemSelected;
    OnExplanationAsked onExplanationAsked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewPager = (ViewPager) findViewById(R.id.pager);
        onItemSelected = this;
        onExplanationAsked = this;

        searchBoxFragment = new SearchBoxFragment();
        searchBoxFragment.setOnSearchPerformed(this);
        fragmentArrayList.add(searchBoxFragment);
        myPagerAdapter = new MyPagerAdapter(getFragmentManager(), fragmentArrayList);

        myViewPager.setAdapter(myPagerAdapter);


    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSearchDone(String URLEnding) {

        URLEnding = URLEnding.trim();
        URLEnding = URLEnding.replace(" ", "+");
        completeURL = URL_BEGINNING+URLEnding;
        Log.v("URL", completeURL);
        gson = new Gson();

        Ion.with(this).load(completeURL).progressBar(new ProgressBar(this)).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String json) {

                Log.v("JSon", json);
                JSONComplete = gson.fromJson(json, CompleteJSON.class);
                response = JSONComplete.getResponse();

                if (response != null) {

                    hitsArrayList = (ArrayList<Hit>) response.getHits();
                    resultArrayList = new ArrayList<>();

                    for (int i = 0; i < hitsArrayList.size(); i++) {

                        resultArrayList.add(i, hitsArrayList.get(i).getResult().getPrimaryArtist().getName() +
                                "-" + hitsArrayList.get(i).getResult().getTitle());

                    }

                    if (fragmentArrayList.size() < 2){

                        Bundle args = new Bundle();
                        args.putStringArrayList("responseArrayList", resultArrayList);

                        searchResultListFragment = new SearchResultListFragment();
                        searchResultListFragment.setArguments(args);
                        searchResultListFragment.setOnItemSelected(onItemSelected);
                        myPagerAdapter.addFragment(searchResultListFragment);

                    }else{

                        searchResultListFragment.changeListDisplayed(resultArrayList);

                    }
                    myViewPager.setCurrentItem(1);

                }
            }
        });



    }

    @Override
    public void onItemSelection(String URL, int positionClicked) {

        if (fragmentArrayList.size() < 3){
            lyricsFragment = new LyricsFragment();
            lyricsFragment.setOnExplanationAsked(onExplanationAsked);
            Bundle bundle = new Bundle();
            bundle.putString("URL", URL);
            lyricsFragment.setArguments(bundle);
            myPagerAdapter.addFragment(lyricsFragment);

        }else{

            if(!lyricsFragment.getURL().equals(URL))
                lyricsFragment.setNewURL(URL);
        }
        myViewPager.setCurrentItem(2);


    }

    @Override
    public void onLyricsExplanationRequired(String URL) {

        if (fragmentArrayList.size()<4){
            explanationFragment = new ExplanationFragment();
            Bundle bundle = new Bundle();
            bundle.putString("URL", URL);
            explanationFragment.setArguments(bundle);
            myPagerAdapter.addFragment(explanationFragment);

        }else{

            if(!explanationFragment.getURL().equals(URL))
                explanationFragment.setNewURL(URL);
        }

        myViewPager.setCurrentItem(3);

    }
}