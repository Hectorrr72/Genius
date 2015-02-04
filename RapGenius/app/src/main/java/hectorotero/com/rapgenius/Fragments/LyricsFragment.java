package hectorotero.com.rapgenius.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.telly.groundy.Groundy;
import com.telly.groundy.annotations.OnSuccess;
import com.telly.groundy.annotations.Param;

import hectorotero.com.rapgenius.HelpingClasses.HtmlLyricsTask;
import hectorotero.com.rapgenius.HelpingClasses.URLImageParser;
import hectorotero.com.rapgenius.HelpingClasses.URLTextClick;
import hectorotero.com.rapgenius.Interfaces.BackPressed;
import hectorotero.com.rapgenius.Interfaces.OnExplanationAsked;
import hectorotero.com.rapgenius.R;

/**
 * Created by hectoroteromediero on 24/12/14.
 */
public class LyricsFragment extends Fragment {

    TextView artistTextView;
    TextView titleTextView;

    String titleName;
    String artistName;
    String URL = "";

    TextView lyricsTextView;
    View rootView;
    View progressView;
    View wholeTextView;
    OnExplanationAsked onExplanationAsked;
    TextView song_toolbar_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.lyrics_fragment, container, false);

        rootView.setTag("LYRICS_TAG");

        progressView = rootView.findViewById(R.id.lyrics_progress_view);
        wholeTextView = rootView.findViewById(R.id.lyrics_relative_view);

        lyricsTextView = (TextView) rootView.findViewById(R.id.lyrics_text_view);


        Bundle data = getArguments();
        setTitleName(data.getString("titleName"));
        setArtistName(data.getString("artistName"));
        setURL(data.getString("URL"));

        song_toolbar_tv = (TextView) getActivity().findViewById(R.id.song_toolbar_title);
        song_toolbar_tv.setText(titleName+" - "+artistName);

        Groundy.create(HtmlLyricsTask.class).callback(this).arg("url", getURL()).queueUsing(this.getActivity());

        return rootView;
    }

    @OnSuccess(HtmlLyricsTask.class)
    public void onLyricsLoaded(@Param("Lyrics") String html) {

        progressView.setVisibility(View.GONE);
        wholeTextView.setVisibility(View.VISIBLE);


        URLImageParser urlImageParser = new URLImageParser(lyricsTextView, this.getActivity());
        lyricsTextView.setText(Html.fromHtml(html, urlImageParser, null));
        lyricsTextView.setMovementMethod(LinkMovementMethod.getInstance());

        CharSequence text = lyricsTextView.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable sp = (Spannable) lyricsTextView.getText();
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.clearSpans();
            for (URLSpan url : urls) {
                URLTextClick click = new URLTextClick(url.getURL(), this);
                style.setSpan(click, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            lyricsTextView.setText(style);
        }

    }

    public void setNewURL(String URL) {

        progressView = rootView.findViewById(R.id.lyrics_progress_view);
        progressView.setVisibility(View.VISIBLE);
        wholeTextView.setVisibility(View.INVISIBLE);
        wholeTextView.scrollTo(0, 0);
        setURL(URL);

        Groundy.create(HtmlLyricsTask.class).callback(this).arg("url", URL).queueUsing(this.getActivity());

    }

    public void receivedAClick(String URL) {

        this.onExplanationAsked.onLyricsExplanationRequired(URL);

    }

    public void setOnExplanationAsked(OnExplanationAsked onExplanationAsked) {
        this.onExplanationAsked = onExplanationAsked;
    }


    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setTitleName(String titleName) {

        this.titleName = titleName;
    }

    public void setArtistName(String artistName) {

        this.artistName = artistName;


    }


}
