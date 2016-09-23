package ru.gdgkazan.marvel.repository;

import android.support.annotation.NonNull;

import java.util.List;

import ru.gdgkazan.marvel.content.comics.Comics;
import rx.Observable;

/**
 * @author Aydar Farrakhov
 */
public interface ComicsRepository {

    @NonNull
    Observable<List<Comics>> comics(Long offset, Long limit);

    Observable<Comics> comics(Long id);

}
