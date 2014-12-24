package hectorotero.com.rapgenius.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import hectorotero.com.rapgenius.Adapters.MyBaseAdapter;
import hectorotero.com.rapgenius.Interfaces.OnItemSelected;
import hectorotero.com.rapgenius.R;

/**
 * Created by hectoroteromediero on 05/11/14.
 */
public class SearchResultListFragment extends Fragment {

    ListView listView;
    ArrayList<String> searchResult;
    MyBaseAdapter myBaseAdapter;
    String baseURL = "http://genius.com/";
    String lyricsURL = "-lyrics";
    OnItemSelected onItemSelected;
    String middleURL;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_result_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.listView);

        Bundle bundle = getArguments();
        searchResult = bundle.getStringArrayList("responseArrayList");

        myBaseAdapter = new MyBaseAdapter(this.getActivity(), searchResult);
        listView.setAdapter(myBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                middleURL = getProperURL(searchResult.get(position));
                onItemSelected.onItemSelection(baseURL + middleURL + lyricsURL, position);

            }
        });

        return view;

    }

    String getProperURL(String malformedURL) {

        malformedURL = malformedURL.replace(" ", "-").replace("'", "").replace("’", "").replace(".", "").replace(":", "-")
                .replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").replace("?","")
                .replace("(","").replace(")","");


        return malformedURL;
    }


    public void setOnItemSelected(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }

    public void changeListDisplayed(ArrayList<String> newList){

        searchResult = newList;
        myBaseAdapter.setList(newList);
        myBaseAdapter.notifyDataSetChanged();

    }



}

