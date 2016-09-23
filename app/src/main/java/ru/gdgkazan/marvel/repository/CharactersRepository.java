package ru.gdgkazan.marvel.repository;


import android.support.annotation.NonNull;

import ru.gdgkazan.marvel.content.character.CharactersResponse;
import rx.Observable;

public interface CharactersRepository {

    @NonNull
    Observable<CharactersResponse> characters();
}
