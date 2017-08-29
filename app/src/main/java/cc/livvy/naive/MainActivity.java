package cc.livvy.naive;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.hyphenate.util.EMLog;

import cc.livvy.naive.base.AppBaseActivity;
import cc.livvy.naive.discover.presentation.fragment.DiscoverFragment;
import cc.livvy.naive.message.presentation.fragment.MessageFragment;
import cc.livvy.naive.mine.presentation.fragment.MineFragment;
import cc.livvy.naive.ppt.PPTFragment;

public class MainActivity extends AppBaseActivity {

    protected FragmentTabHost fragmentTabHost;

    private String mTextTitle[] = {"ppt", "discover", "message", "user"};

    private int mDrawableSelector[] = {
            R.drawable.main_tab_chat_selector,
            R.drawable.main_tab_compass_selector,
            R.drawable.main_tab_message_selector,
            R.drawable.main_tab_user_selector
    };

    private Class mFragmentArray[] = {PPTFragment.class, DiscoverFragment.class, TestFragment.class, MineFragment.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        showExceptionDialogFromIntent(getIntent());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        showExceptionDialogFromIntent(intent);
    }

    private void initView() {
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
        addThemeImageResource(imageView, mDrawableSelector[i]);
        addThemeBackground(mViewDot, R.drawable.main_tab_text_selector);

        return view;
    }

    private int getExceptionMessageId(String exceptionType) {
        if(exceptionType.equals(Constant.ACCOUNT_CONFLICT)) {
            return R.string.connect_conflict;
        }
        return R.string.Network_error;
    }

    private android.app.AlertDialog.Builder exceptionBuilder;
    private boolean isExceptionDialogShow =  false;
    public boolean isConflict = false;

    private void showExceptionDialog(String exceptionType) {
        isExceptionDialogShow = true;
        DemoHelper.getInstance().logout(false,null);
        String st = getResources().getString(R.string.Logoff_notification);
        if (!MainActivity.this.isFinishing()) {
            // clear up global variables
            try {
                if (exceptionBuilder == null)
                    exceptionBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
                exceptionBuilder.setTitle(st);
                exceptionBuilder.setMessage(getExceptionMessageId(exceptionType));
                exceptionBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        exceptionBuilder = null;
                        isExceptionDialogShow = false;
                        finish();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                exceptionBuilder.setCancelable(false);
                exceptionBuilder.create().show();
                isConflict = true;
            } catch (Exception e) {
            }
        }
    }

    private void showExceptionDialogFromIntent(Intent intent) {
        if (!isExceptionDialogShow && intent.getBooleanExtra(Constant.ACCOUNT_CONFLICT, false)) {
            showExceptionDialog(Constant.ACCOUNT_CONFLICT);
        } else if (!isExceptionDialogShow && intent.getBooleanExtra(Constant.ACCOUNT_REMOVED, false)) {
            showExceptionDialog(Constant.ACCOUNT_REMOVED);
        } else if (!isExceptionDialogShow && intent.getBooleanExtra(Constant.ACCOUNT_FORBIDDEN, false)) {
            showExceptionDialog(Constant.ACCOUNT_FORBIDDEN);
        } else if (intent.getBooleanExtra(Constant.ACCOUNT_KICKED_BY_CHANGE_PASSWORD, false) ||
                intent.getBooleanExtra(Constant.ACCOUNT_KICKED_BY_OTHER_DEVICE, false)) {
            this.finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


}
