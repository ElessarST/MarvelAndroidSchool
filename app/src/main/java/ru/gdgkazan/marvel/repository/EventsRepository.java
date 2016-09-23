package ru.gdgkazan.marvel.repository;

import android.support.annotation.NonNull;

import java.util.List;

import ru.gdgkazan.marvel.content.event.Event;
import ru.gdgkazan.marvel.content.event.EventsResponseData;
import rx.Observable;

/**
 * @author Olga Telezhnikova
 */
public interface EventsRepository {

    @NonNull
    Observable<List<Event>> events(Long offset, Long limit);

    @NonNull
    Observable<Event> eventById(long id);
}
