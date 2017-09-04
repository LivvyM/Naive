package cc.livvy.naive.ppt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMPageResult;
import com.hyphenate.exceptions.HyphenateException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cc.livvy.naive.R;
import cc.livvy.naive.base.AppBaseFragment;

/**
 * Created by livvy on 17-8-14.
 */

public class PPTFragment extends AppBaseFragment implements PPtListAdapter.OnItemClickListener {

    private PPtListAdapter adapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

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

        setRightImageDrawable(R.drawable.ic_add);

        setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateRoomActivity.class));
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.message_out_bubble);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new PPtListAdapter(this);
        mRecyclerView.setAdapter(adapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadAndShowData();
            }
        });

        mSwipeRefreshLayout.setRefreshing(true);
        loadAndShowData();
    }

    @Override
    public void onItemClick(@NotNull String id, @NotNull String title) {
        startActivity(new Intent(getActivity(), PPTDetailTeacherActivity.class).putExtra("chatType", 3).
                putExtra("userId", id));
    }

    private void loadAndShowData() {
        new Thread(new Runnable() {

            public void run() {
                try {
                    final EMPageResult<EMChatRoom> result = EMClient.getInstance().chatroomManager().fetchPublicChatRoomsFromServer(1, 50);
                    //get chat room list
                    final List<EMChatRoom> chatRooms = result.getData();
                    final List<EMChatRoom> chatRoomList = new ArrayList<>();
                    getActivity().runOnUiThread(new Runnable() {

                        public void run() {
                            mSwipeRefreshLayout.setRefreshing(false);
                            chatRoomList.addAll(chatRooms);
                            adapter.setNewData(chatRoomList);
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
