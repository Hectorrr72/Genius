package hectorotero.com.rapgenius.HelpingClasses;


import android.content.Context;
import android.text.style.ClickableSpan;
import android.view.View;
import hectorotero.com.rapgenius.Fragments.LyricsFragment;

/**
 * Created by hectoroteromediero on 23/12/14.
 */
public class URLTextClick extends ClickableSpan{

    private String URL;
    private LyricsFragment fragment;

    public URLTextClick(String URL, LyricsFragment fragment) {
        this.URL = URL;
        this.fragment = fragment;
    }

    @Override
    public void onClick(View widget) {

        fragment.receivedAClick(URL);

    }
}
