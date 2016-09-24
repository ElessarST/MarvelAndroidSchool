package ru.gdgkazan.marvel.screen.events;

import android.support.annotation.NonNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.content.event.Event;
import ru.gdgkazan.marvel.repository.RepositoryProvider;
import ru.gdgkazan.marvel.screen.common.CommonListView;
import ru.gdgkazan.marvel.screen.eventslist.EventsListPresenter;
import ru.gdgkazan.marvel.test.MockLifecycleHandler;
import ru.gdgkazan.marvel.test.TestEventsRepository;
import ru.gdgkazan.marvel.util.Constants;
import rx.Observable;

/**
 * @author Olga Telezhnikova
 */
@RunWith(JUnit4.class)
public class EventListPresenterTest {

    private CommonListView<Event> mView;
    private EventsListPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        LifecycleHandler lifecycleHandler = new MockLifecycleHandler();
        mView = Mockito.mock(CommonListView.class);

        mPresenter = new EventsListPresenter(lifecycleHandler, mView);
    }


    @Test
    public void testInitError() throws Exception {
        List<Event> events = new ArrayList<>();
        RepositoryProvider.setEventsRepository((new EventListPresenterTest.TestRepository(true, events)));
        mPresenter.init();
        Mockito.verify(mView).showLoading();
        Mockito.verify(mView).hideLoading();
        Mockito.verify(mView).showError();
    }

    @Test
    public void testInitSuccess() throws Exception {
        List<Event> events = new ArrayList<>();
        RepositoryProvider.setEventsRepository(new TestRepository(false, events));
        mPresenter.init();
        Mockito.verify(mView).showLoading();
        Mockito.verify(mView).hideLoading();
        Mockito.verify(mView).showItems(events);
    }

    @Test
    public void testUploadEvents(){
        List<Event> events = new ArrayList<>();
        RepositoryProvider.setEventsRepository(new TestRepository(false, events));
        mPresenter.init();
//        Assert.assertEquals(lenghtList,mPresenter.loadMoreItems(1).);
    }

    private class TestRepository extends TestEventsRepository {
        private boolean error;
        private List<Event> events;

        public TestRepository(boolean error, List<Event> events) {
            this.error = error;
            this.events = events;
        }

        @NonNull
        @Override
        public Observable<List<Event>> events(Long offset, Long limit) {
            if (this.error) {
                return Observable.error(new IOException());
            } else {
                return Observable.just(this.events);
            }
        }
    }
}
