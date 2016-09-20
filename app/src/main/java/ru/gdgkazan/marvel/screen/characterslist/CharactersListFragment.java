package ru.gdgkazan.marvel.screen.characterslist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.gdgkazan.marvel.R;

public class CharactersListFragment extends Fragment {
    public CharactersListFragment() {
    }


    public static CharactersListFragment newInstance() {
        CharactersListFragment fragment = new CharactersListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters_list, container, false);
        getActivity().setTitle(getString(R.string.characters));
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