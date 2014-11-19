package hectorotero.com.rapgenius;



import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;


import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity implements OnSearchPerformed, OnItemSelected {

    SearchFragment searchFragment;
    SearchResultFragment searchResultFragment;
    LyricsView lyricsView;
    ViewPager myViewPager;
    MyPagerAdapter myPagerAdapter;

    final String URL_BEGINNING = "http://api.genius.com/search?q=";
    JSONComplete JSONComplete;
    ArrayList<JSONHit> hitsArrayList = new ArrayList<JSONHit>();
    ArrayList<String> resultArrayList;
    Gson gson;
    String completeURL;
    JSONResponse response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewPager = (ViewPager) findViewById(R.id.pager);
        myPagerAdapter = new MyPagerAdapter(getFragmentManager());

        searchFragment = new SearchFragment();
        myViewPager.setAdapter(myPagerAdapter);
        myPagerAdapter.getFragmentArrayList().add(searchFragment);
        myPagerAdapter.notifyDataSetChanged();
        myViewPager.setCurrentItem(0);

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

        Ion.with(this).load(completeURL).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String json) {

                Log.v("JSon", json);
                JSONComplete = gson.fromJson(json, JSONComplete.class);
                response = JSONComplete.getJSONResponse();

                if (response != null) {

                    hitsArrayList = (ArrayList<JSONHit>) response.getJSONHits();
                    resultArrayList = new ArrayList<String>();

                    for (int i = 0; i < hitsArrayList.size(); i++) {

                        resultArrayList.add(i, hitsArrayList.get(i).getJSONResult().getJSONPrimaryArtist().getName() +
                                "-" + hitsArrayList.get(i).getJSONResult().getTitle());

                    }

                    Bundle args = new Bundle();
                    args.putStringArrayList("responseArrayList", resultArrayList);

                    searchResultFragment = new SearchResultFragment();
                    searchResultFragment.setArguments(args);
                    if (myPagerAdapter.getFragmentArrayList().size() < 2) {
                        myPagerAdapter.getFragmentArrayList().add(searchResultFragment);

                    } else {
                        myPagerAdapter.getFragmentArrayList().set(1, searchResultFragment);
                    }
                    myPagerAdapter.notifyDataSetChanged();
                    myViewPager.setCurrentItem(1);

                }
            }
        });



    }

    @Override
    public void onItemSelection(String URL) {

        lyricsView = new LyricsView();
        Bundle bundle = new Bundle();
        bundle.putString("URL", URL);
        lyricsView.setArguments(bundle);

        if (myPagerAdapter.getFragmentArrayList().size()<3){
            myPagerAdapter.getFragmentArrayList().add(lyricsView);

        }else{
            myPagerAdapter.getFragmentArrayList().set(2, lyricsView);
        }
        myPagerAdapter.notifyDataSetChanged();
        myViewPager.setCurrentItem(2);

    }

}