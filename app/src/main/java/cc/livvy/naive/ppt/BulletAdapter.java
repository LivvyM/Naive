package cc.livvy.naive.ppt;

import android.content.Context;
import android.view.View;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;

import cc.livvy.naive.R;
import cc.livvy.widget.recyclerview.BaseQuickAdapter;
import cc.livvy.widget.recyclerview.BaseViewHolder;

/**
 * Created by livvy on 17-8-30.
 */

public class BulletAdapter extends BaseQuickAdapter<EMMessage,BaseViewHolder>{

    private final OnItemClickListener listener;

    public BulletAdapter(List<EMMessage> bullets,OnItemClickListener listener){
        super(R.layout.item_bullet,bullets);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, EMMessage item) {

        EMTextMessageBody txtBody = (EMTextMessageBody) item.getBody();

        helper.setText(R.id.mTextBullet,txtBody.getMessage());
        helper.getView(R.id.mLayoutParent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick();
            }
        });
    }

    public interface OnItemClickListener{
        void onItemClick();
    }
}
