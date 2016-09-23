package ru.gdgkazan.marvel.screen.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.util.Images;

/**
 * @author Aydar Farrakhov
 */
public class ItemHolder extends RecyclerView.ViewHolder {

    private static final int MAX_LENGTH = 80;
    private static final String MORE_TEXT = "...";

    @BindView(R.id.name)
    TextView mName;

    @BindView(R.id.description)
    TextView mDescription;

    @BindView(R.id.image)
    ImageView mImageView;

    @NonNull
    public static ItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.universal_item, null);
        ItemHolder holder = new ItemHolder(view);
        ButterKnife.bind(holder, view);
        return holder;
    }

    public ItemHolder(View itemView) {
        super(itemView);
    }

    public void bind(@NonNull ListItem item) {
        mName.setText(item.getName());
        if (item.getDescription() != null){
            mDescription.setText(cutLongDescription(item.getDescription()));
        }
        if (item.getImage() != null){
            Images.loadPicture(mImageView, String.format("%s.%s", item.getImage().getPath(), item.getImage().getExtension()));
        }
    }

    private String cutLongDescription(String description) {
        if (description.length() < MAX_LENGTH){
            return description;
        } else {
            return description.substring(0, MAX_LENGTH - MORE_TEXT.length()) + MORE_TEXT;
        }
    }
}
