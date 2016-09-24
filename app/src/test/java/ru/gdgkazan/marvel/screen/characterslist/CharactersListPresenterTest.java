package ru.gdgkazan.marvel.screen.characterslist;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.marvel.content.character.Character;
import ru.gdgkazan.marvel.repository.CharactersRepository;
import ru.gdgkazan.marvel.repository.RepositoryProvider;
import ru.gdgkazan.marvel.screen.common.CommonListView;
import ru.gdgkazan.marvel.test.MockLifecycleHandler;
import rx.Observable;

import static junit.framework.Assert.assertNotNull;

public class CharactersListPresenterTest {

    private CommonListView<Character> mView;
    private CharactersListPresenter mPresenter;
    ArrayList<Character> characters;

    @Before
    public void setUp() throws Exception {
        mView = Mockito.mock(CommonListView.class);
        Character character = new Character();
        character.setId(1L);
        character.setName("Hell");
        character.setDescription("");
        characters = new ArrayList<>();
        characters.add(character);
        RepositoryProvider.setCharactersRepository(new TestRepository(false, characters));
        LifecycleHandler lifecycleHandler = new MockLifecycleHandler();
        mPresenter = new CharactersListPresenter(lifecycleHandler, mView);
    }

    @Test
    public void testCreated() throws Exception {
        assertNotNull(mPresenter);
    }

    @Test
    public void testNoActionsWithView() throws Exception {
        Mockito.verifyNoMoreInteractions(mView);
    }

    @Test
    public void init() throws Exception {
        mPresenter.init();
        Mockito.verify(mView).showLoading();
        Mockito.verify(mView).hideLoading();
        Mockito.verify(mView).showItems(characters);
    }

    @Test
    public void initError() throws Exception {
        RepositoryProvider.setCharactersRepository(new TestRepository(true, null));
        mPresenter.init();
        Mockito.verify(mView).showLoading();
        Mockito.verify(mView).hideLoading();
        Mockito.verify(mView).showError();
    }

    @Test
    public void loadMoreItems() throws Exception {
        mPresenter.loadMoreItems(1);
        Mockito.verifyNoMoreInteractions(mView);
    }

    @Test
    public void onItemClick() throws Exception {
        Character character = new Character();
        mPresenter.onItemClick(character);
        Mockito.verify(mView).showDetails(character);
    }

    private class TestRepository implements CharactersRepository {

        private boolean error;
        private List<Character> characters;

        public TestRepository(boolean error, List<Character> characters) {
            this.error = error;
            this.characters = characters;
        }

        @NonNull
        @Override
        public Observable<List<Character>> characters(Long offset, Long limit) {
            if (this.error) {
                return Observable.error(new IOException());
            } else {
                return Observable.just(this.characters);

            }
        }

        @Override
        public Observable<Character> characterById(long id) {
            return Observable.empty();
        }
    }


}