package ru.gdgkazan.marvel.screen.eventslist;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.comics.Comics;
import ru.gdgkazan.marvel.content.event.Event;
import ru.gdgkazan.marvel.general.LoadingDialog;
import ru.gdgkazan.marvel.general.LoadingView;
import ru.gdgkazan.marvel.screen.common.CommonAdapter;
import ru.gdgkazan.marvel.screen.common.CommonListView;
import ru.gdgkazan.marvel.screen.common.CommonOnScrollListener;
import ru.gdgkazan.marvel.widget.BaseAdapter;
import ru.gdgkazan.marvel.widget.DividerItemDecoration;
import ru.gdgkazan.marvel.widget.EmptyRecyclerView;
import rx.Observable;

public class EventsListFragment extends Fragment implements CommonListView<Event>,
        BaseAdapter.OnItemClickListener<Event>{

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    private LoadingView mLoadingView;

    private CommonAdapter mAdapter;

    private EventsListPresenter mPresenter;

    public EventsListFragment() {
    }

    public static EventsListFragment newInstance() {
        return new EventsListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_list, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle(getString(R.string.events));

        mLoadingView = LoadingDialog.view(getChildFragmentManager());

        initRecycler();

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(getActivity(), getLoaderManager());
        mPresenter = new EventsListPresenter(lifecycleHandler, this);
        mPresenter.init();
        return view;
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mRecyclerView.setEmptyView(mEmptyView);
        mRecyclerView.addOnScrollListener(new CommonOnScrollListener<Event>(layoutManager, this) {
            @Override
            public Observable<List<Event>> loadMoreItems(int page) {
                return mPresenter.loadMoreItems(page);
            }
        });

        mAdapter = getAdapter();
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);
    }

    private CommonAdapter getAdapter() {
        return new CommonAdapter<>(new ArrayList<>());
    }

    @Override
    public void showItems(@NonNull List<Event> events) {
        mAdapter.changeDataSet(events);
    }

    @Override
    public void addMoreItems(List<Event> items) {
        mAdapter.addAll(items);
    }

    @Override
    public void showError() {
        mAdapter.clear();
    }

    @Override
    public void showDetails(Event item) {

    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void onItemClick(@NonNull Event item) {
        mPresenter.onItemClick(item);
    }
}
