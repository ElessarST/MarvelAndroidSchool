package ru.gdgkazan.marvel.screen.character;


import ru.gdgkazan.marvel.content.character.Character;
import ru.gdgkazan.marvel.general.LoadingView;

interface CharacterView extends LoadingView {

    void show(Character character);

    void showError();

}
