package cc.livvy.naive.ppt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.EMVoiceMessageBody;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.widget.chatrow.EaseChatRowVoicePlayClickListener;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cc.livvy.naive.R;
import cc.livvy.naive.base.AppBaseParamActivity;
import cc.livvy.widget.cycleview.CrystalCycleView;
import cc.livvy.widget.cycleview.CusConvenientBanner;
import cc.livvy.widget.cycleview.adapter.CrystalViewHolderCreator;
import cc.livvy.widget.recyclerview.BaseSectionQuickAdapter;
import cc.livvy.widget.recyclerview.BaseViewHolder;
import cc.livvy.widget.recyclerview.entity.SectionEntity;

import static android.R.attr.data;

/**
 * Created by livvy on 17-8-31.
 */

public class PPTDetailPlayActivity extends AppBaseParamActivity {

    private CusConvenientBanner mCrystalCycleView;
    private RecyclerView mRecyclerView;
    private String toChatUsername;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppt_play);

        setTitle("演示");

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCrystalCycleView = (CusConvenientBanner) findViewById(R.id.mCrystalCycleView);
        mCrystalCycleView.setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setPageTransformer(CrystalCycleView.Transformer.ZoomOutSlideTransformer);


        initCrystal();
        refreshBullet();
    }

    public static void showClass(Activity paramsActivity, String toChatUsername) {
        Intent intent = new Intent(paramsActivity, PPTDetailPlayActivity.class);
        intent.putExtra("toChatUsername", toChatUsername);
        paramsActivity.startActivity(intent);

    }

    @Override
    protected void onInitParams(Bundle bundle) {
        toChatUsername = bundle.getString("toChatUsername", "");
    }

    private void refreshBullet() {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername, EaseCommonUtils.getConversationType(3), true);
        java.util.List<EMMessage> var = conversation.getAllMessages();
        List<ChatSection> list = new ArrayList<>();
        String title = "";
        for (EMMessage item : var) {
            if (item.getBooleanAttribute("em_send_owner", false) && !item.getBooleanAttribute("scroll_page", false)
                    && item.getIntAttribute("em_pager", -1) != -1) {
                if (!title.equals(String.valueOf(item.getIntAttribute("em_pager", -1)))) {
                    title = String.valueOf(item.getIntAttribute("em_pager", -1));
                    list.add(new ChatSection(true, title));
                }
                list.add(new ChatSection(item));
            }
        }
        mRecyclerView.setAdapter(new ItemAdapter(list));
    }

    private List<Integer> localImages = new ArrayList<>();
    private void initCrystal() {
        localImages.add(R.drawable.img_ppt_2);
        localImages.add(R.drawable.img_ppt_3);
        localImages.add(R.drawable.img_ppt_4);
        localImages.add(R.drawable.img_ppt_5);
        localImages.add(R.drawable.img_ppt_6);
        mCrystalCycleView.setVisibility(View.VISIBLE);
        mCrystalCycleView.setPageTransformer(CrystalCycleView.Transformer.DefaultTransformer);
        mCrystalCycleView.setPages(new CrystalViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();
            }
        }, localImages).setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mCrystalCycleView.setManualPageable(true);
    }



    private class ItemAdapter extends BaseSectionQuickAdapter<ChatSection, BaseViewHolder> {

        public ItemAdapter(List<ChatSection> bullets) {
            super(R.layout.item_chapter_layout, R.layout.item_chapter_header, bullets);
        }

        @Override
        protected void convertHead(BaseViewHolder helper, final ChatSection item) {
            helper.setText(R.id.mTextChapterName, "滑至第" + item.header + "页");
        }

        @Override
        protected void convert(final BaseViewHolder helper, final ChatSection item) {

            Date date = new Date(item.t.getMsgTime());
            String content = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(data);
            helper.setText(R.id.mTextChapterTime,content);

            if(item.t.getType() == EMMessage.Type.TXT){
                EMTextMessageBody txtBody = (EMTextMessageBody) item.t.getBody();

                helper.setText(R.id.mTextChapterContent, txtBody.getMessage());
                helper.getView(R.id.mLayoutParent).setOnClickListener(null);
                helper.getView(R.id.voiceIconView).setVisibility(View.GONE);
            }else if(item.t.getType() == EMMessage.Type.VOICE){
                EMVoiceMessageBody voiceBody = (EMVoiceMessageBody) item.t.getBody();

                helper.setText(R.id.mTextChapterContent, "语音消息  " + voiceBody.getLength() + "\"");
                helper.getView(R.id.voiceIconView).setVisibility(View.VISIBLE);
                helper.getView(R.id.mLayoutParent).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        }
    }

    class ChatSection extends SectionEntity<EMMessage> {
        public ChatSection(boolean isHeader, String header) {
            super(isHeader, header);
        }

        public ChatSection(EMMessage emMessage) {
            super(emMessage);
        }
    }
}
