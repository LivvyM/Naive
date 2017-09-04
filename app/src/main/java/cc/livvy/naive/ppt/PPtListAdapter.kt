package cc.livvy.naive.ppt

import android.view.View
import android.widget.ImageView
import cc.livvy.naive.R
import cc.livvy.widget.image.ImageViewUtils
import cc.livvy.widget.recyclerview.BaseQuickAdapter
import cc.livvy.widget.recyclerview.BaseViewHolder
import com.hyphenate.chat.EMChatRoom

/**
 * Created by livvy on 17-8-14.
 */
class PPtListAdapter(listener: OnItemClickListener) : BaseQuickAdapter<EMChatRoom, BaseViewHolder>(R.layout.item_ppt_list) {

    val listener: OnItemClickListener

    init {
        this.listener = listener
    }


    override fun convert(helper: BaseViewHolder, item: EMChatRoom) {
        ImageViewUtils.bindImageView(helper.getView<ImageView>(R.id.mImageLogo),
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502704026386&di=3250c5b87173069cbd92fa5ee51aeb5d&imgtype=0&src=http%3A%2F%2Fdynamic1.icourses.cn%2Fcoursepic%2F2014%2F0425%2F7313_480.jpg")


        helper.setText(R.id.mTextTitle,item.name)

        helper.getView<View>(R.id.mLayoutParent).setOnClickListener {
            listener.onItemClick(item.id,"")
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id: String, title: String)
    }
}