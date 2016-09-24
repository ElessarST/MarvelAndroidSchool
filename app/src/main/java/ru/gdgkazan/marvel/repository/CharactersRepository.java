package ru.gdgkazan.marvel.repository;


import android.support.annotation.NonNull;

import java.util.List;

import ru.gdgkazan.marvel.content.character.Character;
import rx.Observable;

public interface CharactersRepository {

    @NonNull
    Observable<List<Character>> characters(Long offset, Long limit);

    Observable<Character> characterById(long id);
}
