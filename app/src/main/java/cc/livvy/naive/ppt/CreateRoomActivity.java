package cc.livvy.naive.ppt;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.widget.EaseAlertDialog;

import cc.livvy.naive.R;
import cc.livvy.naive.base.AppBaseActivity;

/**
 * Created by livvy on 17-9-1.
 */

public class CreateRoomActivity extends AppBaseActivity {

    private EditText chatRoomNameEditText;
    private EditText introductionEditText;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat_room);

        setRightDrawable(R.drawable.ic_save);

        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        chatRoomNameEditText = (EditText) findViewById(R.id.edit_chat_room_name);
        introductionEditText = (EditText) findViewById(R.id.edit_chat_room_introduction);

    }

    public void save() {
        String name = chatRoomNameEditText.getText().toString();
        if (TextUtils.isEmpty(name)) {
            new EaseAlertDialog(this, "群组名称不能为空").show();
        } else {
            String st1 = "创建课程";
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(st1);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            final String chatRoomName = chatRoomNameEditText.getText().toString().trim();
            final String desc = introductionEditText.getText().toString();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final EMChatRoom chatRoom = EMClient.getInstance().chatroomManager().createChatRoom(chatRoomName, desc, "welcome join chat", 500, null);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                progressDialog.dismiss();
                                setResult(RESULT_OK);
                                finish();
                            }
                        });
                    } catch (final Exception e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                progressDialog.dismiss();
                                final String st2 = "创建课程失败";
                                Toast.makeText(CreateRoomActivity.this, st2 + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }

    public void back(View view) {
        finish();
    }

}
