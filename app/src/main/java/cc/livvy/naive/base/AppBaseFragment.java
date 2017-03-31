package cc.livvy.naive.base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
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
    ImageView mImageRight;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            Typeface mTf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Regular.ttf");
            mLayoutTitleParent = view.findViewById(R.id.mLayoutTitleParent);
            mTextTitle = (TextView)view.findViewById(R.id.mTextTitle);
            mImageRight = (ImageView)view.findViewById(R.id.mImageRight);
            mTextTitle.setTypeface(mTf);
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

    public void setTitle(String title){
        if(mTextTitle != null){
            mTextTitle.setText(title);
        }
    }

    public void setRightImageDrawable(int res){
        if(mImageRight != null){
            mImageRight.setImageResource(res);
            addThemeImageResource(mImageRight,res);
        }
    }

    public void setOnRightClickListener(View.OnClickListener listener){
        if(mImageRight != null){
            mImageRight.setOnClickListener(listener);
        }
    }
}
