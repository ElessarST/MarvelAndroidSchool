package ru.gdgkazan.marvel.screen.comics;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.Image;
import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.content.comics.ComicsTextObject;
import ru.gdgkazan.marvel.general.LoadingDialog;
import ru.gdgkazan.marvel.general.LoadingView;
import ru.gdgkazan.marvel.util.Images;

public class ComicsActivity extends AppCompatActivity implements ComicsView{

    private static final String COMICS_NAME_KEY = "name";
    private static final String COMICS_ID_KEY = "id";
    public static final String IMAGE = "image";


    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.image)
    ImageView mImage;

    @BindView(R.id.description)
    TextView mDescription;

    private ComicsPresenter mPresenter;

    private LoadingView mLoadingView;

    public static void start(@NonNull Activity activity, @NonNull Comics comics) {
        Intent intent = new Intent(activity, ComicsActivity.class);
        intent.putExtra(COMICS_NAME_KEY, comics.getName());
        intent.putExtra(COMICS_ID_KEY, comics.getId());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareWindowForAnimation();
        setContentView(R.layout.activity_comics);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar), IMAGE);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        mCollapsingToolbar.setTitle(getIntent().getStringExtra(COMICS_NAME_KEY));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mPresenter = new ComicsPresenter(lifecycleHandler, this);
        mPresenter.init(getIntent().getLongExtra(COMICS_ID_KEY, 0));
    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showComics(Comics comics) {
        mCollapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));

        Image image = comics.getImage();
        if (image != null){
            Images.loadPicture(mImage,
                    String.format("%s.%s", comics.getImage().getPath(), comics.getImage().getExtension()));
        }

        if (comics.getTextObjects() != null){
            StringBuilder description = new StringBuilder();
            for (ComicsTextObject comicsTextObject : comics.getTextObjects()) {
                description.append(comicsTextObject.getText()).append("\n\n");
            }
            mDescription.setText(description.toString());
        }
    }

    private void prepareWindowForAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }
}
