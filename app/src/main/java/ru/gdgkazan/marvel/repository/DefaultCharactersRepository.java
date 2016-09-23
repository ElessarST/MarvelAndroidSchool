package ru.gdgkazan.marvel.repository;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.RxUtils;
import ru.gdgkazan.marvel.api.ApiFactory;
import ru.gdgkazan.marvel.content.character.CharactersResponse;
import ru.gdgkazan.marvel.content.character.CharactersResponseData;
import ru.gdgkazan.marvel.repository.cache.RealmSingleCacheErrorHandler;
import ru.gdgkazan.marvel.repository.cache.RealmSingleRewriteCache;
import rx.Observable;

/**
 * @author Artur Badretdinov
 */
public class DefaultCharactersRepository implements CharactersRepository {

    @NonNull
    @Override
    public Observable<CharactersResponseData> characters() {
        return ApiFactory.getCharactersService()
                .characters()
                .flatMap(new RealmSingleRewriteCache<>(CharactersResponse.class))
                .onErrorResumeNext(new RealmSingleCacheErrorHandler<>(CharactersResponse.class))
                .compose(RxUtils.async())
                .map(CharactersResponse::getData);
    }
}
