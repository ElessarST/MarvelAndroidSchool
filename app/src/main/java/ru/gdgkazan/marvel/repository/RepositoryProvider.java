package ru.gdgkazan.marvel.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * @author Aydar Farrakhov
 */
public final class RepositoryProvider {

    private static ComicsRepository sComicsRepository;

    private RepositoryProvider() {
    }

    @NonNull
    public static ComicsRepository provideComicsRepository() {
        if (sComicsRepository == null) {
            sComicsRepository = new DefaultComicsRepository();
        }
        return sComicsRepository;
    }

    public static void setComicsRepository(ComicsRepository comicsRepository) {
        sComicsRepository = comicsRepository;
    }

    @MainThread
    public static void init() {
        sComicsRepository = new DefaultComicsRepository();
    }
}
