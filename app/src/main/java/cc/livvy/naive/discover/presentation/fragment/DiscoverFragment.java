package cc.livvy.naive.discover.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cc.livvy.naive.R;
import cc.livvy.naive.base.AppBaseFragment;
import cc.livvy.naive.discover.adapter.DiscoverItemAdapter;
import cc.livvy.naive.discover.fixtures.DiscoverListFixtures;

/**
 * Created by livvy on 17-3-31.
 */

public class DiscoverFragment extends AppBaseFragment{


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

        RecyclerView mRecyclerView = (RecyclerView)view.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new DiscoverItemAdapter(getContext(), DiscoverListFixtures.getDiscoverList()));
    }
}
