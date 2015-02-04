package hectorotero.com.rapgenius.Activities;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;


import hectorotero.com.rapgenius.Adapters.MyRecyclerViewAdapter;
import hectorotero.com.rapgenius.Fragments.ResultRecyclerViewFragment;
import hectorotero.com.rapgenius.Interfaces.OnItemSelected;
import hectorotero.com.rapgenius.Interfaces.OnSearchPerformed;
import hectorotero.com.rapgenius.JsonRelated.CompleteJSON;
import hectorotero.com.rapgenius.JsonRelated.Hit;
import hectorotero.com.rapgenius.JsonRelated.Response;
import hectorotero.com.rapgenius.R;
import hectorotero.com.rapgenius.Fragments.SearchBoxFragment;


public class MainActivity extends ActionBarActivity implements OnSearchPerformed, OnItemSelected{

    SearchBoxFragment searchBoxFragment;
    ResultRecyclerViewFragment resultRecyclerViewFragment;

    final String URL_BEGINNING = "http://api.genius.com/search?q=";
    CompleteJSON JSONComplete;
    ArrayList<Hit> hitsArrayList = new ArrayList<>();
    ArrayList<String> artistsArrayList;
    ArrayList<String> titlesArrayList;
    Gson gson;
    String completeURL;
    Response response;

    OnItemSelected onItemSelected;
    SongActivity songActivity;
    MyRecyclerViewAdapter mAdapter;

    Toolbar toolbar;
    TextView title_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.my_main_toolbar);
        toolbar.setTitle("");

        title_text_view = (TextView) toolbar.findViewById(R.id.main_toolbar_title);

        setSupportActionBar(toolbar);

        onItemSelected = this;
        searchBoxFragment = new SearchBoxFragment();
        searchBoxFragment.setOnSearchPerformed(this);

        getFragmentManager().beginTransaction().add(R.id.fragment_layout, searchBoxFragment).commit();

    }


    @Override
    public void onSearchDone(String URLEnding) {


        URLEnding = URLEnding.trim();
        URLEnding = URLEnding.replace(" ", "+");
        completeURL = URL_BEGINNING + URLEnding;
        Log.v("SearchURL", completeURL);
        gson = new Gson();

        Ion.with(this).load(completeURL).progressBar(new ProgressBar(this)).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String json) {

                Log.v("JSon", json);
                JSONComplete = gson.fromJson(json, CompleteJSON.class);
                response = JSONComplete.getResponse();

                if (response != null) {

                    hitsArrayList = (ArrayList<Hit>) response.getHits();
                    titlesArrayList = new ArrayList<>();
                    artistsArrayList = new ArrayList<>();

                    for (int i = 0; i < hitsArrayList.size(); i++) {

                        artistsArrayList.add(i, hitsArrayList.get(i).getResult().getPrimaryArtist().getName());
                        titlesArrayList.add(i, hitsArrayList.get(i).getResult().getTitle());

                    }

                    Bundle args = new Bundle();

                    args.putStringArrayList("titlesArrayList", titlesArrayList);
                    args.putStringArrayList("artistsArrayList", artistsArrayList);

                    resultRecyclerViewFragment = new ResultRecyclerViewFragment();
                    resultRecyclerViewFragment.setArguments(args);
                    resultRecyclerViewFragment.setOnItemSelected(onItemSelected);


                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_layout, resultRecyclerViewFragment).commit();
                }
            }
        });

    }

    @Override
    public void onItemSelection(String URL, String titleName, String artistName) {


        Intent intent = new Intent(this, SongActivity.class);

        intent.putExtra("URL", URL);
        intent.putExtra("titleName", titleName);
        intent.putExtra("artistName", artistName);

        startActivity(intent);


    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount()!= 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();

    }

}