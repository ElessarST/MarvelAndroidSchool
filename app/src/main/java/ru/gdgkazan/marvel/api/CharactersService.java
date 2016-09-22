package ru.gdgkazan.marvel.api;

import retrofit2.http.GET;
import ru.gdgkazan.marvel.content.character.CharactersResponse;
import rx.Observable;

/**
 * Created by aydar on 17.09.16.
 */
public interface CharactersService {

    @GET("characters")
    Observable<CharactersResponse> characters();

}
