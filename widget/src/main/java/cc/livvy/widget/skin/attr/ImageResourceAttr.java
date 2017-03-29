package cc.livvy.widget.skin.attr;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import cc.livvy.widget.skin.attr.base.SkinAttr;
import cc.livvy.widget.skin.loader.SkinManager;

/**
 * Created by livvy on 17-3-29.
 */

public class ImageResourceAttr extends SkinAttr {


    @Override
    public void apply(View view) {

        if(view instanceof ImageView){
            ImageView imageView = (ImageView)view;
            if (RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)) {
                Drawable bg = SkinManager.getInstance().getDrawable(attrValueRefId);
                if(bg != null){
                    imageView.setImageDrawable(bg);
                }
            }
        }

    }
}
