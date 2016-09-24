package ru.gdgkazan.marvel.repository;

import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.rxloader.RxUtils;
import ru.gdgkazan.marvel.api.ApiFactory;
import ru.gdgkazan.marvel.content.character.Character;
import ru.gdgkazan.marvel.content.character.CharactersResponse;
import ru.gdgkazan.marvel.content.character.CharactersResponseData;
import ru.gdgkazan.marvel.repository.cache.RealmCacheErrorHandler;
import ru.gdgkazan.marvel.repository.cache.RealmRewriteCache;
import rx.Observable;

/**
 * @author Artur Badretdinov
 */
public class DefaultCharactersRepository implements CharactersRepository {

    @NonNull
    @Override
    public Observable<List<Character>> characters(Long offset, Long limit) {
        return ApiFactory.getCharactersService()
                .characters(offset, limit)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .flatMap(new RealmRewriteCache<>(Character.class))
                .onErrorResumeNext(new RealmCacheErrorHandler<>(Character.class))
                .compose(RxUtils.async());

    }

    @Override
    public Observable<Character> characterById(long id) {
        return ApiFactory.getCharactersService()
                .characterById(id)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .map(characters -> characters.get(0))
                .compose(RxUtils.async());
    }

}
