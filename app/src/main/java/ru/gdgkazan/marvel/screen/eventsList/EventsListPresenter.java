package ru.gdgkazan.marvel.screen.eventslist;

import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.event.Event;
import ru.gdgkazan.marvel.repository.RepositoryProvider;
import ru.gdgkazan.marvel.screen.common.CommonListView;
import ru.gdgkazan.marvel.util.Constants;
import rx.Observable;

/**
 * @author Olga Telezhnikova
 */
public class EventsListPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final CommonListView<Event> mView;

    public EventsListPresenter(@NonNull LifecycleHandler lifecycleHandler,
                               @NonNull CommonListView<Event> view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideEventsRepository()
                .events(Constants.ZERO_OFFSET, Constants.PAGE_SIZE)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.events_request))
                .subscribe(mView::showItems, throwable -> mView.showError());
    }

    public Observable<List<Event>> loadMoreItems(int page) {
        return RepositoryProvider.provideEventsRepository()
                .events(page * Constants.PAGE_SIZE, Constants.PAGE_SIZE)
                .compose(mLifecycleHandler.load(R.id.events_request_more + page));
    }

    public void onItemClick(Event event) {
        mView.showDetails(event);
    }
}
