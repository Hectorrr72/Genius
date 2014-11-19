package hectorotero.com.rapgenius;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



/**
 * Created by hectoroteromediero on 05/11/14.
 */
public class SearchFragment extends Fragment {

    EditText searchBarET;
    Button searchButton;


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

                    ((OnSearchPerformed)getActivity()).onSearchDone(searchBarET.getText().toString());

                }
                }
        });

        return view;
    }

}
