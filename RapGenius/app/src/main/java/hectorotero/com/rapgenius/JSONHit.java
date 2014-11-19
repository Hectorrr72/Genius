package hectorotero.com.rapgenius;

/**
 * Created by hectoroteromediero on 04/11/14.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class JSONHit {

    @Expose
    private JSONHighlights JSONHighlights;
    @Expose
    private JSONResult JSONResult;

    /**
     *
     * @return
     *     The highlights
     */
    public JSONHighlights getJSONHighlights() {
        return JSONHighlights;
    }

    /**
     *
     * @param JSONHighlights
     *     The highlights
     */
    public void setJSONHighlights(JSONHighlights JSONHighlights) {
        this.JSONHighlights = JSONHighlights;
    }

    /**
     *
     * @return
     *     The result
     */
    public JSONResult getJSONResult() {
        return JSONResult;
    }

    /**
     *
     * @param JSONResult
     *     The result
     */
    public void setJSONResult(JSONResult JSONResult) {
        this.JSONResult = JSONResult;
    }

}
