package ru.gdgkazan.marvel.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * @author Aydar Farrakhov
 */
public final class RepositoryProvider {

    private static ComicsRepository sComicsRepository;

    private static CharactersRepository sCharactersRepository;

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

    @NonNull
    public static CharactersRepository provideCharactersRepository() {
        if (sCharactersRepository == null) {
            sCharactersRepository = new DefaultCharactersRepository();
        }
        return sCharactersRepository;
    }

    public static void setCharactersRepository(CharactersRepository charactersRepository) {
        sCharactersRepository = charactersRepository;
    }

    @MainThread
    public static void init() {
        sComicsRepository = new DefaultComicsRepository();
    }
}
