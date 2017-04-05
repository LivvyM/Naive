package cc.livvy.naive.message.fixtures;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import cc.livvy.naive.message.models.DefaultUser;

/*
 * Created by troy379 on 12.12.16.
 */
public final class MessagesListFixtures extends FixturesData {
    private MessagesListFixtures() {
        throw new AssertionError();
    }

    public static ArrayList<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message message = new MessagesListFixtures.Message();

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            message.createdAt = calendar.getTime();

            messages.add(message);
        }
        return messages;
    }

    public static class Message implements IMessage {

        private long id;
        private String text;
        private Date createdAt;
        private int messageType = MESSAGE_TYPE_TEXT;

        public Message() {
            this(messages.get(rnd.nextInt(messages.size())));

        }

        public Message(String text) {
            this.text = text;
            this.id = UUID.randomUUID().getLeastSignificantBits();
            if(text.contains("http")){
                messageType = MESSAGE_TYPE_PICTURE;
            }else{
                messageType = MESSAGE_TYPE_TEXT;
            }
        }

        @Override
        public Date getCreatedAt() {
            return createdAt == null ? new Date() : createdAt;
        }

        @Override
        public String getId() {
            return String.valueOf(id);
        }

        @Override
        public IUser getUser() {
            return new DefaultUser(id % 2 == 0 ? "0" : "1", id % 2 == 0 ? names.get(0) : names.get(1),
                    id % 2 == 0 ? avatars.get(0) : avatars.get(1), true);
        }

        @Override
        public int getMessageType() {
            return messageType;
        }

        @Override
        public String getText() {
            return text;
        }

        public String getStatus() {
            return "Sent";
        }
    }
}
