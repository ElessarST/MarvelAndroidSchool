package ru.gdgkazan.marvel.screen.characterslist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.gdgkazan.marvel.R;


/**
 * Created by Artur Badretdinov on 21.09.2016.
 */

public class CharacterFragment extends Fragment {
    public CharacterFragment() {
    }


    public static CharacterFragment newInstance() {
        CharacterFragment fragment = new CharacterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character, container, false);
        getActivity().setTitle(getString(R.string.character));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}