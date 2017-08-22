package cc.livvy.naive.ppt;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cc.livvy.widget.cycleview.adapter.CrystalPageAdapter;


/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetworkImageHolderView implements CrystalPageAdapter.Holder<Integer>{
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        //todo测试
        int resId = data;
        Glide.with(context).load(data)
                .centerCrop()
                .crossFade()
                .placeholder(resId)
                .into(imageView);
    }
}
