package ru.gdgkazan.marvel.screen.comicslist;

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
import ru.gdgkazan.marvel.general.LoadingDialog;
import ru.gdgkazan.marvel.general.LoadingView;
import ru.gdgkazan.marvel.widget.DividerItemDecoration;
import ru.gdgkazan.marvel.widget.EmptyRecyclerView;


public class ComicsListFragment extends Fragment implements ComicsView {


    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    private LoadingView mLoadingView;

    private ComicsListAdapter mAdapter;

    private ComicsListPresenter mPresenter;

    public ComicsListFragment() {
    }


    public static ComicsListFragment newInstance() {
        return new ComicsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comics_list, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle(getString(R.string.comics));

        mLoadingView = LoadingDialog.view(getChildFragmentManager());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mRecyclerView.setEmptyView(mEmptyView);

        mAdapter = new ComicsListAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(getActivity(), getLoaderManager());
        mPresenter = new ComicsListPresenter(lifecycleHandler, this);
        mPresenter.init();
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


    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void showComics(@NonNull List<Comics> comics) {
        mAdapter.changeDataSet(comics);
    }

    @Override
    public void showError() {
        mAdapter.clear();
    }

}
