package cc.livvy.naive.base;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cc.livvy.naive.R;
import cc.livvy.widget.skin.attr.base.AttrFactory;
import cc.livvy.widget.skin.base.SkinBaseActivity;

/**
 *
 * Created by livvy on 17-3-29.
 */

public class AppBaseActivity extends SkinBaseActivity{


    View mViewBack;
    ImageView mImageBack;
    TextView mTextTitle;
    View mLayoutTitleParent;
    View mViewRight;
    ImageView mImageRight;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(LayoutInflater.from(this).inflate(layoutResID, null));
        try{
            mViewBack = findViewById(R.id.mViewBack);
            mLayoutTitleParent = findViewById(R.id.mLayoutTitleParent);
            mImageBack = (ImageView)findViewById(R.id.mImageBack);
            mTextTitle = (TextView)findViewById(R.id.mTextTitle);
            mViewRight = findViewById(R.id.mViewRight);
            mImageRight = (ImageView)findViewById(R.id.mImageRight);

            mViewBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Throwable ex){
            //暂时不做任何处理
        }

        if(mLayoutTitleParent != null){
            addThemeBackground(mLayoutTitleParent,R.color.theme_color);
        }
        if(mTextTitle != null){
            addThemeTextColor(mTextTitle,R.color.theme_toolbar_title_text_color);
        }
        if(mImageBack != null){
            addThemeImageResource(mImageBack,R.drawable.ic_svg_back);
        }
        if(mViewBack != null){
            addThemeBackground(mViewBack,R.drawable.drawable_white_click_background);
        }
        if(mViewRight != null){
            addThemeBackground(mViewRight,R.drawable.drawable_white_click_background);
        }
    }

    public void setTitle(String title){
        if(mTextTitle != null){
            mTextTitle.setText(title);
        }
    }

    public void setRightDrawable(int drawable){
        if(mViewRight != null && mImageRight != null){
            mViewRight.setVisibility(View.VISIBLE);
            mImageRight.setBackgroundResource(drawable);
        }
    }

    public void setRightOnClickListener(View.OnClickListener listener){
        if(mViewRight !=  null){
            mViewRight.setOnClickListener(listener);
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

    public void addThemeTextColor(View view,int color){
        dynamicAddView(view, AttrFactory.SupportAttr.SUPPORT_ATTR_TEXT_COLOR,color);
    }

}
