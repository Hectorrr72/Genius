package hectorotero.com.rapgenius;

/**
 * Created by hectoroteromediero on 04/11/14.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Hit {

    @Expose
    private Highlights highlights;
    @Expose
    private Result result;

    /**
     *
     * @return
     *     The highlights
     */
    public Highlights getHighlights() {
        return highlights;
    }

    /**
     *
     * @param highlights
     *     The highlights
     */
    public void setHighlights(Highlights highlights) {
        this.highlights = highlights;
    }

    /**
     *
     * @return
     *     The result
     */
    public Result getResult() {
        return result;
    }

    /**
     *
     * @param result
     *     The result
     */
    public void setResult(Result result) {
        this.result = result;
    }

}
