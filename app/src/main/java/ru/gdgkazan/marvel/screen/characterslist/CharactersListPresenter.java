package ru.gdgkazan.marvel.screen.characterslist;

import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.character.Character;
import ru.gdgkazan.marvel.repository.RepositoryProvider;
import ru.gdgkazan.marvel.screen.common.CommonListView;
import ru.gdgkazan.marvel.util.Constants;
import rx.Observable;

public class CharactersListPresenter {

    private final String[] descriptions = {
            "Один из выдающихся героев Вселенной Marvel, с нелегкой судьбой",
            "Просто мимо крокодил",
            "Очень важный тип"
    };

    private final LifecycleHandler mLifecycleHandler;
    private final CommonListView<Character> mView;

    public CharactersListPresenter(@NonNull LifecycleHandler lifecycleHandler,
                               @NonNull CommonListView<Character> view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideCharactersRepository()
                .characters(Constants.ZERO_OFFSET, Constants.PAGE_SIZE)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.characters_request))
                .subscribe((List<Character> characters) -> {
                    for (Character character : characters) {
                        if (character.getDescription().isEmpty()) {
                            character.setDescription(descriptions[(int)(character.getId() % descriptions.length)]);
                        }
                    }
                    mView.showItems(characters);
                }, throwable -> mView.showError());
    }

    public Observable<List<Character>> loadMoreItems(int page) {
        return Observable.empty();
////                RepositoryProvider.provideCharactersRepository()
////                .characters(page * Constants.PAGE_SIZE, Constants.PAGE_SIZE)
////                .compose(mLifecycleHandler.load(R.id.comics_request_more + page));
    }

    public void onItemClick(Character character) {
        mView.showDetails(character);
    }
}
