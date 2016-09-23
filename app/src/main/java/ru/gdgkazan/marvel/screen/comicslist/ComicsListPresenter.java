package ru.gdgkazan.marvel.screen.comicslist;

import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.repository.RepositoryProvider;
import ru.gdgkazan.marvel.screen.common.CommonListView;
import ru.gdgkazan.marvel.util.Constants;
import rx.Observable;

/**
 * @author Aydar Farrakhov
 */
public class ComicsListPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final CommonListView<Comics> mView;

    public ComicsListPresenter(@NonNull LifecycleHandler lifecycleHandler,
                               @NonNull CommonListView<Comics> view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideComicsRepository()
                .comics(Constants.ZERO_OFFSET, Constants.PAGE_SIZE)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.comics_request))
                .subscribe(mView::showItems, throwable -> mView.showError(throwable));
    }

    public Observable<List<Comics>> loadMoreItems(int page) {
        return RepositoryProvider.provideComicsRepository()
                .comics(page * Constants.PAGE_SIZE, Constants.PAGE_SIZE)
                .compose(mLifecycleHandler.load(R.id.comics_request_more + page));
    }

    public void onItemClick(Comics comics) {
        mView.showDetails(comics);
    }
}
