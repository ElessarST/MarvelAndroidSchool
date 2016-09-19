package ru.gdgkazan.marvel.repository;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.RxUtils;
import ru.gdgkazan.marvel.api.ApiFactory;
import ru.gdgkazan.marvel.content.comics.ComicsResponse;
import ru.gdgkazan.marvel.content.comics.ComicsResponseData;
import ru.gdgkazan.marvel.repository.cache.RealmSingleCacheErrorHandler;
import ru.gdgkazan.marvel.repository.cache.RealmSingleRewriteCache;
import rx.Observable;

/**
 * @author Aydar Farrakhov
 */
public class DefaultComicsRepository implements ComicsRepository {

    @NonNull
    @Override
    public Observable<ComicsResponseData> comics() {
        return ApiFactory.getComicsService()
                .comics()
                .flatMap(new RealmSingleRewriteCache<>(ComicsResponse.class))
                .onErrorResumeNext(new RealmSingleCacheErrorHandler<>(ComicsResponse.class))
                .compose(RxUtils.async())
                .map(ComicsResponse::getData);
    }
}
