package hectorotero.com.rapgenius;



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
import hectorotero.com.rapgenius.Interfaces.OnItemSelected;
import hectorotero.com.rapgenius.Interfaces.OnSearchPerformed;
import hectorotero.com.rapgenius.JsonRelated.CompleteJSON;
import hectorotero.com.rapgenius.JsonRelated.Hit;
import hectorotero.com.rapgenius.JsonRelated.Response;


public class MainActivity extends FragmentActivity implements OnSearchPerformed, OnItemSelected {

    SearchFragment searchFragment;
    SearchResultFragment searchResultFragment;
    LyricsView lyricsView;
    ViewPager myViewPager;
    MyPagerAdapter myPagerAdapter;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();

    final String URL_BEGINNING = "http://api.genius.com/search?q=";
    CompleteJSON JSONComplete;
    ArrayList<Hit> hitsArrayList = new ArrayList<Hit>();
    ArrayList<String> resultArrayList;
    Gson gson;
    String completeURL;
    Response response;

    OnItemSelected onItemSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewPager = (ViewPager) findViewById(R.id.pager);
        onItemSelected = this;

        searchFragment = new SearchFragment();
        searchFragment.setOnSearchPerformed(this);
        fragmentArrayList.add(searchFragment);
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
                    resultArrayList = new ArrayList<String>();

                    for (int i = 0; i < hitsArrayList.size(); i++) {

                        resultArrayList.add(i, hitsArrayList.get(i).getResult().getPrimaryArtist().getName() +
                                "-" + hitsArrayList.get(i).getResult().getTitle());

                    }

                    if (fragmentArrayList.size() < 2){

                        Bundle args = new Bundle();
                        args.putStringArrayList("responseArrayList", resultArrayList);

                        searchResultFragment = new SearchResultFragment();
                        searchResultFragment.setArguments(args);
                        searchResultFragment.setOnItemSelected(onItemSelected);
                        myPagerAdapter.addFragment(searchResultFragment);

                    }else{

                        searchResultFragment.changeListDisplayed(resultArrayList);

                    }


                    myViewPager.setCurrentItem(1);

                }
            }
        });



    }

    @Override
    public void onItemSelection(String URL) {

        if (fragmentArrayList.size() < 3){
            lyricsView = new LyricsView();
            Bundle bundle = new Bundle();
            bundle.putString("URL", URL);
            lyricsView.setArguments(bundle);
            myPagerAdapter.addFragment(lyricsView);

        }else{
            lyricsView.setNewURL(URL);

        }
        myViewPager.setCurrentItem(2);


    }

}