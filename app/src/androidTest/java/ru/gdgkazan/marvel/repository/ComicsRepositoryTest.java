package ru.gdgkazan.marvel.repository;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;
import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.test.RxSchedulersTestRule;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;

/**
 * Created by aydar on 24.09.16.
 */
@RunWith(AndroidJUnit4.class)
public class ComicsRepositoryTest {


    @Rule
    public RxSchedulersTestRule mRule = new RxSchedulersTestRule();

    private ComicsRepository mRepository;

    @Before
    public void setUp() throws Exception {
        mRepository = new DefaultComicsRepository();
    }


    @Test
    public void testLoadComics() throws Exception {
        TestSubscriber<Comics> testSubscriber = new TestSubscriber<>();
        mRepository.comics(0L, 10L).flatMap(Observable::from).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(10);
    }

    @Test
    public void testLoadSingleComics() throws Exception {
        TestSubscriber<Comics> testSubscriber = new TestSubscriber<>();
        mRepository.comics(1L).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
    }

    @Test
    public void testComicsSaved() throws Exception {
        mRepository.comics(0L, 10L).subscribe();

        int savedCount = Realm.getDefaultInstance()
                .where(Comics.class)
                .findAll()
                .size();
        assertEquals(10, savedCount);
    }

    @After
    public void tearDown() throws Exception {
        Realm.getDefaultInstance().executeTransaction(realm -> realm.delete(Comics.class));
    }

}