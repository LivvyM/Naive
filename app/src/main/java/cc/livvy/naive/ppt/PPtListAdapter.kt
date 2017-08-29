package cc.livvy.naive.ppt

import android.view.View
import android.widget.ImageView
import cc.livvy.naive.R
import cc.livvy.widget.image.ImageViewUtils
import cc.livvy.widget.recyclerview.BaseQuickAdapter
import cc.livvy.widget.recyclerview.BaseViewHolder

/**
 * Created by livvy on 17-8-14.
 */
class PPtListAdapter(listener: OnItemClickListener) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_ppt_list) {

    val listener: OnItemClickListener

    init {
        this.listener = listener
    }


    override fun convert(helper: BaseViewHolder, item: String) {
        ImageViewUtils.bindImageView(helper.getView<ImageView>(R.id.mImageLogo), item)

        helper.getView<View>(R.id.mLayoutParent).setOnClickListener {
            listener.onItemClick("","")
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id: String, title: String)
    }
}