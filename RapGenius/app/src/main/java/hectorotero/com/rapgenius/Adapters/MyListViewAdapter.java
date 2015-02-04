package hectorotero.com.rapgenius.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hectorotero.com.rapgenius.R;

/**
 * Created by hectoroteromediero on 19/11/14.
 */

public class MyListViewAdapter extends BaseAdapter {


    ArrayList<String> artistsList;
    ArrayList<String> titlesList;
    Context context;
    TextView titleName;
    TextView artistName;

    public MyListViewAdapter(Context context, ArrayList<String> titlesList, ArrayList<String> artistsList) {
        super();
        this.titlesList = titlesList;
        this.artistsList = artistsList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return titlesList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.single_item, parent, false);

        }

        titleName = (TextView) convertView.findViewById(R.id.singleTitleTextView);
        artistName = (TextView) convertView.findViewById(R.id.singleArtistTextView);
        titleName.setText(titlesList.get(position));
        artistName.setText(artistsList.get(position));

        return convertView;

    }

    public void setList(ArrayList<String> titlesList, ArrayList<String> artistsList) {
        this.titlesList = titlesList;
        this.artistsList = artistsList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return titlesList.get(position) + artistsList.get(position);
    }

}




