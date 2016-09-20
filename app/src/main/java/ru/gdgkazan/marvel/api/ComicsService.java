package ru.gdgkazan.marvel.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.gdgkazan.marvel.content.comics.ComicsResponse;
import rx.Observable;

/**
 * @author Aydar Farrakhov
 */
public interface ComicsService {

    @GET("comics")
    Observable<ComicsResponse> comics(@Query("offset") Long offset, @Query("limit") Long limit);

    @GET("comics/{comicsId}")
    Observable<ComicsResponse> comics(@Path("comicsId") Long id);

}
