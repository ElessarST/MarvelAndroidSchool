package ru.gdgkazan.marvel.screen.comicslist;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.repository.RepositoryProvider;
import ru.gdgkazan.marvel.screen.common.CommonListView;
import ru.gdgkazan.marvel.test.MockLifecycleHandler;
import ru.gdgkazan.marvel.test.TestComicsRepository;
import rx.Observable;

/**
 * Created by aydar on 17.09.16.
 */
public class ComicsListPresenterTest {

    private CommonListView<Comics> mView;
    private ComicsListPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        LifecycleHandler lifecycleHandler = new MockLifecycleHandler();
        mView = Mockito.mock(CommonListView.class);

        mPresenter = new ComicsListPresenter(lifecycleHandler, mView);
    }


    @Test
    public void testInitError() throws Exception {
        List<Comics> comicses = new ArrayList<>();
        RepositoryProvider.setComicsRepository(new TestRepository(true, comicses));
        mPresenter.init();
        Mockito.verify(mView).showLoading();
        Mockito.verify(mView).hideLoading();
        Mockito.verify(mView).showError(new Throwable());
    }

    @Test
    public void testInitSuccess() throws Exception {
        List<Comics> comicses = new ArrayList<>();
        RepositoryProvider.setComicsRepository(new TestRepository(false, comicses));
        mPresenter.init();
        Mockito.verify(mView).showLoading();
        Mockito.verify(mView).hideLoading();
        Mockito.verify(mView).showItems(comicses);
    }

    private class TestRepository extends TestComicsRepository {
        private boolean error;
        private List<Comics> comics;

        public TestRepository(boolean error, List<Comics> comics) {
            this.error = error;
            this.comics = comics;
        }

        @NonNull
        @Override
        public Observable<List<Comics>> comics(Long offset, Long limit){
            if (this.error){
                return Observable.error(new IOException());
            } else {
                return Observable.just(this.comics);
            }
        }
    }
}