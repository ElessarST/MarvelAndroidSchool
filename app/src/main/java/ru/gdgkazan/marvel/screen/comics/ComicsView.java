package ru.gdgkazan.marvel.screen.comics;

import ru.gdgkazan.marvel.content.comics.CharactersAndEvents;
import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.general.LoadingView;

/**
 * Created by aydar on 23.09.16.
 */

public interface ComicsView extends LoadingView {

    void showError();

    void showComics(Comics comics);

    void showAdditionalLoading();

    void hideAdditionalLoading();

    void showEventsAndCharacters(CharactersAndEvents data);
}
