package cc.livvy.widget.image;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.flexbox.FlexboxLayout;

import cc.livvy.common.base.BaseApplication;
import cc.livvy.widget.R;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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

    public static void bindMessageImageView(final @NonNull ImageView view, String url){
        Glide.with(BaseApplication.getInstance())
                .load(url)
                .centerCrop()
                .bitmapTransform(new RoundedCornersTransformation(BaseApplication.getInstance(),25,0))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        view.setLayoutParams(buildLayoutParams(view.getContext(),resource));
                        view.setImageDrawable(resource);
                    }
                });
    }

    private static FlexboxLayout.LayoutParams buildLayoutParams(Context context, GlideDrawable resource){
        int width = resource.getIntrinsicWidth();
        int height = resource.getIntrinsicHeight();
        int maxSize = getScreenWidth(context) / 2;
        double scale = (double) width / height;
        if(scale >= 1){
            /**
             * 宽图片
             */
            width = maxSize;
            height = (int)(maxSize * scale);
        }else{
            /**
             * 长图片
             */
            width = (int)(maxSize * scale);
            height = maxSize;
        }

        return new FlexboxLayout.LayoutParams(width,height);
    }

    // 获取屏幕的宽度
    private static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    // 获取屏幕的高度
    private static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }
}
