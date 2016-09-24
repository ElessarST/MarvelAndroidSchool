package ru.gdgkazan.marvel.screen.character;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.repository.RepositoryProvider;

/**
 * @author Olga Telezhnikova
 */
public class CharacterPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final CharacterView mView;

    public CharacterPresenter(@NonNull LifecycleHandler lifecycleHandler,
                              @NonNull CharacterView view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init(long id) {
        RepositoryProvider.provideCharactersRepository()
                .characterById(id)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.single_character_request))
                .subscribe(mView::show, throwable -> mView.showError());
    }

}
