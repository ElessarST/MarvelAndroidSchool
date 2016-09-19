package ru.gdgkazan.marvel.api;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.gdgkazan.marvel.BuildConfig;

/**
 * @author Aydar Farrakhov
 */
public final class ApiFactory {

    private static OkHttpClient sClient;

    private static volatile ComicsService sComicsService;
    private static volatile CharactersService sCharacterService;
    private static volatile EventsService sEventsService;

    private ApiFactory() {
    }

    @NonNull
    public static ComicsService getComicsService() {
        ComicsService service = sComicsService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sComicsService;
                if (service == null) {
                    service = sComicsService = buildRetrofit().create(ComicsService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    public static CharactersService getCharactersService() {
        CharactersService service = sCharacterService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sCharacterService;
                if (service == null) {
                    service = sCharacterService = buildRetrofit().create(CharactersService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    public static EventsService getEventsService() {
        EventsService service = sEventsService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sEventsService;
                if (service == null) {
                    service = sEventsService = buildRetrofit().create(EventsService.class);
                }
            }
        }
        return service;
    }

    public static void recreate() {
        sClient = null;
        sClient = getClient();
        sComicsService = buildRetrofit().create(ComicsService.class);
        sEventsService = buildRetrofit().create(EventsService.class);
        sCharacterService = buildRetrofit().create(CharactersService.class);
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor.create())
                .addInterceptor(ApiKeyInterceptor.create())
                .build();
    }
}
