package ru.gdgkazan.marvel.test;

import android.support.annotation.NonNull;

import ru.gdgkazan.marvel.content.comics.ComicsResponseData;
import ru.gdgkazan.marvel.repository.ComicsRepository;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class TestComicsRepository implements ComicsRepository {

    @NonNull
    @Override
    public Observable<ComicsResponseData> comics(Long offset, Long limit) {
        return Observable.empty();
    }
}

