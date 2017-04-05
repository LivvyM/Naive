package cc.livvy.widget.skin.attr;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import cc.livvy.widget.skin.attr.base.SkinAttr;
import cc.livvy.widget.skin.loader.SkinManager;

/**
 * Created by livvy on 17-3-31.
 */

public class SwipeRefreshLayoutAttr  extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof SwipeRefreshLayout) {
            SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) view;
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
                int color = SkinManager.getInstance().getColor(attrValueRefId);
                mSwipeRefreshLayout.setColorSchemeColors(color);
            }
        }
    }
}
