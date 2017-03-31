package cc.livvy.naive.discover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cc.livvy.naive.R;
import cc.livvy.naive.discover.models.DiscoverModel;
import cc.livvy.widget.image.ImageViewUtils;

/**
 * 发现页面item
 *
 * Created by livvy on 17-3-31.
 */

public class DiscoverItemAdapter extends RecyclerView.Adapter<DiscoverItemAdapter.DiscoverViewHolder>{

    private Context context;
    private List<DiscoverModel> discoverModels;

    public DiscoverItemAdapter(Context context, List<DiscoverModel> list){
        this.context = context;
        this.discoverModels = list;
    }

    @Override
    public DiscoverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiscoverViewHolder(LayoutInflater.from(context).inflate(R.layout.item_discover, parent, false));
    }

    @Override
    public void onBindViewHolder(DiscoverViewHolder holder, int position) {
        holder.bind(discoverModels.get(position));
    }

    @Override
    public int getItemCount() {
        return discoverModels.size();
    }

    class DiscoverViewHolder extends RecyclerView.ViewHolder{

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
            ImageViewUtils.bindCircleImageView(mImageAvatar,bean.getAvatar());
            ImageViewUtils.bindImageView(mImagePicture,bean.getPhoto());

            mTextName.setText(bean.getName());
            mTextTime.setText(bean.getTime());
            mTextTitle.setText(bean.getContentTitle());
            mTextContent.setText(bean.getContent());
        }
    }
}
