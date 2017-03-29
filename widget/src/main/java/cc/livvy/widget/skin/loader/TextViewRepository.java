package cc.livvy.widget.skin.loader;

import android.graphics.Typeface;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cc.livvy.widget.skin.utils.TypefaceUtils;


/**
 * Created by _SOLID
 * Date:2016/7/12
 * Time:17:58
 */
public class TextViewRepository {
    private static List<TextView> mTextViews = new ArrayList<>();

    public static void add(TextView textView) {
        mTextViews.add(textView);
        textView.setTypeface(TypefaceUtils.CURRENT_TYPEFACE);
    }

    public static void clear() {
        mTextViews.clear();
    }

    public static void remove(TextView textView) {
        mTextViews.remove(textView);
    }

    public static void applyFont(Typeface tf) {
        for (TextView textView : mTextViews) {
            textView.setTypeface(tf);
        }
    }
}
