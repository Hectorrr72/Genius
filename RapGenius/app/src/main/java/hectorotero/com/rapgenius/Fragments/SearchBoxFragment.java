package hectorotero.com.rapgenius.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import hectorotero.com.rapgenius.Interfaces.OnSearchPerformed;
import hectorotero.com.rapgenius.R;


/**
 * Created by hectoroteromediero on 05/11/14.
 */
public class SearchBoxFragment extends Fragment {

    EditText searchBarET;
    Button searchButton;
    OnSearchPerformed onSearchPerformed;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_box_fragment, container, false);

        searchBarET = (EditText) view.findViewById(R.id.searchBar);
        searchBarET.setText("");
        searchButton = (Button) view.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!searchBarET.getText().toString().equals("")) {

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchBarET.getWindowToken(), 0);
                    onSearchPerformed.onSearchDone(searchBarET.getText().toString());

                }
                }
        });

        return view;
    }

    public void setOnSearchPerformed(OnSearchPerformed onSearchPerformed) {
        this.onSearchPerformed = onSearchPerformed;
    }


}
