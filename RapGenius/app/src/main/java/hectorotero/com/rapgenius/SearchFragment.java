package hectorotero.com.rapgenius;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


import java.util.ArrayList;

/**
 * Created by hectoroteromediero on 05/11/14.
 */
public class SearchFragment extends Fragment {

    EditText searchBarET;
    Button searchButton;
    static final String URLBeginning = "http://api.genius.com/search?q=";
    String URLEnding = "";
    CompleteJSON completeJSON;

    static ArrayList<Hit> hitsArrayList;
    ArrayList<String> resultArrayList;
    Gson gson;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_fragment, container, false);

        searchBarET = (EditText) view.findViewById(R.id.searchBar);
        searchBarET.setText("");
        searchButton = (Button) view.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (searchBarET.getText().toString() != "") {
                    URLEnding = searchBarET.getText().toString();
                    URLEnding.trim();
                    URLEnding = URLEnding.replace(" ", "+");
                    getResultList(URLBeginning + URLEnding);
                 }
                }
        });

        return view;
    }

     void getResultList(String completeURL){

         gson = new Gson();

         Ion.with(getActivity()).load(completeURL).asString().setCallback(new FutureCallback<String>() {
             @Override
             public void onCompleted(Exception e, String s) {

                 completeJSON = gson.fromJson(s, CompleteJSON.class);
                 resultArrayList = new ArrayList<String>();
                 hitsArrayList = (ArrayList<Hit>) completeJSON.getResponse().getHits();

                 if (!hitsArrayList.isEmpty()){

                     for (int i = 0; i<hitsArrayList.size(); i++){

                         resultArrayList.add(i, hitsArrayList.get(i).getResult().getPrimaryArtist().getName() +
                                 "-" + hitsArrayList.get(i).getResult().getTitle());

                     }

                     Bundle args = new Bundle();
                     args.putStringArrayList("responseArrayList",resultArrayList);
                     SearchResultFragment searchResultFragment = new SearchResultFragment();
                     searchResultFragment.setArguments(args);

                     getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main_layout, searchResultFragment).commit();

                 }

             }

         });

    }


}
