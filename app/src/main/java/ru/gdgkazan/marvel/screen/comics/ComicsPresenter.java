package ru.gdgkazan.marvel.screen.comics;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.character.Character;
import ru.gdgkazan.marvel.content.comics.CharactersAndEvents;
import ru.gdgkazan.marvel.content.event.Event;
import ru.gdgkazan.marvel.repository.RepositoryProvider;
import rx.Observable;

/**
 * Created by aydar on 23.09.16.
 */

public class ComicsPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final ComicsView mView;

    public ComicsPresenter(@NonNull LifecycleHandler lifecycleHandler,
                               @NonNull ComicsView view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init(long id) {
        RepositoryProvider.provideComicsRepository()
                .comics(id)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.comics_request))
                .subscribe(mView::showComics, throwable -> mView.showError());
    }

    public void loadCharactersAndEvents(long id) {
        Observable.zip(
                RepositoryProvider.provideComicsRepository().characters(id),
                RepositoryProvider.provideComicsRepository().events(id),
                CharactersAndEvents::new)
                .doOnSubscribe(mView::showAdditionalLoading)
                .doOnTerminate(mView::hideAdditionalLoading)
                .subscribe(mView::showEventsAndCharacters, throwable -> mView.showError());

    }

    public void onEventClick(Event item) {
        mView.showEvent(item);
    }

    public void onCharacterClick(Character item) {
        mView.showCharacter(item);
    }
}
