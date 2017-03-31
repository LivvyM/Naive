package com.stfalcon.chatkit.emoji.common.filter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.stfalcon.chatkit.emoji.common.bean.EmojiDisplay;
import com.stfalcon.chatkit.emoji.common.bean.EmojiDisplayListener;
import com.stfalcon.chatkit.emoji.common.bean.EmojiSpan;
import com.stfalcon.chatkit.emoji.common.resource.DefXhsEmoticons;
import com.stfalcon.chatkit.emoji.common.resource.EmojiEmoticons;
import com.stfalcon.chatkit.emoji.interfaces.EmoticonFilter;
import com.stfalcon.chatkit.emoji.widget.EmoticonsTextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pzlvm on 2016/7/21.
 */
public class TextViewFilter {

    public static final Pattern ALL_RANGE = Pattern.compile("\\[[a-zA-Z0-9\\u4e00-\\u9fa5]+\\]");

    public static Matcher getMatcher(CharSequence matchStr) {
        return ALL_RANGE.matcher(matchStr);
    }

    public static Spannable filterFromImages(TextView tv_content, String content) {
        if(!(tv_content instanceof EmoticonsTextView)){
            return null;
        }
        if(!content.contains("[") || !content.contains("]")){
            return null;
        }
        return filterFromFile(tv_content.getContext(),new SpannableStringBuilder(content),
                EmojiDisplay.getFontHeight(tv_content), null);
    }

    public static Spannable filterFromFile(Context context, Spannable spannable, int fontSize, EmojiDisplayListener emojiDisplayListener) {
        if (spannable == null) {
            return null;
        }
        Matcher m = getMatcher(spannable.toString());
        if (m != null) {
            while (m.find()) {
                if (emojiDisplayListener == null) {
                    String name = m.group();
                    String nameValue = EmojiEmoticons.sEmojiEmoticonHashMap.get(name);
                    if("".equals(nameValue) || null == nameValue){
                        nameValue = DefXhsEmoticons.sXhsEmoticonHashMap.get(name);
                    }
                    if("".equals(nameValue) || null == nameValue){
                        nameValue = name;
                        continue;
                    }
                    Drawable drawable = EmoticonFilter.getDrawableFromAssets(context,nameValue);

                    if (drawable != null) {
                        drawable.setBounds(0, 0, fontSize, fontSize);
                        EmojiSpan imageSpan = new EmojiSpan(drawable);
                        spannable.setSpan(imageSpan, m.start(), m.end(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    }
                } else {
                    emojiDisplayListener.onEmojiDisplay(null, spannable, "", fontSize, m.start(), m.end());
                }
            }
        }
        return spannable;
    }
}
