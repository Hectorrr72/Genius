package hectorotero.com.rapgenius;

/**
 * Created by hectoroteromediero on 04/11/14.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class JSONComplete {

    @Expose
    private JSONResponse JSONResponse;
    @Expose
    private JSONMeta JSONMeta;

    /**
     *
     * @return
     *     The response
     */
    public JSONResponse getJSONResponse() {
        return JSONResponse;
    }

    /**
     *
     * @param JSONResponse
     *     The response
     */
    public void setJSONResponse(JSONResponse JSONResponse) {
        this.JSONResponse = JSONResponse;
    }

    /**
     *
     * @return
     *     The meta
     */
    public JSONMeta getJSONMeta() {
        return JSONMeta;
    }

    /**
     *
     * @param JSONMeta
     *     The meta
     */
    public void setJSONMeta(JSONMeta JSONMeta) {
        this.JSONMeta = JSONMeta;
    }

}

