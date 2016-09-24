package ru.gdgkazan.marvel.screen.common;

import android.support.annotation.NonNull;

import java.util.List;

import ru.gdgkazan.marvel.general.LoadingView;

/**
 * Created by aydar on 23.09.16.
 */

public interface CommonListView<T> extends LoadingView {

    void showItems(@NonNull List<T> items);

    void addMoreItems(List<T> items);

    void showError();

    void showDetails(T item);

}
