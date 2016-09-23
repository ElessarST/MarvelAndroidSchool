package ru.gdgkazan.marvel.repository;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.RxUtils;
import ru.gdgkazan.marvel.api.ApiFactory;
import ru.gdgkazan.marvel.content.event.EventsResponse;
import ru.gdgkazan.marvel.content.event.EventsResponseData;
import ru.gdgkazan.marvel.repository.cache.RealmSingleCacheErrorHandler;
import ru.gdgkazan.marvel.repository.cache.RealmSingleRewriteCache;
import rx.Observable;

/**
 * @author Olga Telezhnikova
 */
public class DefaultEventsRepository implements EventsRepository {

    @NonNull
    @Override
    public Observable<EventsResponseData> events(Long offset, Long limit) {
        return ApiFactory.getEventsService()
                .events(offset, limit)
                .flatMap(new RealmSingleRewriteCache<>(EventsResponse.class))
                .onErrorResumeNext(new RealmSingleCacheErrorHandler<>(EventsResponse.class))
                .compose(RxUtils.async())
                .map(EventsResponse::getData);
    }
}
