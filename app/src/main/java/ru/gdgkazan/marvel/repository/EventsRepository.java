package ru.gdgkazan.marvel.repository;

import android.support.annotation.NonNull;

import ru.gdgkazan.marvel.content.event.EventsResponseData;
import rx.Observable;

/**
 * @author Olga Telezhnikova
 */
public interface EventsRepository {

    @NonNull
    Observable<EventsResponseData> events(Long offset, Long limit);
}
