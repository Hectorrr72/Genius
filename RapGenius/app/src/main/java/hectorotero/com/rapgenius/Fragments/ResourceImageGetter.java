package hectorotero.com.rapgenius.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;

import hectorotero.com.rapgenius.BuildConfig;

/**
 * Created by hectoroteromediero on 19/12/14.
 */
class ResourceImageGetter
        implements Html.ImageGetter {
    private final Context context;

    public ResourceImageGetter(Context context) {
        this.context = context;
    }

    public Drawable getDrawable(String source) {
        int path = context.getResources().getIdentifier(
                source, "drawable", BuildConfig.APPLICATION_ID);
        Drawable drawable
                = context.getResources().getDrawable(path);
        drawable.setBounds(0, 0,
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        return drawable;
    }
}
