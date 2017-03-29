package cc.livvy.widget.skin.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cc.livvy.common.base.BaseActivity;
import cc.livvy.widget.R;
import cc.livvy.widget.skin.IDynamicNewView;
import cc.livvy.widget.skin.ISkinUpdate;
import cc.livvy.widget.skin.SkinConfig;
import cc.livvy.widget.skin.attr.base.DynamicAttr;
import cc.livvy.widget.skin.loader.SkinInflaterFactory;
import cc.livvy.widget.skin.loader.SkinManager;
import cc.livvy.widget.skin.statusbar.StatusBarUtil;
import cc.livvy.widget.skin.utils.SkinL;


/**
 * Created by _SOLID
 * Date:2016/4/14
 * Time:10:24
 * Your activity need extend this
 */
public class SkinBaseActivity extends BaseActivity implements ISkinUpdate, IDynamicNewView {

    private SkinInflaterFactory mSkinInflaterFactory;

    private final static String TAG = "SkinBaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSkinInflaterFactory = new SkinInflaterFactory();
        mSkinInflaterFactory.setAppCompatActivity(this);
        LayoutInflaterCompat.setFactory(getLayoutInflater(), mSkinInflaterFactory);
        super.onCreate(savedInstanceState);
        changeStatusColor();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinManager.getInstance().attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
        mSkinInflaterFactory.clean();
    }

    @Override
    public void onThemeUpdate() {
        SkinL.i(TAG, "onThemeUpdate");
        mSkinInflaterFactory.applySkin();
        changeStatusColor();
    }

    public SkinInflaterFactory getInflaterFactory() {
        return mSkinInflaterFactory;
    }

    public void changeStatusColor() {
        if (!SkinConfig.isCanChangeStatusColor()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SkinL.i(TAG, "changeStatus");
            int color = SkinManager.getInstance().getColorPrimaryDark();
            StatusBarUtil statusBarBackground;
            if (color != -1){
                statusBarBackground = new StatusBarUtil(this, color);
            }else{
                statusBarBackground = new StatusBarUtil(this, 0xFFCCCCCC);
            }
            statusBarBackground.setStatusBarColor();
        }
    }

    @Override
    public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }

    @Override
    public void dynamicAddView(View view, String attrName, int attrValueResId) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, attrName, attrValueResId);
    }

    @Override
    public void dynamicAddFontView(TextView textView) {
        mSkinInflaterFactory.dynamicAddFontEnableView(textView);
    }

    public final void removeSkinView(View v) {
        mSkinInflaterFactory.removeSkinView(v);
    }

}
