package ru.gdgkazan.marvel.util;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ru.gdgkazan.marvel.AppDelegate;

/**
 * @author Aydar Farrakhov
 */
public final class Images {

    private Images() {
    }

    public static void loadPicture(@NonNull ImageView imageView, @NonNull String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .noFade()
                .into(imageView);
    }

    public static void fetch(@NonNull String url) {
        Picasso.with(AppDelegate.getContext())
                .load(url)
                .fetch();
    }
}
