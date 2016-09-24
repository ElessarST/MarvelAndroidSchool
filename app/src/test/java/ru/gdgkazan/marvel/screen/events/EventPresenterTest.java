package ru.gdgkazan.marvel.screen.events;

import android.database.Observable;
import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.content.character.Character;
import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.content.event.Event;
import ru.gdgkazan.marvel.repository.RepositoryProvider;
import ru.gdgkazan.marvel.screen.eventslist.EventsView;
import ru.gdgkazan.marvel.test.MockLifecycleHandler;
import ru.gdgkazan.marvel.test.TestEventsRepository;

/**
 * @author Olga Telezhnikova
 */
@RunWith(JUnit4.class)
public class EventPresenterTest {

    private EventsView mView;
    private EventPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        LifecycleHandler lifecycleHandler = new MockLifecycleHandler();
        mView = Mockito.mock(EventsView.class);

        mPresenter = new EventPresenter(lifecycleHandler, mView);
    }


    @Test
    public void testInitError() throws Exception {
        Event event = new Event();
        RepositoryProvider.setEventsRepository(new EventPresenterTest.TestRepository(true, event));
        mPresenter.init(1);
        Mockito.verify(mView).showLoading();
        Mockito.verify(mView).hideLoading();
        Mockito.verify(mView).showError();
    }

    @Test
    public void testInitSuccess() throws Exception {
        Event event = new Event();
        RepositoryProvider.setEventsRepository(new EventPresenterTest.TestRepository(false, event));
        mPresenter.init(1);
        Mockito.verify(mView).showLoading();
        Mockito.verify(mView).hideLoading();
        Mockito.verify(mView).showEvents(event);
    }

    private class TestRepository extends TestEventsRepository {
        private boolean error;
        private Event events;
        private List<Comics> mComics;
        private List<Character> mCharacters;

        public TestRepository(boolean error, Event event) {
            this.error = error;
            this.events = event;
        }

        public TestRepository(boolean error, List<Comics> comics, List<Character> characters){
            this.error = error;
            this.mComics = comics;
            this.mCharacters = characters;
        }

        @NonNull
        @Override
        public rx.Observable<Event> eventById(long id){
            if (this.error){
                return rx.Observable.error(new IOException());
            } else {
                return rx.Observable.just(this.events);
            }
        }
    }
}
