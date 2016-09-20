package ru.gdgkazan.marvel.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.gdgkazan.marvel.content.comics.ComicsResponse;
import rx.Observable;

/**
 * Created by aydar on 17.09.16.
 */
public interface CharactersService {

    @GET("characters/{characterId}")
    Observable<ComicsResponse> comicsByCharacter(@Path("characterId") Long id);

}
