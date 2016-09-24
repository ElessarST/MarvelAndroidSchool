package ru.gdgkazan.marvel.content.comics;

import java.util.List;

import ru.gdgkazan.marvel.content.character.Character;
import ru.gdgkazan.marvel.content.event.Event;

/**
 * Created by aydar on 23.09.16.
 */

public class CharactersAndEvents {

    private List<Character> mCharacters;

    private List<Event> mEvents;

    public CharactersAndEvents(List<Character> characters, List<Event> events) {
        mCharacters = characters;
        mEvents = events;
    }

    public List<Character> getCharacters() {
        return mCharacters;
    }

    public void setCharacters(List<Character> characters) {
        mCharacters = characters;
    }

    public List<Event> getEvents() {
        return mEvents;
    }

    public void setEvents(List<Event> events) {
        mEvents = events;
    }
}
