package ru.gdgkazan.marvel.screen.character;

import android.support.annotation.NonNull;

import java.io.Serializable;

import ru.gdgkazan.marvel.content.character.Character;

/**
 * @author Olga Telezhnikova
 */
public class CharacterPresenter {

    private final CharacterView mView;

    public CharacterPresenter(@NonNull CharacterView view) {
        mView = view;
    }

    public void init(Serializable character) {
        if (character instanceof Character) {
            mView.show((Character) character);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
