package cc.livvy.naive.ppt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cc.livvy.naive.R;
import cc.livvy.naive.base.AppBaseActivity;
import cc.livvy.naive.base.AppBaseParamActivity;
import cc.livvy.widget.recyclerview.BaseQuickAdapter;
import cc.livvy.widget.recyclerview.BaseViewHolder;

import static android.R.attr.data;

/**
 * Created by livvy on 17-8-31.
 */

public class CommentListActivity extends AppBaseParamActivity {


    private RecyclerView mRecyclerView;

    private String toChatUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        setTitle("详情");

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        refreshBullet();
    }

    public static void showClass(Activity paramsActivity, String toChatUsername){
        Intent intent = new Intent(paramsActivity,CommentListActivity.class);
        intent.putExtra("toChatUsername",toChatUsername);
        paramsActivity.startActivity(intent);

    }

    @Override
    protected void onInitParams(Bundle bundle) {
        toChatUsername = bundle.getString("toChatUsername","");
    }

    private void refreshBullet() {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername, EaseCommonUtils.getConversationType(3), true);
        java.util.List<EMMessage> var = conversation.getAllMessages();
        List<EMMessage> list = new ArrayList<>();
        for (EMMessage item : var) {
            if (!item.getBooleanAttribute("em_send_owner", false) && !item.getBooleanAttribute("scroll_page",false)) {
                list.add(item);
            }
        }
        mRecyclerView.setAdapter(new ItemAdapter(list));
        mRecyclerView.smoothScrollToPosition(list.size() - 1);

    }




    class ItemAdapter extends BaseQuickAdapter<EMMessage, BaseViewHolder> {

        public ItemAdapter(List<EMMessage> bullets) {
            super(R.layout.item_bullet_detail, bullets);
        }

        @Override
        protected void convert(BaseViewHolder helper, EMMessage item) {
            EMTextMessageBody txtBody = (EMTextMessageBody) item.getBody();

            helper.setText(R.id.mTextUserName, item.getFrom());
            helper.setText(R.id.mTextContent,txtBody.getMessage());
            helper.setText(R.id.mTextTime, DateUtils.getTimestampString(new Date(item.getMsgTime())));
        }
    }
}
