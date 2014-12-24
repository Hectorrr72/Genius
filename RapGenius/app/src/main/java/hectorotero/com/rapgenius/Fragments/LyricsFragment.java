package hectorotero.com.rapgenius.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.telly.groundy.Groundy;
import com.telly.groundy.annotations.OnSuccess;
import com.telly.groundy.annotations.Param;

import hectorotero.com.rapgenius.HelpingClasses.HtmlLyricsTask;
import hectorotero.com.rapgenius.HelpingClasses.URLTextClick;
import hectorotero.com.rapgenius.Interfaces.OnExplanationAsked;
import hectorotero.com.rapgenius.R;

/**
 * Created by hectoroteromediero on 24/12/14.
 */
public class LyricsFragment extends Fragment {


    TextView textView;
    String URL;
    View completeView;
    View progressView;
    ScrollView wholeTextView;
    OnExplanationAsked onExplanationAsked;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (completeView == null) {

            completeView = inflater.inflate(R.layout.base_text_fragment, container, false);
            progressView = completeView.findViewById(R.id.lyrics_progress_view);
            wholeTextView = (ScrollView) completeView.findViewById(R.id.lyrics_view);

            textView = (TextView) completeView.findViewById(R.id.lyrics_text_view);

            Bundle data = getArguments();
            setURL(data.getString("URL"));

            Groundy.create(HtmlLyricsTask.class).callback(this).arg("url", getURL()).queueUsing(this.getActivity());

        }

        return completeView;
    }

    @OnSuccess(HtmlLyricsTask.class)
    public void onLyricsLoaded(@Param("Lyrics") String html) {

        progressView.setVisibility(View.GONE);
        wholeTextView.setVisibility(View.VISIBLE);

        textView.setText(Html.fromHtml(html, new ResourceImageGetter(this.getActivity()), null));
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        CharSequence text = textView.getText();
        if(text instanceof Spannable){
            int end = text.length();
            Spannable sp = (Spannable)textView.getText();
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.clearSpans();
            for(URLSpan url : urls){
                URLTextClick click = new URLTextClick(url.getURL(), this);
                style.setSpan(click, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            textView.setText(style);
        }

    }

    public void setNewURL(String URL) {

        progressView.setVisibility(View.VISIBLE);
        wholeTextView.setVisibility(View.INVISIBLE);
        wholeTextView.scrollTo(0, 0);
        setURL(URL);

        Groundy.create(HtmlLyricsTask.class).callback(this).arg("url", URL).queueUsing(this.getActivity());

    }

    public void receivedAClick(String URL){

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


}
