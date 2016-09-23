package ru.gdgkazan.marvel.screen.eventsList;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import ru.gdgkazan.marvel.content.event.Event;

/**
 * @author Olga Telezhnikova
 */
public class EventsListActivity extends AppCompatActivity implements EventsView {

    @Override
    public void showEvents(@NonNull List<Event> events) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
