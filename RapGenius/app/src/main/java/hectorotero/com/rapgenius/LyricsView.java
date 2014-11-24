package hectorotero.com.rapgenius;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import hectorotero.com.rapgenius.R;

/**
 * Created by hectoroteromediero on 08/11/14.
 */
public class LyricsView extends Fragment {

    WebView wv;
    String URL;
    View view;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null){

            view = inflater.inflate(R.layout.lyrics_view, container, false);


            wv= (WebView) view.findViewById(R.id.webView);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.getSettings().setUseWideViewPort(true);
            wv.getSettings().setLoadWithOverviewMode(true);

            URL = getArguments().getString("URL");

            wv.setWebChromeClient(new WebChromeClient(){

                @Override
                public void onProgressChanged(WebView view, int progress) {
                    super.onProgressChanged(view, progress);

                    if(progress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
                        progressBar.setVisibility(ProgressBar.VISIBLE);
                        progressBar.setProgress(progress);
                    }
                    if(progress == 100) {
                        progressBar.setVisibility(ProgressBar.GONE);
                    }
                }


            });
            WebViewClient webViewClient = new WebViewClient();
            webViewClient.shouldOverrideUrlLoading(wv, URL);
            wv.setWebViewClient(webViewClient);
            wv.loadUrl(URL);


        }

        return view;
    }

    public void setNewURL(String URL) {

        this.URL = URL;
        wv.loadUrl(URL);
    }
}
