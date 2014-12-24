package hectorotero.com.rapgenius.HelpingClasses;

import android.util.Log;

import com.telly.groundy.GroundyTask;
import com.telly.groundy.TaskResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by hectoroteromediero on 25/11/14.
 */
public class HtmlLyricsTask extends GroundyTask{
    String url;
    Document document;
    String text;
    @Override
    protected TaskResult doInBackground() {

        url = getStringArg("url");
        Log.v("URL", url);
        try {
            document = Jsoup.connect(url).get();

            document.outputSettings(new Document.OutputSettings().prettyPrint(false));

            Element element = document.select("p").first();

            text = element.html().replace("href=\"", "href=\"http://genius.com");

            return succeeded().add("Lyrics", text);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return succeeded().add("Lyrics", "There has been a problem when loading the information");

    }
}
