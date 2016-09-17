package ru.gdgkazan.marvel.api;

import retrofit2.http.GET;
import ru.gdgkazan.marvel.content.comics.ComicsResponse;
import rx.Observable;

/**
 * @author Aydar Farrakhov
 */
public interface ComicsService {

    @GET("comics")
    Observable<ComicsResponse> comics();

}
