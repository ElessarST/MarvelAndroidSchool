package ru.gdgkazan.marvel.screen.comics;

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
import ru.gdgkazan.marvel.test.MockLifecycleHandler;
import ru.gdgkazan.marvel.test.TestComicsRepository;
import rx.Observable;

import static org.mockito.ArgumentMatchers.any;

/**
 * Created by aydar on 24.09.16.
 */
@RunWith(JUnit4.class)
public class ComicsPresenterTest {

    private ComicsView mView;
    private ComicsPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        LifecycleHandler lifecycleHandler = new MockLifecycleHandler();
        mView = Mockito.mock(ComicsView.class);

        mPresenter = new ComicsPresenter(lifecycleHandler, mView);
    }


    @Test
    public void testInitError() throws Exception {
        Comics comics = new Comics();
        RepositoryProvider.setComicsRepository(new ComicsPresenterTest.TestRepository(true, comics));
        mPresenter.init(1);
        Mockito.verify(mView).showLoading();
        Mockito.verify(mView).hideLoading();
        Mockito.verify(mView).showError();
    }

    @Test
    public void testInitSuccess() throws Exception {
        Comics comics = new Comics();
        RepositoryProvider.setComicsRepository(new ComicsPresenterTest.TestRepository(false, comics));
        mPresenter.init(1);
        Mockito.verify(mView).showLoading();
        Mockito.verify(mView).hideLoading();
        Mockito.verify(mView).showComics(comics);
    }

    @Test
    public void testLoadCharactersAndEventsSuccess() throws Exception {
        RepositoryProvider.setComicsRepository(new ComicsPresenterTest.TestRepository(false,
                new ArrayList<>(), new ArrayList<>()));
        mPresenter.loadCharactersAndEvents(1);
        Mockito.verify(mView).showAdditionalLoading();
        Mockito.verify(mView).hideAdditionalLoading();
        Mockito.verify(mView).showEventsAndCharacters(any());
    }

    @Test
    public void testLoadCharactersAndEventsError() throws Exception {
        RepositoryProvider.setComicsRepository(new ComicsPresenterTest.TestRepository(true,
                new ArrayList<>(), new ArrayList<>()));
        mPresenter.loadCharactersAndEvents(1);
        Mockito.verify(mView).showAdditionalLoading();
        Mockito.verify(mView).hideAdditionalLoading();
        Mockito.verify(mView).showError();
    }

    @Test
    public void testOnEventClick() throws Exception {
        Event event = new Event();
        mPresenter.onEventClick(event);
        Mockito.verify(mView).showEvent(event);
    }

    @Test
    public void testOnCharacterClick() throws Exception {
        Character character = new Character();
        mPresenter.onCharacterClick(character);
        Mockito.verify(mView).showCharacter(character);
    }



    private class TestRepository extends TestComicsRepository {
        private boolean error;
        private Comics comics;
        private List<Event> mEvents;
        private List<Character> mCharacters;

        public TestRepository(boolean error, Comics comics) {
            this.error = error;
            this.comics = comics;
        }

        public TestRepository(boolean error, List<Event> events, List<Character> characters){
            this.error = error;
            this.mEvents = events;
            this.mCharacters = characters;
        }

        @NonNull
        @Override
        public Observable<Comics> comics(Long id){
            if (this.error){
                return Observable.error(new IOException());
            } else {
                return Observable.just(this.comics);
            }
        }

        @Override
        public Observable<List<Character>> characters(Long id) {
            if (this.error){
                return Observable.error(new IOException());
            } else {
                return Observable.just(this.mCharacters);
            }
        }

        @Override
        public Observable<List<Event>> events(Long id) {
            if (this.error){
                return Observable.error(new IOException());
            } else {
                return Observable.just(this.mEvents);
            }
        }
    }
}