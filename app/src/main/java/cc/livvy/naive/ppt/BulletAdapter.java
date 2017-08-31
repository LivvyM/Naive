package cc.livvy.naive.ppt;

import android.content.Context;

import com.hyphenate.chat.EMMessage;

import java.util.List;

import cc.livvy.naive.R;
import cc.livvy.widget.recyclerview.BaseQuickAdapter;
import cc.livvy.widget.recyclerview.BaseViewHolder;

/**
 * Created by livvy on 17-8-30.
 */

public class BulletAdapter extends BaseQuickAdapter<EMMessage,BaseViewHolder>{

    public BulletAdapter(Context context, List<EMMessage> bullets){
        super(R.layout.item_bullet,bullets);
    }

    @Override
    protected void convert(BaseViewHolder helper, EMMessage item) {

        helper.setText(R.id.mTextBullet,item.getBody().toString());
    }
}
