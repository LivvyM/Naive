package cc.livvy.naive.message.presentation;

import android.os.Bundle;
import android.widget.ImageView;

import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.List;

import cc.livvy.naive.R;
import cc.livvy.naive.base.AppBaseActivity;
import cc.livvy.naive.message.fixtures.DialogsListFixtures;
import cc.livvy.naive.message.models.DefaultDialog;
import cc.livvy.widget.image.ImageViewUtils;

/**
 *
 * Created by livvy on 17-3-30.
 */

public class Test extends AppBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_message);

        initView();
    }

    private void initView(){
        setTitle(getString(R.string.app_name));

        DialogsList dialogsListView = (DialogsList)findViewById(R.id.dialogsList);

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
