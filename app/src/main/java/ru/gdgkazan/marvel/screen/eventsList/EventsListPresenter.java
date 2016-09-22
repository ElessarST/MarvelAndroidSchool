package ru.gdgkazan.marvel.screen.eventslist;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.event.EventsResponseData;
import ru.gdgkazan.marvel.repository.RepositoryProvider;
import ru.gdgkazan.marvel.util.Constants;

/**
 * @author Olga Telezhnikova
 */
public class EventsListPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final EventsView mView;

    public EventsListPresenter(@NonNull LifecycleHandler lifecycleHandler,
                               @NonNull EventsView view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideEventsRepository()
                .events(Constants.ZERO_OFFSET, Constants.DEFAULT_LIMIT)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.comics_request))
                .map(EventsResponseData::getResults)
                .subscribe(mView::showEvents, throwable -> mView.showError());
    }
}
