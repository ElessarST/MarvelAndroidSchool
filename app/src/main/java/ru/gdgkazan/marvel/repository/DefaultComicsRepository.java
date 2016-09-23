package ru.gdgkazan.marvel.repository;

import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.rxloader.RxUtils;
import ru.gdgkazan.marvel.api.ApiFactory;
import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.content.comics.ComicsResponse;
import ru.gdgkazan.marvel.content.comics.ComicsResponseData;
import ru.gdgkazan.marvel.repository.cache.RealmCacheErrorHandler;
import ru.gdgkazan.marvel.repository.cache.RealmRewriteCache;
import rx.Observable;

/**
 * @author Aydar Farrakhov
 */
public class DefaultComicsRepository implements ComicsRepository {

    @NonNull
    @Override
    public Observable<List<Comics>> comics(Long offset, Long limit) {
        return ApiFactory.getComicsService()
                .comics(offset, limit)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .flatMap(new RealmRewriteCache<>(Comics.class))
                .onErrorResumeNext(new RealmCacheErrorHandler<>(Comics.class))
                .compose(RxUtils.async());
    }
}
