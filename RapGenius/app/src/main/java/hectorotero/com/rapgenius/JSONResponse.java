package hectorotero.com.rapgenius;

/**
 * Created by hectoroteromediero on 04/11/14.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class JSONResponse {

    @Expose
    private List<JSONHit> JSONHits = new ArrayList<JSONHit>();

    /**
     *
     * @return
     *     The hits
     */
    public List<JSONHit> getJSONHits() {
        return JSONHits;
    }

    /**
     *
     * @param JSONHits
     *     The hits
     */
    public void setJSONHits(List<JSONHit> JSONHits) {
        this.JSONHits = JSONHits;
    }

}
