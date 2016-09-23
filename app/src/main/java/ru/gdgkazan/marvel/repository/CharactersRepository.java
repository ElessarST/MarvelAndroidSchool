package ru.gdgkazan.marvel.repository;


import android.support.annotation.NonNull;

import ru.gdgkazan.marvel.content.character.CharactersResponse;
import ru.gdgkazan.marvel.content.character.CharactersResponseData;
import rx.Observable;

public interface CharactersRepository {

    @NonNull
    Observable<CharactersResponseData> characters();
}
