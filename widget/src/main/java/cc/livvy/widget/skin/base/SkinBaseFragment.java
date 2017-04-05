package cc.livvy.widget.skin.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cc.livvy.widget.skin.IDynamicNewView;
import cc.livvy.widget.skin.attr.base.AttrFactory;
import cc.livvy.widget.skin.attr.base.DynamicAttr;


/**
 * Created by _SOLID
 * Date:2016/4/14
 * Time:10:35
 * Desc:
 */
public class SkinBaseFragment extends Fragment implements IDynamicNewView {

    private IDynamicNewView mIDynamicNewView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mIDynamicNewView = (IDynamicNewView) context;
        } catch (ClassCastException e) {
            mIDynamicNewView = null;
        }
    }

    @Override
    public final void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
        if (mIDynamicNewView == null) {
            throw new RuntimeException("IDynamicNewView should be implements !");
        } else {
            mIDynamicNewView.dynamicAddView(view, pDAttrs);
        }
    }

    @Override
    public final void dynamicAddView(View view, String attrName, int attrValueResId) {
        mIDynamicNewView.dynamicAddView(view, attrName, attrValueResId);
    }

    @Override
    public final void dynamicAddFontView(TextView textView) {
        mIDynamicNewView.dynamicAddFontView(textView);
    }

    public final LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        LayoutInflater result = getActivity().getLayoutInflater();
        return result;
    }

    @Override
    public void onDestroyView() {
        removeAllView(getView());
        super.onDestroyView();
    }

    private final void removeAllView(View v) {
        if (v instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) v;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                removeAllView(viewGroup.getChildAt(i));
            }
            removeViewInSkinInflaterFactory(v);
        } else {
            removeViewInSkinInflaterFactory(v);
        }
    }

    private final void removeViewInSkinInflaterFactory(View v) {
        if (getContext() instanceof SkinBaseActivity) {
            SkinBaseActivity skinBaseActivity = (SkinBaseActivity) getContext();
            //移除SkinInflaterFactory中的v
            skinBaseActivity.removeSkinView(v);
        }
    }

    /**
     * support imageView
     * attr imageResource
     */
    public void addThemeImageResource(ImageView view, int res){
        dynamicAddView(view, AttrFactory.SupportAttr.SUPPORT_ATTR_IMAGE_RESOURCE,res);
    }

    public void addThemeBackground(View view,int color){
        dynamicAddView(view, AttrFactory.SupportAttr.SUPPORT_ATTR_BACKGROUND,color);
    }

    public void addThemeSwipeRefreshLayoutColor(SwipeRefreshLayout view, int color){
        dynamicAddView(view, AttrFactory.SupportAttr.SUPPORT_ATTR_SWIPE_REFRESH_LAYOUT_COLOR,color);
    }

    public void addThemeTextColor(View view,int color){
        dynamicAddView(view, AttrFactory.SupportAttr.SUPPORT_ATTR_TEXT_COLOR,color);
    }
}
