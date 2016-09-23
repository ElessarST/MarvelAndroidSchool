package ru.gdgkazan.marvel.test;

import android.support.annotation.NonNull;

import java.util.List;

import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.repository.ComicsRepository;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class TestComicsRepository implements ComicsRepository {

    @NonNull
    @Override
    public Observable<List<Comics>> comics(Long offset, Long limit) {
        return Observable.empty();
    }

    @Override
    public Observable<Comics> comics(Long id) {
        return null;
    }
}

