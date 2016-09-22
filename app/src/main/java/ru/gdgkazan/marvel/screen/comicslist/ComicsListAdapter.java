package ru.gdgkazan.marvel.screen.comicslist;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.widget.BaseAdapter;

/**
 * @author Aydar Farrakhov
 */
public class ComicsListAdapter extends BaseAdapter<ComicsHolder, Comics> {

    private final int mImageHeight;
    private final int mImageWidth;

    public ComicsListAdapter(@NonNull List<Comics> items, int imageWidth, int imageHeight) {
        super(items);
        mImageHeight = imageHeight;
        mImageWidth = imageWidth;
    }

    @Override
    public ComicsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ComicsHolder.create(parent.getContext(), mImageWidth, mImageHeight);
    }

    @Override
    public void onBindViewHolder(ComicsHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Comics comics = getItem(position);
        holder.bind(comics);
    }

}
