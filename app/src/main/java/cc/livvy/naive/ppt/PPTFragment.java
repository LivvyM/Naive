package cc.livvy.naive.ppt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cc.livvy.naive.R;
import cc.livvy.naive.base.AppBaseFragment;

/**
 * Created by livvy on 17-8-14.
 */

public class PPTFragment extends AppBaseFragment {

    private PPtListAdapter adapter;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ppt, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        setTitle(getString(R.string.app_name));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new PPtListAdapter();
        mRecyclerView.setAdapter(adapter);

        List<String> data = new ArrayList<>();
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502704026386&di=3250c5b87173069cbd92fa5ee51aeb5d&imgtype=0&src=http%3A%2F%2Fdynamic1.icourses.cn%2Fcoursepic%2F2014%2F0425%2F7313_480.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502704068622&di=fa09453a97a0b65c7336788b08e28131&imgtype=0&src=http%3A%2F%2Fs9.sinaimg.cn%2Fbmiddle%2F005XIuMAzy73v2qrOHe08%26690");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503298810&di=8a151b674f0e2e25537bdb61214bff0a&imgtype=jpg&er=1&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F35%2F39%2F03i58PICcvx_1024.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503298837&di=02905a64eab4befa307124ea047c25ea&imgtype=jpg&er=1&src=http%3A%2F%2Fs2.sinaimg.cn%2Fmw690%2F0069KLBUgy6VdIhqUWB91");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502704140417&di=97a08603be806dc97a0dae185eba27aa&imgtype=0&src=http%3A%2F%2Fdynamic1.icourses.cn%2Fcoursepic%2F2015%2F0517%2F4596_480.jpg");
        adapter.setNewData(data);

    }
}
