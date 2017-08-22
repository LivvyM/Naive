package cc.livvy.naive.mine.presentation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;

import cc.livvy.naive.R;
import cc.livvy.naive.mine.presentation.ThemeActivity;
import cc.livvy.naive.ui.SettingActivity;
import cc.livvy.widget.skin.base.SkinBaseFragment;

/**
 * 我的页面
 *
 * Created by livvy on 17-3-29.
 */

public class MineFragment extends SkinBaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view){
        View mLayoutTheme = view.findViewById(R.id.mLayoutTheme);
        View mLayoutAccount = view.findViewById(R.id.mLayoutAccount);
        ImageView mImageSet = (ImageView)view.findViewById(R.id.mImageSet);
        ImageView mImageTheme = (ImageView)view.findViewById(R.id.mImageTheme);
        TextView mTextName = (TextView)view.findViewById(R.id.mTextName);

        mLayoutTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ThemeActivity.class));
            }
        });

        mImageSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });

        if (!TextUtils.isEmpty(EMClient.getInstance().getCurrentUser())) {
            mTextName.setText(EMClient.getInstance().getCurrentUser());
        }

        addThemeBackground(mLayoutAccount,R.color.theme_color);
        addThemeImageResource(mImageSet,R.drawable.ic_svg_set);
        addThemeImageResource(mImageTheme,R.drawable.ic_svg_mine_theme);
        addThemeTextColor(mTextName,R.color.theme_text_color_mine_name);
    }

}
