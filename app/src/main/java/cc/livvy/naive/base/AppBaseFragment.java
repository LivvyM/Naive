package cc.livvy.naive.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import cc.livvy.naive.R;
import cc.livvy.widget.skin.base.SkinBaseFragment;

/**
 *
 * Created by livvy on 17-3-29.
 */

public class AppBaseFragment extends SkinBaseFragment {

    View mViewBack;
    TextView mTextTitle;
    View mLayoutTitleParent;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            mViewBack = view.findViewById(R.id.mViewBack);
            mLayoutTitleParent = view.findViewById(R.id.mLayoutTitleParent);
            mTextTitle = (TextView)view.findViewById(R.id.mTextTitle);
            mViewBack.setVisibility(View.GONE);
        }catch (Exception e){
            //不做处理
        }
        if(mLayoutTitleParent != null){
            addThemeBackground(mLayoutTitleParent,R.color.theme_color);
        }
        if(mTextTitle != null){
            addThemeTextColor(mTextTitle,R.color.theme_toolbar_title_text_color);
        }
    }


}
