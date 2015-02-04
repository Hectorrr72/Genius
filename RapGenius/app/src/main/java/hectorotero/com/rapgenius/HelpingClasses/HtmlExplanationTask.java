package hectorotero.com.rapgenius.HelpingClasses;

import android.util.Log;

import com.telly.groundy.GroundyTask;
import com.telly.groundy.TaskResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import hectorotero.com.rapgenius.R;

/**
 * Created by hectoroteromediero on 23/12/14.
 */
public class HtmlExplanationTask extends GroundyTask{

    String url;
    Document document;
    String text;
    Elements images;

    @Override
    protected TaskResult doInBackground() {

        url = getStringArg("url");
        Log.v("URL", url);
        try {
            document = Jsoup.connect(url).get();

            document.outputSettings(new Document.OutputSettings().prettyPrint(false));

            text = document.select("p").toString();

            Log.v("TEXT", text);

            return succeeded().add("Explanation", text);



        } catch (IOException e) {
            e.printStackTrace();
        }

        return succeeded().add("Explanation", "There has been a problem when loading the information");

    }
}
