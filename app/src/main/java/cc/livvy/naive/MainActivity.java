package cc.livvy.naive;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected FragmentTabHost fragmentTabHost;

    private String mTextTitle[] = {"home","compass","add","message","user"};

    private int mDrawableSelector[] = {
            R.drawable.main_tab_home_selector,
            R.drawable.main_tab_compass_selector,
            R.drawable.main_tab_add_selector,
            R.drawable.main_tab_message_selector,
            R.drawable.main_tab_user_selector
    };

    private Class mFragmentArray[] = {TestFragment.class, TestFragment.class, TestFragment.class, TestFragment.class, TestFragment.class};

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
        View view;
        if(i == 2){
            view = View.inflate(MainActivity.this, R.layout.tab_bottom_menu_center, null);
        }else{
            view = View.inflate(MainActivity.this, R.layout.tab_bottom_menu, null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.mImageIcon);
        imageView.setImageResource(mDrawableSelector[i]);
        return view;
    }

    public static int dip2px(Context context, float dpValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) (dpValue * metrics.density + 0.5f);
    }
}
