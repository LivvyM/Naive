package cc.livvy.naive;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import cc.livvy.naive.base.AppBaseActivity;

/**
 * 启动页
 *
 * Created by livvy on 17-3-31.
 */

public class SplashActivity extends AppBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Typeface mTf = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");

        TextView mTextSplashTitle = (TextView)findViewById(R.id.mTextSplashTitle);
        mTextSplashTitle.setTypeface(mTf);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        },5000);
    }
}
