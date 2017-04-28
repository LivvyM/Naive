package cc.livvy.naive.discover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cc.livvy.naive.R;
import cc.livvy.naive.discover.models.DiscoverModel;
import cc.livvy.widget.image.ImageViewUtils;
import cc.livvy.widget.recyclerview.BaseQuickAdapter;
import cc.livvy.widget.recyclerview.BaseViewHolder;

/**
 * 发现页面item
 *
 * Created by livvy on 17-3-31.
 */

public class DiscoverItemAdapter extends BaseQuickAdapter<DiscoverModel,DiscoverItemAdapter.DiscoverViewHolder>{

    private Context context;

    public DiscoverItemAdapter(Context context,List<DiscoverModel> discoverModels){
        super(R.layout.item_discover,discoverModels);
    }

    @Override
    protected void convert(DiscoverViewHolder helper, DiscoverModel item) {
        helper.bind(item);
    }

    @Override
    protected DiscoverViewHolder createBaseViewHolder(View view) {
        return new DiscoverViewHolder(view);
    }

    public class DiscoverViewHolder extends BaseViewHolder {

        ImageView mImageAvatar;
        ImageView mImagePicture;
        TextView mTextName;
        TextView mTextTime;
        TextView mTextTitle;
        TextView mTextContent;

        public DiscoverViewHolder(View view){
            super(view);

            mImageAvatar = (ImageView)view.findViewById(R.id.mImageAvatar);
            mImagePicture = (ImageView)view.findViewById(R.id.mImagePicture);
            mTextName = (TextView)view.findViewById(R.id.mTextName);
            mTextTime = (TextView)view.findViewById(R.id.mTextTime);
            mTextTitle = (TextView)view.findViewById(R.id.mTextTitle);
            mTextContent = (TextView)view.findViewById(R.id.mTextContent);
        }

        public void bind(DiscoverModel bean){
            Log.e("=======","111111111111111111111");
            ImageViewUtils.bindCircleImageView(mImageAvatar,bean.getAvatar());
            ImageViewUtils.bindImageView(mImagePicture,bean.getPhoto());

            mTextName.setText(bean.getName());
            mTextTime.setText(bean.getTime());
            mTextTitle.setText(bean.getContentTitle());
            mTextContent.setText(bean.getContent());
        }
    }
}
