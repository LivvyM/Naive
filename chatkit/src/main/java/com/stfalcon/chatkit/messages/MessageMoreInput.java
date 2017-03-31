package com.stfalcon.chatkit.messages;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stfalcon.chatkit.R;
import com.stfalcon.chatkit.emoji.adpater.PageSetAdapter;
import com.stfalcon.chatkit.emoji.common.util.SimpleCommonUtils;
import com.stfalcon.chatkit.emoji.data.PageSetEntity;
import com.stfalcon.chatkit.emoji.interfaces.EmoticonClickListener;
import com.stfalcon.chatkit.emoji.utils.EmoticonsKeyboardUtils;
import com.stfalcon.chatkit.emoji.widget.EmoticonsEditText;
import com.stfalcon.chatkit.emoji.widget.EmoticonsFuncView;
import com.stfalcon.chatkit.emoji.widget.EmoticonsIndicatorView;
import com.stfalcon.chatkit.emoji.widget.EmoticonsToolBarView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 表情和更多功能
 *
 * Created by livvy on 17-3-30.
 */

public class MessageMoreInput extends RelativeLayout
        implements View.OnClickListener, TextWatcher,EmoticonsFuncView.OnEmoticonsPageViewListener,
        EmoticonsToolBarView.OnToolBarItemClickListener {

    protected EmoticonsFuncView mEmoticonsFuncView;
    protected EmoticonsIndicatorView mEmoticonsIndicatorView;
    protected EmoticonsToolBarView mEmoticonsToolBarView;
    protected LinearLayout mLayoutFace;
    protected RelativeLayout mLayoutKeyboard;
    protected ImageView mImageModel;
    protected LinearLayout mLayoutParent;
    protected ImageView mImageMore;

    protected EmoticonsEditText messageInput;
    protected Button messageSendButton;

    private Animation enterAnimation;

    private CharSequence input;
    private MessageInput.InputListener inputListener;

    private boolean isShowEmoji = true; //判断是否显示表情

    public MessageMoreInput(Context context) {
        super(context);
        init(context);
    }

    public MessageMoreInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MessageMoreInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * Set callback to be invoked when user entered his input
     *
     * @param inputListener input callback
     */
    public void setInputListener(MessageInput.InputListener inputListener) {
        this.inputListener = inputListener;
    }

    /**
     * Returns EditText for messages input
     *
     * @return EditText
     */
    public EditText getInputEditText() {
        return messageInput;
    }

    /**
     * Returns `submit` button
     *
     * @return ImageButton
     */
    public Button getButton() {
        return messageSendButton;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.messageSendButton) {
            boolean isSubmitted = onSubmit();
            if (isSubmitted) {
                messageInput.setText("");
            }
        }
    }

    /**
     * This method is called to notify you that, within s,
     * the count characters beginning at start have just replaced old text that had length before
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        input = s;
        messageSendButton.setEnabled(input.length() > 0);
    }

    /**
     * This method is called to notify you that, within s,
     * the count characters beginning at start are about to be replaced by new text with length after.
     */
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    /**
     * This method is called to notify you that, somewhere within s, the text has been changed.
     */
    @Override
    public void afterTextChanged(Editable editable) {

    }

    private boolean onSubmit() {
        return inputListener != null && inputListener.onSubmit(input);
    }

    private void init(Context context, AttributeSet attrs) {
        init(context);
        MessageInputStyle style = MessageInputStyle.parse(context, attrs);

        this.messageInput.setMaxLines(style.getInputMaxLines());
        this.messageInput.setHint(style.getInputHint());
        this.messageInput.setText(style.getInputText());
        this.messageInput.setTextSize(TypedValue.COMPLEX_UNIT_PX, style.getInputTextSize());
        this.messageInput.setTextColor(style.getInputTextColor());
        this.messageInput.setHintTextColor(style.getInputHintColor());
        this.messageInput.setBackground(style.getInputBackground());
        setCursor(style.getInputCursorDrawable());

//        this.messageSendButton.setBackground(style.getInputButtonBackground());
//        this.messageSendButton.setImageDrawable(style.getInputButtonIcon());
//        this.messageSendButton.getLayoutParams().width = style.getInputButtonWidth();
//        this.messageSendButton.getLayoutParams().height = style.getInputButtonHeight();
//        this.buttonSpace.getLayoutParams().width = style.getInputButtonMargin();

//        if (getPaddingLeft() == 0
//                && getPaddingRight() == 0
//                && getPaddingTop() == 0
//                && getPaddingBottom() == 0) {
//            setPadding(
//                    style.getInputDefaultPaddingLeft(),
//                    style.getInputDefaultPaddingTop(),
//                    style.getInputDefaultPaddingRight(),
//                    style.getInputDefaultPaddingBottom()
//            );
//        }
    }

    private void init(Context context) {
        inflate(context, R.layout.view_message_more_input, this);

        messageInput = (EmoticonsEditText) findViewById(R.id.messageInput);
        messageSendButton = (Button) findViewById(R.id.messageSendButton);
//        buttonSpace = (Space) findViewById(R.id.buttonSpace);

        mEmoticonsFuncView = ((EmoticonsFuncView) findViewById(R.id.view_epv));
        mEmoticonsIndicatorView = (EmoticonsIndicatorView) findViewById(R.id.view_eiv);
        mEmoticonsToolBarView = (EmoticonsToolBarView) findViewById(R.id.view_etv);
        mLayoutFace = (LinearLayout)findViewById(R.id.mLayoutFace);
        mLayoutKeyboard = (RelativeLayout)findViewById(R.id.mLayoutKeyboard);
        mImageModel = (ImageView) findViewById(R.id.mImageModel);
        mImageMore = (ImageView)findViewById(R.id.mImageMore);
        mLayoutParent = (LinearLayout) findViewById(R.id.mLayoutParent);

        enterAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.pop_enter_anim);

        messageSendButton.setOnClickListener(this);
        messageInput.addTextChangedListener(this);
        mLayoutKeyboard.setLayoutParams(new LinearLayout.LayoutParams(
                EmoticonsKeyboardUtils.getDisplayWidthPixels(getContext())
                ,EmoticonsKeyboardUtils.getDefKeyboardHeight(getContext()) - EmoticonsKeyboardUtils.dip2px(getContext(),54)));
        mLayoutKeyboard.setVisibility(View.GONE);

        messageInput.setFocusable(true);
        messageInput.setFocusableInTouchMode(true);
        messageInput.setText("");
        mEmoticonsFuncView .setOnIndicatorListener(this);
        mEmoticonsToolBarView.setOnToolBarItemClickListener(this);

        mImageModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isShowEmoji){
                    EmoticonsKeyboardUtils.closeSoftKeyboard(messageInput);
                    mImageModel.setImageResource(R.drawable.ic_svg_message_broad);
                    isShowEmoji = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mLayoutParent.startAnimation(enterAnimation);
                            mLayoutKeyboard.setVisibility(View.VISIBLE);
                        }
                    },100);


                }else{
                    isShowEmoji = true;
                    mLayoutKeyboard.setVisibility(View.GONE);
                    mImageModel.setImageResource(R.drawable.ic_svg_message_face);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            EmoticonsKeyboardUtils.openSoftKeyboard(messageInput);
                        }
                    },100);
                }
            }
        });

        messageInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!isShowEmoji){
                    isShowEmoji = true;
                    EmoticonsKeyboardUtils.openSoftKeyboard(messageInput);
                    mImageModel.setImageResource(R.drawable.ic_svg_message_face);
                    mLayoutKeyboard.setVisibility(View.GONE);
                }
                return false;
            }
        });

        messageInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.length() > 0){
                    mImageMore.setVisibility(INVISIBLE);
                    messageSendButton.setVisibility(VISIBLE);
                }else{
                    mImageMore.setVisibility(VISIBLE);
                    messageSendButton.setVisibility(INVISIBLE);
                }
            }
        });

        EmoticonClickListener emoticonClickListener = SimpleCommonUtils.getCommonEmoticonClickListener(messageInput);
        PageSetAdapter pageSetAdapter = new PageSetAdapter();
        SimpleCommonUtils.addEmojiPageSetEntity(pageSetAdapter, getContext(), emoticonClickListener);
        setAdapter(pageSetAdapter);
        SimpleCommonUtils.initEmoticonsEditText(messageInput);
    }

    public void setAdapter(PageSetAdapter pageSetAdapter) {
        if (pageSetAdapter != null) {
            ArrayList<PageSetEntity> pageSetEntities = pageSetAdapter.getPageSetEntityList();
            if (pageSetEntities != null) {
                for (PageSetEntity pageSetEntity : pageSetEntities) {
                    mEmoticonsToolBarView.addToolItemView(pageSetEntity);
                }
            }
        }
        mEmoticonsFuncView.setAdapter(pageSetAdapter);
    }

    private void setCursor(Drawable drawable) {
        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(this.messageInput, drawable);
        } catch (Exception ignore) {
        }
    }

    /**
     * Interface definition for a callback to be invoked when user entered his input
     */
    public interface InputListener {

        /**
         * Fires when user press send button.
         *
         * @param input input entered by user
         * @return if input text is valid, you must return {@code true} and input will be cleared, otherwise return false.
         */
        boolean onSubmit(CharSequence input);
    }

    public boolean isBack(){
        if(!isShowEmoji){
            isShowEmoji = true;
            mImageModel.setImageResource(R.drawable.ic_svg_message_face);
            mLayoutKeyboard.setVisibility(View.GONE);
            return false;
        }else{
            return true;
        }
    }

    public void closeMore(){
        if(isShowEmoji){
            isShowEmoji = false;
            EmoticonsKeyboardUtils.closeSoftKeyboard(messageInput);
        }else{
            isShowEmoji = true;
            mImageModel.setImageResource(R.drawable.ic_svg_message_face);
            mLayoutKeyboard.setVisibility(View.GONE);
        }
    }

    public void setImageModelDrawable(int res){
        if(mImageModel != null){
            mImageModel.setImageResource(res);
        }
    }

    public void setImageMoreDrawable(int res){
        if(mImageMore != null){
            mImageMore.setImageResource(res);
        }
    }


    @Override
    public void emoticonSetChanged(PageSetEntity pageSetEntity) {
        mEmoticonsToolBarView.setToolBtnSelect(pageSetEntity.getUuid());
    }

    @Override
    public void playTo(int position, PageSetEntity pageSetEntity) {
        mEmoticonsIndicatorView.playTo(position, pageSetEntity);
    }

    @Override
    public void playBy(int oldPosition, int newPosition, PageSetEntity pageSetEntity) {
        mEmoticonsIndicatorView.playBy(oldPosition, newPosition, pageSetEntity);
    }

    @Override
    public void onToolBarItemClick(PageSetEntity pageSetEntity) {
        mEmoticonsFuncView.setCurrentPageSet(pageSetEntity);
    }
}
