package cc.livvy.naive.ppt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.utils.EaseCommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cc.livvy.naive.R;
import cc.livvy.naive.base.AppBaseParamActivity;
import cc.livvy.widget.cycleview.CrystalCycleView;
import cc.livvy.widget.cycleview.CusConvenientBanner;
import cc.livvy.widget.cycleview.adapter.CrystalViewHolderCreator;
import cc.livvy.widget.cycleview.listener.OnItemClickListener;

/**
 * Created by livvy on 17-8-17.
 */

public class PPTDetailTeacherActivity extends AppBaseParamActivity {

    public static PPTDetailTeacherActivity activityInstance;

    private CusConvenientBanner mCrystalCycleView;
    private RecyclerView mCommentRecyclerView;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mListFragment = new ArrayList<>();     //定义要装fragment的列表
    private List<String> mListTab = new ArrayList<>();            //tab名称列表

    private EaseChatFragment chatFragment;

    String toChatUsername;
    private String mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pptdetail_teacher);
        EventBus.getDefault().register(this);
        setTitle(mTitle);
        setRightDrawable(R.drawable.ic_svg_qun);

        activityInstance = this;
        //get user id or group id
        toChatUsername = getIntent().getExtras().getString("userId");

        chatFragment = new PPTChatFragment();
        //pass parameters to chat fragment
        chatFragment.setArguments(getIntent().getExtras());

        initView();
    }

    public static void showClass(Activity paramsActivity, String id, String title) {
        Intent intent = new Intent(paramsActivity, PPTDetailTeacherActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("id", id);
        paramsActivity.startActivity(intent);
    }

    @Override
    protected void onInitParams(Bundle bundle) {
        mTitle = bundle.getString("title", "");
    }

    private void initView() {
        mCrystalCycleView = (CusConvenientBanner) findViewById(R.id.mCrystalCycleView);
        mCrystalCycleView.setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setPageTransformer(CrystalCycleView.Transformer.ZoomOutSlideTransformer);


        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        mCommentRecyclerView = (RecyclerView) findViewById(R.id.mCommentRecyclerView);

        LinearLayoutManager layout = new LinearLayoutManager(this);
        mCommentRecyclerView.setLayoutManager(layout);

        initCrystal();

        initTab();
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
        mCommentRecyclerView.setAdapter(new BulletAdapter(this, list));
        mCommentRecyclerView.smoothScrollToPosition(list.size() - 1);

        scrollPage();
    }

    private List<Integer> localImages = new ArrayList<>();

    private void initCrystal() {
        localImages.add(R.drawable.img_ppt_1);
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
                EventBus.getDefault().post(new ChatEventBus(ChatEventBus.OPT_ACTION_SCROLL, position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mCrystalCycleView.setManualPageable(false);
    }

    private void initTab() {
        mListFragment.add(chatFragment);
        mListFragment.add(new DemoFragment());

        mListTab.add("课堂");
        mListTab.add("目录");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        mTabLayout.addTab(mTabLayout.newTab().setText(mListTab.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTab.get(1)));

        mViewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mListFragment.get(position);
        }

        @Override
        public int getCount() {
            return mListTab.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mListTab.get(position % mListTab.size());
        }
    }

    private void scrollPage(){
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername, EaseCommonUtils.getConversationType(3), true);
        java.util.List<EMMessage> var = conversation.getAllMessages();
        int index = -1;
        for (EMMessage item : var) {
            if (item.getBooleanAttribute("scroll_page",false)) {
                index = item.getIntAttribute("scroll_page_index",-1);
            }
        }

        if(index != -1 && mCrystalCycleView != null){
            mCrystalCycleView.setcurrentitem(index);
        }
    }

    /**
     * 监听操作行为
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ChatEventBus event) {
        if (event.opt == ChatEventBus.OPT_SEND_MESSAGE ||
                event.opt == ChatEventBus.OPT_JOIN_ROOM) {
            refreshBullet();
        }else if(event.opt == ChatEventBus.OPT_PERMISSION){
            if(mCrystalCycleView != null){
                mCrystalCycleView.setManualPageable(event.isOwner);
            }
        }else if(event.opt == ChatEventBus.OPT_RECEIVE){
            refreshBullet();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
