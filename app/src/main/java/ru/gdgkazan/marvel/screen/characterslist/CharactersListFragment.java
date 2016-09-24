package ru.gdgkazan.marvel.screen.characterslist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.gdgkazan.marvel.R;
import ru.gdgkazan.marvel.content.character.Character;
import ru.gdgkazan.marvel.general.LoadingDialog;
import ru.gdgkazan.marvel.general.LoadingView;
import ru.gdgkazan.marvel.screen.character.CharacterActivity;
import ru.gdgkazan.marvel.screen.common.CommonAdapter;
import ru.gdgkazan.marvel.screen.common.CommonListView;
import ru.gdgkazan.marvel.screen.common.CommonOnScrollListener;
import ru.gdgkazan.marvel.widget.BaseAdapter;
import ru.gdgkazan.marvel.widget.DividerItemDecoration;
import ru.gdgkazan.marvel.widget.EmptyRecyclerView;
import rx.Observable;

public class CharactersListFragment extends Fragment
        implements CommonListView<Character>,
        BaseAdapter.OnItemClickListener<Character> {

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    private LoadingView mLoadingView;

    private CommonAdapter<Character> mAdapter;

    private CharactersListPresenter mPresenter;

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
        View view = inflater.inflate(R.layout.fragment_comics_list, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle(getString(R.string.characters));

        mLoadingView = LoadingDialog.view(getChildFragmentManager());

        initRecycler();

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(getActivity(), getLoaderManager());
        mPresenter = new CharactersListPresenter(lifecycleHandler, this);
        mPresenter.init();
        return view;
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mRecyclerView.setEmptyView(mEmptyView);
        mRecyclerView.addOnScrollListener(new CommonOnScrollListener<Character>(layoutManager, this) {
            @Override
            public Observable<List<Character>> loadMoreItems(int page) {
                return mPresenter.loadMoreItems(page);
            }
        });

        mAdapter = getAdapter();
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);
    }

    private CommonAdapter<Character> getAdapter() {
        return new CommonAdapter<>(new ArrayList<>());
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
    public void showItems(@NonNull List<Character> comics) {
        mAdapter.changeDataSet(comics);
    }

    @Override
    public void addMoreItems(List<Character> items) {
        mAdapter.addAll(items);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), R.string.some_error, Toast.LENGTH_LONG).show();
        mAdapter.clear();
    }

    @Override
    public void showDetails(Character item) {
        CharacterActivity.start(getActivity(), item);
    }

    @Override
    public void onItemClick(@NonNull Character comics) {
        mPresenter.onItemClick(comics);
    }
}
