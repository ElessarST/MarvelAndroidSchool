package ru.gdgkazan.marvel.screen.common;


import ru.gdgkazan.marvel.content.Image;

public interface ListItem {

    Long getId();

    String getName();

    String getDescription();

    Image getImage();
}
