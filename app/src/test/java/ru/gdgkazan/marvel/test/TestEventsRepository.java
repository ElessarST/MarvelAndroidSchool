package ru.gdgkazan.marvel.test;

import android.support.annotation.NonNull;

import java.util.List;

import ru.gdgkazan.marvel.content.event.Event;
import ru.gdgkazan.marvel.repository.EventsRepository;
import rx.Observable;

/**
 * @author Olga Telezhnikova
 */
public class TestEventsRepository implements EventsRepository {
    @NonNull
    @Override
    public Observable<List<Event>> events(Long offset, Long limit) {
        return Observable.empty();
    }

    @NonNull
    @Override
    public Observable<Event> eventById(long id) {
        return Observable.empty();
    }
}
