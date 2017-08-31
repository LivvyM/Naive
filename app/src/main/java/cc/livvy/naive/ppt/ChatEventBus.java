package cc.livvy.naive.ppt;

/**
 * Created by livvy on 17-8-30.
 */

public class ChatEventBus {
    /**
     * 无操作
     */
    public static final int OPT_NULL = -1;

    public static final int OPT_JOIN_ROOM = 1001;

    public static final int OPT_SEND_MESSAGE = 1002;

    public static final int OPT_PERMISSION = 1003;

    public static final int OPT_RECEIVE = 1004;

    public static final int OPT_ACTION_SCROLL = 1005;

    public int opt = OPT_NULL;
    public ChatEventBus(int opt){
        this.opt = opt;
    }

    public boolean isOwner = false;
    public ChatEventBus(int opt,boolean isOwner){
        this.opt = opt;
        this.isOwner = isOwner;
    }

    public int page = 0;
    public ChatEventBus(int opt,int page){
        this.opt = opt;
        this.page = page;
    }
}
