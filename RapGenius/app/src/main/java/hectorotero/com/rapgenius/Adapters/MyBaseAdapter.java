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

public class MyBaseAdapter extends BaseAdapter {


    ArrayList<String> list;
    Context context;

    public MyBaseAdapter(Context context, ArrayList<String> list) {
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

    public void setList(ArrayList<String> list) {
        this.list = list;
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




