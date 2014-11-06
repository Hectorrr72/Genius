package hectorotero.com.rapgenius;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_result_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.listView);

        Bundle bundle = getArguments();
        searchResult = bundle.getStringArrayList("responseArrayList");

        myBaseAdapter = new MyBaseAdapter(this.getActivity(), searchResult);
        listView.setAdapter(myBaseAdapter);

        return view;

    }

private class MyBaseAdapter extends BaseAdapter {

    ArrayList<String> list;
    Context context;

    private MyBaseAdapter(Context context, ArrayList<String> list) {
        super();
        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.single_item, parent, false);

        }

        TextView singleName = (TextView) convertView.findViewById(R.id.itemTextView);

        singleName.setText(list.get(position));

        return convertView;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

}


}


