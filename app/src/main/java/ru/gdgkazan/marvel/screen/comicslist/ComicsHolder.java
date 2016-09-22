package ru.gdgkazan.marvel.screen.comicslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.Image;
import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.util.Images;

/**
 * @author Aydar Farrakhov
 */
public class ComicsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.comics_name)
    TextView mName;

    @BindView(R.id.image)
    ImageView mImageView;

    @NonNull
    public static ComicsHolder create(@NonNull Context context, int imageWidth, int imageHeight) {
        View view = View.inflate(context, R.layout.item_comics, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = imageHeight;
        layoutParams.width = imageWidth;
        imageView.requestLayout();
        return new ComicsHolder(view);
    }

    public ComicsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Comics comics) {
        mName.setText(comics.getTitle());

        if (comics.getImages() != null && !comics.getImages().isEmpty()){
            Image image = comics.getImages().get(0);
            Images.loadPicture(mImageView, String.format("%s.%s", image.getPath(), image.getExtension()));
        }

    }
}
