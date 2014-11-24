package hectorotero.com.rapgenius.JsonRelated;

/**
 * Created by hectoroteromediero on 04/11/14.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import hectorotero.com.rapgenius.JsonRelated.PrimaryArtist;

@Generated("org.jsonschema2pojo")
public class Result {

    @Expose
    private String title;
    @SerializedName("lyrics_updated_at")
    @Expose
    private Integer lyricsUpdatedAt;
    @SerializedName("pyongs_count")
    @Expose
    private Integer pyongsCount;
    @SerializedName("primary_artist")
    @Expose
    private PrimaryArtist primaryArtist;
    @SerializedName("updated_by_human_at")
    @Expose
    private Integer updatedByHumanAt;
    @Expose
    private Integer id;
    @SerializedName("annotation_count")
    @Expose
    private Integer annotationCount;

    /**
     *
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     *     The lyricsUpdatedAt
     */
    public Integer getLyricsUpdatedAt() {
        return lyricsUpdatedAt;
    }

    /**
     *
     * @param lyricsUpdatedAt
     *     The lyrics_updated_at
     */
    public void setLyricsUpdatedAt(Integer lyricsUpdatedAt) {
        this.lyricsUpdatedAt = lyricsUpdatedAt;
    }

    /**
     *
     * @return
     *     The pyongsCount
     */
    public Integer getPyongsCount() {
        return pyongsCount;
    }

    /**
     *
     * @param pyongsCount
     *     The pyongs_count
     */
    public void setPyongsCount(Integer pyongsCount) {
        this.pyongsCount = pyongsCount;
    }

    /**
     *
     * @return
     *     The primaryArtist
     */
    public PrimaryArtist getPrimaryArtist() {
        return primaryArtist;
    }

    /**
     *
     * @param primaryArtist
     *     The primary_artist
     */
    public void setPrimaryArtist(PrimaryArtist primaryArtist) {
        this.primaryArtist = primaryArtist;
    }

    /**
     *
     * @return
     *     The updatedByHumanAt
     */
    public Integer getUpdatedByHumanAt() {
        return updatedByHumanAt;
    }

    /**
     *
     * @param updatedByHumanAt
     *     The updated_by_human_at
     */
    public void setUpdatedByHumanAt(Integer updatedByHumanAt) {
        this.updatedByHumanAt = updatedByHumanAt;
    }

    /**
     *
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The annotationCount
     */
    public Integer getAnnotationCount() {
        return annotationCount;
    }

    /**
     *
     * @param annotationCount
     *     The annotation_count
     */
    public void setAnnotationCount(Integer annotationCount) {
        this.annotationCount = annotationCount;
    }

}
