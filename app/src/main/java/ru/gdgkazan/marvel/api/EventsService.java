package ru.gdgkazan.marvel.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.gdgkazan.marvel.content.comics.ComicsResponse;
import ru.gdgkazan.marvel.content.event.EventsResponse;
import rx.Observable;

/**
 * Created by aydar on 17.09.16.
 */
public interface EventsService {

    /*
    * Fetches lists of events
    */
    @GET("events")
    Observable<EventsResponse> events(@Query("offset") Long offset, @Query("limit") Long limit);

    /*
    * Fetches a single event by id
    */
    @GET("events/{eventId}")
    Observable<EventsResponse> eventById(@Path("eventId") Long id);

}
