package ru.gdgkazan.marvel.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.gdgkazan.marvel.content.character.CharactersResponse;
import ru.gdgkazan.marvel.content.comics.ComicsResponse;
import rx.Observable;

/**
 * Created by aydar on 17.09.16.
 */
public interface CharactersService {

    @GET("characters")
    Observable<CharactersResponse> characters(@Query("offset") Long offset, @Query("limit") Long limit);


    @GET("characters/{characterId/comics}")
    Observable<ComicsResponse> comicsByCharacter(@Path("characterId") Long id);

    @GET("characters/{id}")
    Observable<CharactersResponse> characterById(@Path("id") Long id);
}
