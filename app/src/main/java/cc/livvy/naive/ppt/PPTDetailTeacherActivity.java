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
import android.view.View;

import com.hyphenate.easeui.ui.EaseChatFragment;

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

        initCrystal();

        initTab();
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
        }, localImages).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
//        mCrystalCycleView.startTurning(5000);
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


}
