package ru.gdgkazan.marvel.screen.comicslist;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.comics.ComicsResponseData;
import ru.gdgkazan.marvel.repository.RepositoryProvider;
import ru.gdgkazan.marvel.util.Constants;

/**
 * @author Aydar Farrakhov
 */
public class ComicsListPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final ComicsView mView;

    public ComicsListPresenter(@NonNull LifecycleHandler lifecycleHandler,
                               @NonNull ComicsView view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideComicsRepository()
                .comics(Constants.ZERO_OFFSET, Constants.DEFAULT_LIMIT)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.comics_request))
                .map(ComicsResponseData::getResults)
                .subscribe(mView::showComics, throwable -> mView.showError());
    }

}
