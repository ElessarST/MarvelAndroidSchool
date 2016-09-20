package ru.gdgkazan.marvel.repository;

import android.support.annotation.NonNull;

import ru.gdgkazan.marvel.content.character.CharactersResponseData;
import rx.Observable;

/**
 * @author Artur Badretdinov
 */
public interface CharactersRepository {

    @NonNull
    Observable<CharactersResponseData> characters();

}
