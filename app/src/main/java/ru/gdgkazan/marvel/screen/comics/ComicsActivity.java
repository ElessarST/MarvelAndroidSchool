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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.Image;
import ru.gdgkazan.marvel.content.character.Character;
import ru.gdgkazan.marvel.content.comics.CharactersAndEvents;
import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.content.comics.ComicsTextObject;
import ru.gdgkazan.marvel.content.event.Event;
import ru.gdgkazan.marvel.general.LoadingDialog;
import ru.gdgkazan.marvel.general.LoadingView;
import ru.gdgkazan.marvel.screen.common.CommonAdapter;
import ru.gdgkazan.marvel.screen.common.ListItem;
import ru.gdgkazan.marvel.util.Images;
import ru.gdgkazan.marvel.widget.BaseAdapter;
import ru.gdgkazan.marvel.widget.DividerItemDecoration;
import ru.gdgkazan.marvel.widget.EmptyRecyclerView;

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

    @BindView(R.id.price)
    TextView mPrice;

    @BindView(R.id.pages)
    TextView mPages;

    @BindView(R.id.progress_loader)
    ProgressBar mProgressBar;

    @BindView(R.id.events_layout)
    LinearLayout mEventsLayout;

    @BindView(R.id.characters_layout)
    LinearLayout mCharactersLayout;

    @BindView(R.id.events_view)
    EmptyRecyclerView mEventsRecycler;

    @BindView(R.id.characters_view)
    EmptyRecyclerView mCharactersRecycler;

    @BindView(R.id.events_empty)
    TextView mEventsEmpty;

    @BindView(R.id.characters_empty)
    TextView mCharactersEmpty;

    private CommonAdapter<Event> mEventsAdapter;

    private CommonAdapter<Character> mCharactersAdapter;

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
        long id = getIntent().getLongExtra(COMICS_ID_KEY, 0);
        mPresenter.init(id);
        initRecyclers();
    }

    private void initRecyclers() {
        mEventsAdapter = new CommonAdapter<>(new ArrayList<>());
        mCharactersAdapter = new CommonAdapter<>(new ArrayList<>());
        initRecycler(mEventsRecycler, mEventsEmpty, mEventsAdapter, item -> {
            mPresenter.onEventClick(item);
        });
        initRecycler(mCharactersRecycler, mCharactersEmpty, mCharactersAdapter, item -> {
            mPresenter.onCharacterClick(item);
        });
    }

    private <T extends ListItem> void initRecycler(EmptyRecyclerView recyclerView,
                                                   TextView emptyView, CommonAdapter<T> adapter,
                                                   BaseAdapter.OnItemClickListener<T> onClickListener) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setEmptyView(emptyView);

        adapter.attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(onClickListener);

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
        Toast.makeText(this, R.string.some_error, Toast.LENGTH_LONG).show();
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
                description.append(comicsTextObject.getText()).append("\n");
            }
            mDescription.setText(description.toString());
        }

        if (comics.getPrices() != null && !comics.getPrices().isEmpty()){
            mPrice.setText(getString(R.string.price_format, String.valueOf(comics.getPrices().get(0).getPrice())));
        }

        mPages.setText(String.valueOf(comics.getPageCount()));

        mPresenter.loadCharactersAndEvents(comics.getId());

    }

    @Override
    public void showAdditionalLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mEventsLayout.setVisibility(View.GONE);
        mCharactersEmpty.setVisibility(View.GONE);
    }

    @Override
    public void hideAdditionalLoading() {
        mProgressBar.setVisibility(View.GONE);
        mEventsLayout.setVisibility(View.VISIBLE);
        mCharactersLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEventsAndCharacters(CharactersAndEvents data) {
        mCharactersAdapter.changeDataSet(data.getCharacters());
        mEventsAdapter.changeDataSet(data.getEvents());
    }

    @Override
    public void showEvent(Event item) {

    }

    @Override
    public void showCharacter(Character item) {

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
