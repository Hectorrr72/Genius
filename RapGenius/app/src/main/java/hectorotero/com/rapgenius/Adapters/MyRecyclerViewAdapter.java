package hectorotero.com.rapgenius.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hectorotero.com.rapgenius.Interfaces.OnItemSelected;
import hectorotero.com.rapgenius.R;

/**
 * Created by hectoroteromediero on 30/12/14.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    ArrayList<String> titlesArrayList;
    ArrayList<String> artistsArrayList;
    public static OnItemSelected onItemSelected;


    public MyRecyclerViewAdapter(ArrayList<String> titlesArrayList, ArrayList<String> artistsArrayList) {

        this.titlesArrayList = titlesArrayList;
        this.artistsArrayList = artistsArrayList;
    }


    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);

        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder viewHolder, int position) {

         viewHolder.artist_text_view.setText(artistsArrayList.get(position));
         viewHolder.title_text_view.setText(titlesArrayList.get(position));
         viewHolder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return titlesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title_text_view;
        public TextView artist_text_view;
        String baseURL = "http://genius.com/";
        String middleURL;
        String lyricsURL = "-lyrics";

        public ViewHolder(View view) {

           super(view);
           view.setOnClickListener(this);
           title_text_view = (TextView) view.findViewById(R.id.singleTitleTextView);
           artist_text_view = (TextView) view.findViewById(R.id.singleArtistTextView);

        }

        @Override
        public void onClick(View v) {


            String title = title_text_view.getText().toString();
            String artist = artist_text_view.getText().toString();

            middleURL = getProperURL(artist+"-"+title);

            Log.v("URL", baseURL+middleURL+lyricsURL);

            MyRecyclerViewAdapter.onItemSelected.onItemSelection(baseURL + middleURL + lyricsURL, title, artist);


        }

        String getProperURL(String malformedURL) {

            malformedURL = malformedURL.replace(" ", "-").replace("'", "").replace("’", "").replace(".", "").replace(":", "-")
                    .replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").replace("?","")
                    .replace("(","").replace(")","");


            return malformedURL;
        }
    }

    public void setList(ArrayList<String> titlesArrayList, ArrayList<String> artistsArrayList) {

        this.titlesArrayList = titlesArrayList;
        this.artistsArrayList = artistsArrayList;
        notifyDataSetChanged();

    }



    public void setOnItemSelected(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }


}
