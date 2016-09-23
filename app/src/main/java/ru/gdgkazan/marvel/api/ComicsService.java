package ru.gdgkazan.marvel.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.gdgkazan.marvel.content.character.CharactersResponse;
import ru.gdgkazan.marvel.content.comics.ComicsResponse;
import ru.gdgkazan.marvel.content.event.EventsResponse;
import rx.Observable;

/**
 * @author Aydar Farrakhov
 */
public interface ComicsService {

    @GET("comics")
    Observable<ComicsResponse> comics(@Query("offset") Long offset, @Query("limit") Long limit,
                                      @Query("orderBy") String orderBy);

    @GET("comics/{comicsId}")
    Observable<ComicsResponse> comics(@Path("comicsId") Long id);

    @GET("comics/{comicsId}/characters")
    Observable<CharactersResponse> characters(@Path("comicsId") Long id);

    @GET("comics/{comicsId}/events")
    Observable<EventsResponse> events(@Path("comicsId") Long id);

}
