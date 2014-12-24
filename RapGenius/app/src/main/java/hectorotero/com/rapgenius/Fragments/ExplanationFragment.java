package hectorotero.com.rapgenius.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;

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
import hectorotero.com.rapgenius.HelpingClasses.HtmlLyricsTask;
import hectorotero.com.rapgenius.Interfaces.OnExplanationAsked;
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

        if (completeView == null) {

            completeView = inflater.inflate(R.layout.base_text_fragment, container, false);
            progressView = completeView.findViewById(R.id.lyrics_progress_view);
            wholeTextView = (ScrollView) completeView.findViewById(R.id.lyrics_view);

            textView = (TextView) completeView.findViewById(R.id.lyrics_text_view);

            Bundle data = getArguments();
            setURL(data.getString("URL"));

            Groundy.create(HtmlExplanationTask.class).callback(this).arg("url", getURL()).queueUsing(this.getActivity());

        }

        return completeView;
    }

    @OnSuccess(HtmlExplanationTask.class)
    public void onExplanationLoaded(@Param("Explanation") String html) {

        progressView.setVisibility(View.GONE);
        wholeTextView.setVisibility(View.VISIBLE);

        textView.setText(Html.fromHtml(html, new ResourceImageGetter(this.getActivity()), null));
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void setNewURL(String URL) {

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

