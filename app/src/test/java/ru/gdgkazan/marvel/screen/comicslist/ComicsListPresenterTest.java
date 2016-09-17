package ru.gdgkazan.marvel.screen.comicslist;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import io.realm.RealmList;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.content.comics.ComicsResponseData;
import ru.gdgkazan.marvel.repository.RepositoryProvider;
import ru.gdgkazan.marvel.test.MockLifecycleHandler;
import ru.gdgkazan.marvel.test.TestComicsRepository;
import rx.Observable;

/**
 * Created by aydar on 17.09.16.
 */
public class ComicsListPresenterTest {

    private ComicsView mView;
    private ComicsListPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        LifecycleHandler lifecycleHandler = new MockLifecycleHandler();
        mView = Mockito.mock(ComicsView.class);

        mPresenter = new ComicsListPresenter(lifecycleHandler, mView);
    }


    @Test
    public void testInitError() throws Exception {
        ComicsResponseData responseData = new ComicsResponseData();
        RepositoryProvider.setComicsRepository(new TestRepository(true, responseData));
        mPresenter.init();
        Mockito.verify(mView).showLoading();
        Mockito.verify(mView).hideLoading();
        Mockito.verify(mView).showError();
    }

    @Test
    public void testInitSuccess() throws Exception {
        ComicsResponseData responseData = new ComicsResponseData();
        responseData.setResults(new RealmList<>());
        RepositoryProvider.setComicsRepository(new TestRepository(false, responseData));
        mPresenter.init();
        Mockito.verify(mView).showLoading();
        Mockito.verify(mView).hideLoading();
        Mockito.verify(mView).showComics(responseData.getResults());
    }

    private class TestRepository extends TestComicsRepository {
        private boolean error;
        private ComicsResponseData comics;

        public TestRepository(boolean error, ComicsResponseData comics) {
            this.error = error;
            this.comics = comics;
        }

        @NonNull
        @Override
        public Observable<ComicsResponseData> comics(){
            if (this.error){
                return Observable.error(new IOException());
            } else {
                return Observable.just(this.comics);
            }
        }
    }
}