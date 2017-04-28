package cc.livvy.naive.message.contract;

/**
 * Created by livvy on 17-4-28.
 */

public interface ChatRoomContract{

    interface View{

        long getRoomId();
    }

    interface Presenter{

        void enter();

        void leave();
    }
}
