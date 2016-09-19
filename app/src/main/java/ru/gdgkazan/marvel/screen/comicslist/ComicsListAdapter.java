package ru.gdgkazan.marvel.screen.comicslist;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.widget.BaseAdapter;

/**
 * @author Aydar Farrakhov
 */
public class ComicsListAdapter extends BaseAdapter<ComicsHolder, Comics> {

    public ComicsListAdapter(@NonNull List<Comics> items) {
        super(items);
    }

    @Override
    public ComicsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ComicsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comics, parent, false));
    }

    @Override
    public void onBindViewHolder(ComicsHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Comics comics = getItem(position);
        holder.bind(comics);
    }

}
