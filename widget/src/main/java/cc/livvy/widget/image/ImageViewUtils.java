package cc.livvy.widget.image;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cc.livvy.common.base.BaseApplication;
import cc.livvy.widget.R;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by livvy on 17-3-29.
 */

public class ImageViewUtils {

    /**
     * 添加圆形imageView
     */
    public static void bindCircleImageView(@NonNull ImageView view, String url) {
        Glide.with(BaseApplication.getInstance())
                .load(url)
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(BaseApplication.getInstance()))
                .placeholder(R.drawable.ic_svg_default_logo)
                .into(view);
    }

    public static void bindImageView(@NonNull ImageView view, String url){
        Glide.with(BaseApplication.getInstance())
                .load(url)
                .centerCrop()
                .into(view);
    }
}
