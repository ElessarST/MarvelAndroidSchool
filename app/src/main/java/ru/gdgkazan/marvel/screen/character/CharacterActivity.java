package ru.gdgkazan.marvel.screen.character;


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
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.Image;
import ru.gdgkazan.marvel.content.character.Character;
import ru.gdgkazan.marvel.general.LoadingDialog;
import ru.gdgkazan.marvel.general.LoadingView;
import ru.gdgkazan.marvel.util.Constants;
import ru.gdgkazan.marvel.util.Images;

public class CharacterActivity extends AppCompatActivity implements CharacterView{

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.image)
    ImageView mImage;

    @BindView(R.id.description)
    TextView mDescription;

    private CharacterPresenter mPresenter;

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

        mPresenter = new CharacterPresenter(this);
        mPresenter.init(getIntent().getSerializableExtra(Constants.CHARACTER_KEY));
    }

    public static void start(@NonNull Activity activity, @NonNull Character character) {
        Intent intent = new Intent(activity, CharacterActivity.class);
        intent.putExtra(Constants.CHARACTER_KEY, character);
        intent.putExtra(Constants.IMAGE_KEY, character.getImage());
        intent.putExtra(Constants.ID_KEY, character.getId());
        activity.startActivity(intent);
    }

    @Override
    public void show(Character character) {
        mCollapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));

        Image image = character.getImage();
        if (image != null) {
            Images.loadPicture(mImage,
                    String.format("%s.%s", character.getImage().getPath(), character.getImage().getExtension()));
        }

        mDescription.setText(character.getDescription());
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.some_error, Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
