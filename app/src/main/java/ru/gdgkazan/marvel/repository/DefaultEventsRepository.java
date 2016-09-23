package ru.gdgkazan.marvel.repository;

import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.rxloader.RxUtils;
import ru.gdgkazan.marvel.api.ApiFactory;
import ru.gdgkazan.marvel.content.event.Event;
import ru.gdgkazan.marvel.content.event.EventsResponse;
import ru.gdgkazan.marvel.content.event.EventsResponseData;
import ru.gdgkazan.marvel.repository.cache.RealmCacheErrorHandler;
import ru.gdgkazan.marvel.repository.cache.RealmRewriteCache;
import rx.Observable;

/**
 * @author Olga Telezhnikova
 */
public class DefaultEventsRepository implements EventsRepository {

    @NonNull
    @Override
    public Observable<List<Event>> events(Long offset, Long limit) {
        return ApiFactory.getEventsService()
                .events(offset, limit)
                .map(EventsResponse::getData)
                .map(EventsResponseData::getResults)
                .flatMap(new RealmRewriteCache<>(Event.class))
                .onErrorResumeNext(new RealmCacheErrorHandler<>(Event.class))
                .compose(RxUtils.async());
    }
}
