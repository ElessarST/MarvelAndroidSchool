package ru.gdgkazan.marvel.screen.comicslist;

import android.support.annotation.NonNull;

import java.util.List;

import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.general.LoadingView;

/**
 * @author Aydar Farrakhov
 */
public interface ComicsView extends LoadingView {

    void showComics(@NonNull List<Comics> comics);

    void showError();
}
