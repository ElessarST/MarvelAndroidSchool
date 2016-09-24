package ru.gdgkazan.marvel.repository;

import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.rxloader.RxUtils;
import ru.gdgkazan.marvel.api.ApiFactory;
import ru.gdgkazan.marvel.content.character.Character;
import ru.gdgkazan.marvel.content.character.CharactersResponse;
import ru.gdgkazan.marvel.content.character.CharactersResponseData;
import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.content.comics.ComicsResponse;
import ru.gdgkazan.marvel.content.comics.ComicsResponseData;
import ru.gdgkazan.marvel.content.event.Event;
import ru.gdgkazan.marvel.content.event.EventsResponse;
import ru.gdgkazan.marvel.content.event.EventsResponseData;
import ru.gdgkazan.marvel.repository.cache.RealmCacheErrorHandler;
import ru.gdgkazan.marvel.repository.cache.RealmRewriteCache;
import rx.Observable;

/**
 * @author Aydar Farrakhov
 */
public class DefaultComicsRepository implements ComicsRepository {

    private static final String DEFAULT_COMICS_SORT = "-onsaleDate";

    @NonNull
    @Override
    public Observable<List<Comics>> comics(Long offset, Long limit) {
        return ApiFactory.getComicsService()
                .comics(offset, limit, DEFAULT_COMICS_SORT)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .flatMap(new RealmRewriteCache<>(Comics.class))
                .onErrorResumeNext(new RealmCacheErrorHandler<>(Comics.class))
                .compose(RxUtils.async());
    }

    @Override
    public Observable<Comics> comics(Long id) {
        return ApiFactory.getComicsService()
                .comics(id)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .map(c -> c.get(0))
                .compose(RxUtils.async());
    }

    @Override
    public Observable<List<Character>> characters(Long id) {
        return ApiFactory.getComicsService()
                .characters(id)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .compose(RxUtils.async());
    }

    @Override
    public Observable<List<Event>> events(Long id) {
        return ApiFactory.getComicsService()
                .events(id)
                .map(EventsResponse::getData)
                .map(EventsResponseData::getResults)
                .compose(RxUtils.async());
    }
}
