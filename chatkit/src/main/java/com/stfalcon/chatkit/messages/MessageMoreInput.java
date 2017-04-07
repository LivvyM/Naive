package com.stfalcon.chatkit.messages;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.stfalcon.chatkit.utils.DeviceUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
    protected LinearLayout mLayoutMore;
    protected ImageView mImageModel;
    protected LinearLayout mLayoutParent;
    protected FrameLayout mLayoutMenuParent;
    protected ImageView mImageMore;
    protected RecyclerView mRecyclerMenu;
    protected MenuAdapter mMenuAdapter;

    protected EmoticonsEditText messageInput;
    protected Button messageSendButton;

    private Animation enterAnimation;

    private CharSequence input;
    private MessageInput.InputListener inputListener;

    private boolean isShowMoreMenu = false; //判断是否显示表情

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

    }

    private void init(Context context) {
        inflate(context, R.layout.view_message_more_input, this);

        messageInput = (EmoticonsEditText) findViewById(R.id.messageInput);
        messageSendButton = (Button) findViewById(R.id.messageSendButton);

        mEmoticonsFuncView = ((EmoticonsFuncView) findViewById(R.id.view_epv));
        mEmoticonsIndicatorView = (EmoticonsIndicatorView) findViewById(R.id.view_eiv);
        mEmoticonsToolBarView = (EmoticonsToolBarView) findViewById(R.id.view_etv);
        mLayoutFace = (LinearLayout)findViewById(R.id.mLayoutFace);
        mLayoutKeyboard = (RelativeLayout)findViewById(R.id.mLayoutKeyboard);
        mLayoutMore = (LinearLayout) findViewById(R.id.mLayoutMore);
        mImageModel = (ImageView) findViewById(R.id.mImageModel);
        mImageMore = (ImageView)findViewById(R.id.mImageMore);
        mLayoutParent = (LinearLayout) findViewById(R.id.mLayoutParent);
        mLayoutMenuParent = (FrameLayout) findViewById(R.id.mLayoutMenuParent);
        mRecyclerMenu = (RecyclerView) findViewById(R.id.mRecyclerMenu);

        enterAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.pop_enter_anim);

        messageSendButton.setOnClickListener(this);
        messageInput.addTextChangedListener(this);

        mLayoutMenuParent.setLayoutParams(new LinearLayout.LayoutParams(
                EmoticonsKeyboardUtils.getDisplayWidthPixels(getContext())
                ,EmoticonsKeyboardUtils.getDefKeyboardHeight(getContext()) - EmoticonsKeyboardUtils.dip2px(getContext(),54)));
        mLayoutMenuParent.setVisibility(GONE);

        messageInput.setFocusable(true);
        messageInput.setFocusableInTouchMode(true);
        messageInput.setText("");
        mEmoticonsFuncView .setOnIndicatorListener(this);
        mEmoticonsToolBarView.setOnToolBarItemClickListener(this);

        mImageModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isShowMoreMenu && mLayoutMore.getVisibility() != VISIBLE){
                    isShowMoreMenu = false;
                    dismissFaceView();

                }else{
                    isShowMoreMenu = true;
                    showFaceView();
                }
            }
        });

        mImageMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowMoreMenu && mLayoutKeyboard.getVisibility() != VISIBLE){
                    isShowMoreMenu = false;
                    dismissMoreMenuView();

                }else{
                    isShowMoreMenu = true;
                    showMoreMenuView();
                }
            }
        });

        messageInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(isShowMoreMenu){
                    isShowMoreMenu = false;
                    dismissMoreMenuView();
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

        initMenuView();
    }

    /**
     * 显示表情view
     */
    private void showFaceView(){
        if(mLayoutMore.getVisibility() == VISIBLE){
            mLayoutMore.setVisibility(View.GONE);
            mLayoutKeyboard.setVisibility(View.VISIBLE);
            mImageModel.setImageResource(R.drawable.ic_svg_message_broad);
        }else{
            mLayoutMore.setVisibility(View.GONE);
            mImageModel.setImageResource(R.drawable.ic_svg_message_broad);
            EmoticonsKeyboardUtils.closeSoftKeyboard(messageInput);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mLayoutParent.startAnimation(enterAnimation);
                    mLayoutMenuParent.setVisibility(VISIBLE);
                    mLayoutKeyboard.setVisibility(View.VISIBLE);
                }
            },100);
        }

    }

    private void dismissFaceView(){
        mLayoutMore.setVisibility(View.GONE);
        mLayoutKeyboard.setVisibility(View.GONE);
        mLayoutMenuParent.setVisibility(GONE);
        mImageModel.setImageResource(R.drawable.ic_svg_message_face);
        EmoticonsKeyboardUtils.openSoftKeyboard(messageInput);
    }


    private void showMoreMenuView(){
        mImageModel.setImageResource(R.drawable.ic_svg_message_face);
        if(mLayoutKeyboard.getVisibility() == VISIBLE){
            mLayoutKeyboard.setVisibility(View.GONE);
            mLayoutMore.setVisibility(View.VISIBLE);
        }else{
            mLayoutKeyboard.setVisibility(View.GONE);
            EmoticonsKeyboardUtils.closeSoftKeyboard(messageInput);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mLayoutParent.startAnimation(enterAnimation);
                    mLayoutMenuParent.setVisibility(View.VISIBLE);
                    mLayoutMore.setVisibility(View.VISIBLE);
                }
            },100);
        }
    }

    private void dismissMoreMenuView(){
        mLayoutKeyboard.setVisibility(View.GONE);
        mLayoutMore.setVisibility(View.GONE);
        mLayoutMenuParent.setVisibility(GONE);
        EmoticonsKeyboardUtils.openSoftKeyboard(messageInput);
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
        if(isShowMoreMenu){
            isShowMoreMenu = false;
            mImageModel.setImageResource(R.drawable.ic_svg_message_face);
            mLayoutMenuParent.setVisibility(View.GONE);
            return false;
        }else{
            return true;
        }
    }

    public void closeMore(){
        if(isShowMoreMenu){
            isShowMoreMenu = false;
            mLayoutMore.setVisibility(View.GONE);
            mLayoutKeyboard.setVisibility(View.GONE);
            mLayoutMenuParent.setVisibility(GONE);
            mImageModel.setImageResource(R.drawable.ic_svg_message_face);
        }else{
            isShowMoreMenu = true;
            EmoticonsKeyboardUtils.closeSoftKeyboard(messageInput);
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

    private void initMenuView(){
        mRecyclerMenu.setLayoutManager(new GridLayoutManager(getContext(),4));
        mMenuAdapter = new MenuAdapter();
        mRecyclerMenu.setAdapter(mMenuAdapter);
        mRecyclerMenu.addItemDecoration(new SpaceItemDecoration(30));
    }

    public void setMenuData(List<MenuEntity> data,OnMenuItemClickListener listener){
        entities.clear();
        entities.addAll(data);
        this.listener  = listener;
        mMenuAdapter.notifyDataSetChanged();
    }

    private List<MenuEntity> entities = new ArrayList<>();
    private OnMenuItemClickListener listener;

    public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder>{

        @Override
        public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MenuViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_input_menu,null));
        }

        @Override
        public void onBindViewHolder(MenuViewHolder holder, final int position) {
            MenuEntity entity = entities.get(position);
            holder.mImageIcon.setImageResource(entity.icon);
            holder.mTextTitle.setText(entity.title);

            if(listener != null){
                holder.mLayoutParent.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return entities.size();
        }
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{

        protected View mLayoutParent;
        protected ImageView mImageIcon;
        protected TextView mTextTitle;

        public MenuViewHolder(View view){
            super(view);
            mLayoutParent = view.findViewById(R.id.mLayoutParent);
            mImageIcon = (ImageView)view.findViewById(R.id.mImageIcon);
            mTextTitle = (TextView)view.findViewById(R.id.mTextTitle);
        }
    }

    public interface OnMenuItemClickListener{
        void onClick(int position);
    }

    public static class MenuEntity{
        private String title;
        private int icon;

        public MenuEntity(String title,int icon){
            this.icon = icon;
            this.title = title;
        }
    }

    private class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = DeviceUtil.dip2px(getContext(),space);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.bottom = space;
            outRect.top = space;
        }

    }
}
