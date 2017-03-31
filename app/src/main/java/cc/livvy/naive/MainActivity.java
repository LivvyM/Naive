package cc.livvy.naive;

import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import cc.livvy.naive.base.AppBaseActivity;
import cc.livvy.naive.discover.presentation.fragment.DiscoverFragment;
import cc.livvy.naive.message.presentation.fragment.MessageFragment;
import cc.livvy.naive.mine.presentation.fragment.MineFragment;

public class MainActivity extends AppBaseActivity {

    protected FragmentTabHost fragmentTabHost;

    private String mTextTitle[] = {"chat","discover","message","user"};

    private int mDrawableSelector[] = {
            R.drawable.main_tab_chat_selector,
            R.drawable.main_tab_compass_selector,
            R.drawable.main_tab_message_selector,
            R.drawable.main_tab_user_selector
    };

    private Class mFragmentArray[] = {MessageFragment.class, DiscoverFragment.class, TestFragment.class, MineFragment.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.mLayoutContent);
        for (int i = 0; i < mDrawableSelector.length; i++) {
            TabHost.TabSpec spec = fragmentTabHost.newTabSpec(mTextTitle[i]).setIndicator(getView(i));
            fragmentTabHost.addTab(spec, mFragmentArray[i], null);
        }
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
    }

    private View getView(int i) {
        View view = View.inflate(MainActivity.this, R.layout.tab_bottom_menu, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.mImageIcon);
        View mViewDot = view.findViewById(R.id.mViewDot);
        imageView.setImageResource(mDrawableSelector[i]);

        /**
         * 添加主题设置
         */
        addThemeImageResource(imageView,mDrawableSelector[i]);
        addThemeBackground(mViewDot,R.drawable.main_tab_text_selector);

        return view;
    }
}
