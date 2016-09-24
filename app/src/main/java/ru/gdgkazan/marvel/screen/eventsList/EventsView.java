package ru.gdgkazan.marvel.screen.eventslist;

import android.support.annotation.NonNull;

import java.util.List;

import ru.gdgkazan.marvel.content.event.Event;
import ru.gdgkazan.marvel.general.LoadingView;

/**
 * @author Olga Telezhnikova
 */

public interface EventsView extends LoadingView {

    void showEvents(@NonNull Event events);

    void showError();
}

