package ru.gdgkazan.marvel.screen.events;

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
import ru.gdgkazan.marvel.content.event.Event;
import ru.gdgkazan.marvel.general.LoadingDialog;
import ru.gdgkazan.marvel.general.LoadingView;
import ru.gdgkazan.marvel.screen.eventslist.EventsView;
import ru.gdgkazan.marvel.util.Constants;
import ru.gdgkazan.marvel.util.Images;


/**
 * @author Olga Telezhnikova
 */
public class EventActivity extends AppCompatActivity implements EventsView {

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.image)
    ImageView mImage;

    @BindView(R.id.description)
    TextView mDescription;

    private EventPresenter mPresenter;

    private LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareWindowForAnimation();
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar), Constants.IMAGE_KEY);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        mCollapsingToolbar.setTitle(getIntent().getStringExtra(Constants.NAME_KEY));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mPresenter = new EventPresenter(lifecycleHandler, this);
        mPresenter.init(getIntent().getLongExtra(Constants.ID_KEY, 0));
    }

    public static void start(@NonNull Activity activity, @NonNull Event event) {
        Intent intent = new Intent(activity, EventActivity.class);
        intent.putExtra(Constants.NAME_KEY, event.getName());
        intent.putExtra(Constants.ID_KEY, event.getId());
        activity.startActivity(intent);
    }

    @Override
    public void showEvents(@NonNull Event event) {
        mCollapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));

        Image image = event.getImage();
        if (image != null) {
            Images.loadPicture(mImage,
                    String.format("%s.%s", event.getImage().getPath(), event.getImage().getExtension()));
        }

        mDescription.setText(event.getDescription());

    }

    @Override
    public void showError() {
    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();

    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
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
