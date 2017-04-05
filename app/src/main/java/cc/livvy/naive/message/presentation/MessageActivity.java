package cc.livvy.naive.message.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessageMoreInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.ArrayList;

import cc.livvy.naive.R;
import cc.livvy.naive.base.AppBaseParamActivity;
import cc.livvy.naive.message.fixtures.MessagesListFixtures;
import cc.livvy.widget.image.ImageViewUtils;

/**
 * 聊天页面
 *
 * Created by livvy on 17-3-30.
 */

public class MessageActivity extends AppBaseParamActivity implements MessagesListAdapter.SelectionListener{

    private MessagesList messagesList;
    private MessagesListAdapter<MessagesListFixtures.Message> adapter;
    private MessageMoreInput input;
    private int selectionCount;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        setTitle(title.equals("") ? "title" : title);

        initView();
    }

    public static void showClass(Activity activity,String title){
        Intent intent = new Intent(activity,MessageActivity.class);
        intent.putExtra("title",title);
        activity.startActivity(intent);
    }

    @Override
    protected void onInitParams(Bundle bundle) {
        title = bundle.getString("title","");
    }

    private void initView(){
        messagesList = (MessagesList) findViewById(R.id.messagesList);
        input = (MessageMoreInput) findViewById(R.id.input);

        messagesList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                input.closeMore();
                return false;
            }
        });

        input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                adapter.addToStart(new MessagesListFixtures.Message(input.toString()), true);
                return true;
            }
        });

        initMessagesAdapter();
    }

    private void initMessagesAdapter() {
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url,boolean isCircle) {
                if (isCircle){
                    ImageViewUtils.bindCircleImageView(imageView, url);
                }else{
                    ImageViewUtils.bindMessageImageView(imageView, url);
                }

            }
        };
        adapter = new MessagesListAdapter<>("0", imageLoader);
        adapter.enableSelectionMode(this);
        adapter.addToStart(new MessagesListFixtures.Message(), false);
        adapter.setLoadMoreListener(new MessagesListAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (totalItemsCount < 50) {
                    loadMessages();
                }
            }
        });

        messagesList.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (selectionCount == 0) {
            super.onBackPressed();
        } else {
            adapter.unselectAllItems();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 && input != null) {
            if(input.isBack()){
                return super.onKeyDown(keyCode, event);
            }else{
                return input.isBack();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onSelectionChanged(int count) {
    }

    private void loadMessages() {
        new Handler().postDelayed(new Runnable() { //imitation of internet connection
            @Override
            public void run() {
                ArrayList<MessagesListFixtures.Message> messages = MessagesListFixtures.getMessages();
                adapter.addToEnd(messages, true);
            }
        }, 1000);
    }
}
