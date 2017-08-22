package cc.livvy.naive;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.util.EasyUtils;

import cc.livvy.naive.base.AppBaseActivity;

/**
 * 启动页
 *
 * Created by livvy on 17-3-31.
 */

public class SplashActivity extends AppBaseActivity{

    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Typeface mTf = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
//
//        TextView mTextSplashTitle = (TextView)findViewById(R.id.mTextSplashTitle);
//        mTextSplashTitle.setTypeface(mTf);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                finish();
//                startActivity(new Intent(SplashActivity.this,MainActivity.class));
//            }
//        },3000);


        DemoHelper.getInstance().initHandler(this.getMainLooper());

        RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.splash_root);
        TextView versionText = (TextView) findViewById(R.id.tv_version);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        rootLayout.startAnimation(animation);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Thread(new Runnable() {
            public void run() {
                if (DemoHelper.getInstance().isLoggedIn()) {
                    // auto login mode, make sure all group and conversation is loaed before enter the main screen
                    long start = System.currentTimeMillis();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    EMClient.getInstance().groupManager().loadAllGroups();
                    long costTime = System.currentTimeMillis() - start;
                    //wait
                    if (sleepTime - costTime > 0) {
                        try {
                            Thread.sleep(sleepTime - costTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String topActivityName = EasyUtils.getTopActivityName(EMClient.getInstance().getContext());
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }else {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }).start();

    }
}
