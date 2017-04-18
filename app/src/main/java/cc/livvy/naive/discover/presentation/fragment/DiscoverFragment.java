package cc.livvy.naive.discover.presentation.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import cc.livvy.naive.R;
import cc.livvy.naive.base.AppBaseFragment;
import cc.livvy.naive.discover.adapter.DiscoverItemAdapter;
import cc.livvy.naive.discover.fixtures.DiscoverListFixtures;
import cc.livvy.widget.recyclerview.BaseQuickAdapter;

/**
 * 发现页
 *
 * Created by livvy on 17-3-31.
 */

public class DiscoverFragment extends AppBaseFragment implements BaseQuickAdapter.RequestLoadMoreListener,SwipeRefreshLayout.OnRefreshListener{

    RecyclerView mRecyclerView;
    DiscoverItemAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view){
        setTitle(getString(R.string.title_discover));

        mRecyclerView = (RecyclerView)view.findViewById(R.id.mRecyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.mSwipeRefreshLayout);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DiscoverItemAdapter(getContext(), DiscoverListFixtures.getDiscoverList());
        adapter.setOnLoadMoreListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        mSwipeRefreshLayout.setColorSchemeResources(R.color.theme_swipe_refresh_color);
        addThemeSwipeRefreshLayoutColor(mSwipeRefreshLayout,R.color.theme_swipe_refresh_color);

        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
                adapter.setNewData(DiscoverListFixtures.getDiscoverList());
            }
        },3000);
    }

    @Override
    public void onLoadMoreRequested() {
        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setEnabled(true);
                adapter.addData(DiscoverListFixtures.getDiscoverList());
                adapter.loadMoreComplete();
            }
        },3000);
    }
}
