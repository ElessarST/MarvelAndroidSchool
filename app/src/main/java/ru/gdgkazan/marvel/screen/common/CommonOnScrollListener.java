package ru.gdgkazan.marvel.screen.common;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;

/**
 * Created by aydar on 23.09.16.
 */

public abstract class CommonOnScrollListener<T extends ListItem> extends RecyclerView.OnScrollListener {

    private int mCurrentPage = 0;
    private boolean mLoading = false;
    private boolean mIsLastPage = false;
    private LinearLayoutManager mLayoutManager;
    private CommonListView<T> mView;

    public CommonOnScrollListener(LinearLayoutManager layoutManager, CommonListView<T> view) {
        this.mLayoutManager = layoutManager;
        this.mView = view;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView,
                                     int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = mLayoutManager.getChildCount();
        int totalItemCount = mLayoutManager.getItemCount();
        int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

        if (!mLoading && !mIsLastPage) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= 10) {
                loadMore(++mCurrentPage);
            }
        }
    }

    private void loadMore(int page) {
        mLoading = true;
        Observable.just(page)
                .compose(RxUtils.async())
                .subscribe(p ->  loadMoreItems(p)
                        .doOnTerminate(() -> mLoading = false)
                        .subscribe(mView::addMoreItems, throwable -> mView.showError(throwable)));

    }

    public abstract Observable<List<T>> loadMoreItems(int page);

}
