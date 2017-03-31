package cc.livvy.naive.message.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.List;

import cc.livvy.naive.R;
import cc.livvy.naive.base.AppBaseFragment;
import cc.livvy.naive.message.fixtures.DialogsListFixtures;
import cc.livvy.naive.message.models.DefaultDialog;
import cc.livvy.naive.message.presentation.MessageActivity;
import cc.livvy.widget.image.ImageViewUtils;

/**
 * 消息
 *
 * Created by livvy on 17-3-30.
 */

public class MessageFragment extends AppBaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view){
        setTitle(getString(R.string.app_name));

        setRightImageDrawable(R.drawable.ic_svg_personal_center);

        DialogsList dialogsListView = (DialogsList) view.findViewById(R.id.dialogsList);

        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                ImageViewUtils.bindCircleImageView(imageView,url);
            }
        };
        DialogsListAdapter<DefaultDialog> dialogsListAdapter = new DialogsListAdapter<>(R.layout.item_dialog_custom,imageLoader);
        dialogsListAdapter.setItems(getDialogs());

        dialogsListAdapter.setOnDialogClickListener(new DialogsListAdapter.OnDialogClickListener<DefaultDialog>() {
            @Override
            public void onDialogClick(DefaultDialog dialog) {
                MessageActivity.showClass(getActivity(),dialog.getDialogName());
            }
        });

        dialogsListAdapter.setOnDialogLongClickListener(new DialogsListAdapter.OnDialogLongClickListener<DefaultDialog>() {
            @Override
            public void onDialogLongClick(DefaultDialog dialog) {
            }
        });

        dialogsListView.setAdapter(dialogsListAdapter);
    }

    private List<DefaultDialog> getDialogs() {
        return DialogsListFixtures.getChatList();
    }
}
