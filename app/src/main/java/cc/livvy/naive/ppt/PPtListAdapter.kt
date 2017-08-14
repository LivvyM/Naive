package cc.livvy.naive.ppt

import android.widget.ImageView
import cc.livvy.naive.R
import cc.livvy.widget.image.ImageViewUtils
import cc.livvy.widget.recyclerview.BaseQuickAdapter
import cc.livvy.widget.recyclerview.BaseViewHolder

/**
 * Created by livvy on 17-8-14.
 */
class PPtListAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_ppt_list) {

    override fun convert(helper: BaseViewHolder, item: String) {
        ImageViewUtils.bindImageView(helper.getView<ImageView>(R.id.mImageLogo), item)
    }
}