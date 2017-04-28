package cc.livvy.naive.message.presenter;

import com.stfalcon.chatkit.data.datasource.IRoomDataSource;
import com.stfalcon.chatkit.data.datasource.RoomDataSource;
import com.stfalcon.chatkit.data.models.Message;
import com.stfalcon.chatkit.data.observer.MessageObserverListener;

import cc.livvy.naive.message.contract.ChatRoomContract;

/**
 * Created by livvy on 17-4-28.
 */

public class ChatRoomPresenter implements ChatRoomContract.Presenter,MessageObserverListener {

    private final ChatRoomContract.View mView;
    private final IRoomDataSource roomDataSource;

    public ChatRoomPresenter(ChatRoomContract.View mView){
        this.mView = mView;
        this.roomDataSource = new RoomDataSource();
    }


    @Override
    public void enter() {

    }

    @Override
    public void leave() {
        roomDataSource.recycle();
    }

    @Override
    public void observerUpdate(int action, Message message) {

    }
}
