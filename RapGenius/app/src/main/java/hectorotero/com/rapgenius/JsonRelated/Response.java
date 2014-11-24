package hectorotero.com.rapgenius.JsonRelated;

/**
 * Created by hectoroteromediero on 04/11/14.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

import hectorotero.com.rapgenius.JsonRelated.Hit;

@Generated("org.jsonschema2pojo")
public class Response {

    @Expose
    private List<Hit> hits = new ArrayList<Hit>();

    /**
     *
     * @return
     *     The hits
     */
    public List<Hit> getHits() {
        return hits;
    }

    /**
     *
     * @param hits
     *     The hits
     */
    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

}
