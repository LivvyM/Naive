package cc.livvy.widget.skin.attr.base;

import java.util.HashMap;

import cc.livvy.widget.skin.attr.BackgroundAttr;
import cc.livvy.widget.skin.attr.ImageResourceAttr;
import cc.livvy.widget.skin.attr.SwipeRefreshLayoutAttr;
import cc.livvy.widget.skin.attr.TextColorAttr;
import cc.livvy.widget.skin.utils.SkinL;


/**
 * Created by _SOLID
 * Date:2016/4/14
 * Time:9:47
 */
public class AttrFactory {

    private static String TAG = "AttrFactory";

    public static class SupportAttr{
        public final static String SUPPORT_ATTR_BACKGROUND = "background";
        public final static String SUPPORT_ATTR_TEXT_COLOR = "textColor";
        public final static String SUPPORT_ATTR_IMAGE_RESOURCE = "imageResource";
        public final static String SUPPORT_ATTR_SWIPE_REFRESH_LAYOUT_COLOR= "SwipeRefreshLayoutAttr";
    }

    public static HashMap<String, SkinAttr> mSupportAttr = new HashMap<>();

    static {
        mSupportAttr.put(SupportAttr.SUPPORT_ATTR_BACKGROUND, new BackgroundAttr());
        mSupportAttr.put(SupportAttr.SUPPORT_ATTR_TEXT_COLOR, new TextColorAttr());
        mSupportAttr.put(SupportAttr.SUPPORT_ATTR_IMAGE_RESOURCE,new ImageResourceAttr());
        mSupportAttr.put(SupportAttr.SUPPORT_ATTR_SWIPE_REFRESH_LAYOUT_COLOR,new SwipeRefreshLayoutAttr());
    }


    public static SkinAttr get(String attrName, int attrValueRefId, String attrValueRefName, String typeName) {
        SkinAttr mSkinAttr = mSupportAttr.get(attrName).clone();
        if (mSkinAttr == null) return null;
        mSkinAttr.attrName = attrName;
        mSkinAttr.attrValueRefId = attrValueRefId;
        mSkinAttr.attrValueRefName = attrValueRefName;
        mSkinAttr.attrValueTypeName = typeName;
        return mSkinAttr;
    }

    /**
     * check current attribute if can be support
     *
     * @param attrName
     * @return true : supported <br>
     * false: not supported
     */
    public static boolean isSupportedAttr(String attrName) {
        return mSupportAttr.containsKey(attrName);
    }

    /**
     * add support's attribute
     *
     * @param attrName attribute name
     * @param skinAttr skin attribute
     */
    public static void addSupportAttr(String attrName, SkinAttr skinAttr) {
        mSupportAttr.put(attrName, skinAttr);
    }
}
