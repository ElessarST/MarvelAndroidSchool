package ru.gdgkazan.marvel.screen.character;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import ru.gdgkazan.marvel.content.character.Character;

import static junit.framework.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class CharacterPresenterTest {

    private CharacterView mView;
    private CharacterPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mView = Mockito.mock(CharacterView.class);
        mPresenter = new CharacterPresenter(mView);
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
        Character character = new Character();
        mPresenter.init(character);
        Mockito.verify(mView).show(character);
    }

}