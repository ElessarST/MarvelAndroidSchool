package ru.gdgkazan.marvel.screen.eventslist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.gdgkazan.marvel.R;

public class EventsListFragment extends Fragment {



    public EventsListFragment() {
    }

    public static EventsListFragment newInstance() {
        EventsListFragment fragment = new EventsListFragment();
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
        getActivity().setTitle(getString(R.string.events));
        return view;
    }

}
