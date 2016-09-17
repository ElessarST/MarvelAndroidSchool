package ru.gdgkazan.marvel.screen.comicslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.comics.Comics;

/**
 * @author Aydar Farrakhov
 */
public class ComicsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.comics_name)
    TextView mName;

    @BindView(R.id.comics_description)
    TextView mDescription;

    public ComicsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Comics comics) {
        mName.setText(comics.getTitle());
        if (comics.getTextObjects() != null && !comics.getTextObjects().isEmpty()){
            mDescription.setText(comics.getTextObjects().get(0).getText());
        }

    }
}
