package ru.gdgkazan.marvel.screen.events;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.repository.RepositoryProvider;
import ru.gdgkazan.marvel.screen.comics.ComicsView;
import ru.gdgkazan.marvel.screen.eventslist.EventsView;

/**
 * @author Olga Telezhnikova
 */
public class EventPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final EventsView mView;

    public EventPresenter(@NonNull LifecycleHandler lifecycleHandler,
                           @NonNull EventsView view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init(long id) {
        RepositoryProvider.provideEventsRepository()
                .eventById(id)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.comics_request))
                .subscribe(mView::showEvents, throwable -> mView.showError());
    }

}
