package hectorotero.com.rapgenius.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hectorotero.com.rapgenius.Adapters.MyRecyclerViewAdapter;
import hectorotero.com.rapgenius.HelpingClasses.DividerItemDecoration;
import hectorotero.com.rapgenius.Interfaces.OnItemSelected;
import hectorotero.com.rapgenius.R;

/**
 * Created by hectoroteromediero on 30/12/14.
 */
public class ResultRecyclerViewFragment extends Fragment {

    ArrayList<String> titlesArrayList;
    ArrayList<String> artistsArrayList;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    View view;
    OnItemSelected onItemSelected;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.result_recycler_view_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        Bundle bundle = getArguments();
        titlesArrayList = bundle.getStringArrayList("titlesArrayList");
        artistsArrayList = bundle.getStringArrayList("artistsArrayList");

        mAdapter = new MyRecyclerViewAdapter(titlesArrayList, artistsArrayList);
        mAdapter.setOnItemSelected(onItemSelected);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }


    public void setOnItemSelected(OnItemSelected onItemSelected) {

        this.onItemSelected = onItemSelected;
    }

    public void changeListDisplayed(ArrayList<String> titlesNewList, ArrayList<String> artistsNewList){

        this.titlesArrayList = titlesNewList;
        this.artistsArrayList = artistsNewList;

        mAdapter.setList(titlesNewList,artistsNewList);

    }

}
