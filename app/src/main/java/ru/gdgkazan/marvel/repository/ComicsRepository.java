package ru.gdgkazan.marvel.repository;

import android.support.annotation.NonNull;

import ru.gdgkazan.marvel.content.comics.ComicsResponseData;
import rx.Observable;

/**
 * @author Aydar Farrakhov
 */
public interface ComicsRepository {

    @NonNull
    Observable<ComicsResponseData> comics();

}
