package hectorotero.com.rapgenius.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;

import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.telly.groundy.Groundy;
import com.telly.groundy.annotations.OnSuccess;
import com.telly.groundy.annotations.Param;

import hectorotero.com.rapgenius.HelpingClasses.HtmlExplanationTask;

import hectorotero.com.rapgenius.HelpingClasses.URLImageParser;
import hectorotero.com.rapgenius.R;

/**
 * Created by hectoroteromediero on 24/12/14.
 */
public class ExplanationFragment extends Fragment{

    TextView textView;
    String URL;
    View completeView;
    View progressView;
    ScrollView wholeTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            completeView = inflater.inflate(R.layout.explanation_fragment, container, false);
            wholeTextView = (ScrollView) completeView.findViewById(R.id.lyrics_scroll_view);
            progressView = completeView.findViewById(R.id.lyrics_progress_view);

            textView = (TextView) completeView.findViewById(R.id.lyrics_text_view);

            Bundle data = getArguments();
            setURL(data.getString("URL"));

            Groundy.create(HtmlExplanationTask.class).callback(this).arg("url", getURL()).queueUsing(this.getActivity());



        return completeView;
    }

    @OnSuccess(HtmlExplanationTask.class)
    public void onExplanationLoaded(@Param("Explanation") String html) {

        progressView.setVisibility(View.GONE);
        wholeTextView.setVisibility(View.VISIBLE);


        URLImageParser urlImageParser = new URLImageParser(textView, this.getActivity());
        Spanned spanned = Html.fromHtml(html, urlImageParser, null);
        String string = spanned.toString();

        int beginning_of_second_thing = string.indexOf("To help improve");

        textView.setText(spanned.subSequence(0, beginning_of_second_thing));
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void setNewURL(String URL) {


        progressView = completeView.findViewById(R.id.lyrics_progress_view);
        progressView.setVisibility(View.VISIBLE);
        wholeTextView.setVisibility(View.INVISIBLE);
        wholeTextView.scrollTo(0, 0);
        setURL(URL);

        Groundy.create(HtmlExplanationTask.class).callback(this).arg("url", URL).queueUsing(this.getActivity());

    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }


}

