package cc.livvy.widget.skin;

import android.content.Context;

import cc.livvy.widget.skin.attr.base.AttrFactory;
import cc.livvy.widget.skin.attr.base.SkinAttr;
import cc.livvy.widget.skin.utils.SkinPreferencesUtils;


/**
 * Created by _SOLID
 * Date:2016/4/13
 * Time:21:29
 */
public class SkinConfig {
    public static final String NAMESPACE = "http://schemas.android.com/android/skin";
    public static final String PREF_CUSTOM_SKIN_PATH = "skin_custom_path";
    public static final String PREF_FONT_PATH = "skin_font_path";
    public static final String DEFAULT_SKIN = "skin_default";
    public static final String ATTR_SKIN_ENABLE = "enable";

    public static final String SKIN_DIR_NAME = "skin";
    public static final String FONT_DIR_NAME = "fonts";
    private static boolean isCanChangeStatusColor = false;
    private static boolean isCanChangeFont = false;

    /**
     * get path of last skin package path
     *
     * @param context
     * @return path of skin package
     */
    public static String getCustomSkinPath(Context context) {
        return SkinPreferencesUtils.getString(context, PREF_CUSTOM_SKIN_PATH, DEFAULT_SKIN);
    }

    /**
     * save the skin's path
     *
     * @param context
     * @param path
     */
    public static void saveSkinPath(Context context, String path) {
        SkinPreferencesUtils.putString(context, PREF_CUSTOM_SKIN_PATH, path);
    }

    public static void saveFontPath(Context context, String path) {
        SkinPreferencesUtils.putString(context, PREF_FONT_PATH, path);
    }

    public static boolean isDefaultSkin(Context context) {
        return DEFAULT_SKIN.equals(getCustomSkinPath(context));
    }

    public static void setCanChangeStatusColor(boolean isCan) {
        isCanChangeStatusColor = isCan;
    }

    public static boolean isCanChangeStatusColor() {
        return isCanChangeStatusColor;
    }

    public static void setCanChangeFont(boolean isCan) {
        isCanChangeFont = isCan;
    }

    public static boolean isCanChangeFont() {
        return isCanChangeFont;
    }

    /**
     * add custom skin attribute support
     *
     * @param attrName attribute name
     * @param skinAttr skin attribute
     */
    public static void addSupportAttr(String attrName, SkinAttr skinAttr) {
        AttrFactory.addSupportAttr(attrName, skinAttr);
    }
}
