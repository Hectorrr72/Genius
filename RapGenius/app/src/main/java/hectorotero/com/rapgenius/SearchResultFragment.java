package hectorotero.com.rapgenius;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hectoroteromediero on 05/11/14.
 */
public class SearchResultFragment extends Fragment {

    ListView listView;
    ArrayList<String> searchResult;
    MyBaseAdapter myBaseAdapter;
    String baseURL = "http://genius.com/";
    String lyricsURL = "-lyrics";

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

                String middleURL = getProperURL(searchResult.get(position));
                ((OnItemSelected) getActivity()).onItemSelection(baseURL+middleURL+lyricsURL);

            }
        });

        return view;

    }

    String getProperURL(String malformedURL) {

        malformedURL = malformedURL.replace(" ", "-").replace("'", "").replace("’", "").replace(".", "").replace(":", "")
                .replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
        return malformedURL;
    }

}

