package cc.livvy.naive.message.fixtures;

import android.support.annotation.NonNull;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import cc.livvy.naive.message.models.DefaultDialog;
import cc.livvy.naive.message.models.DefaultUser;

/**
 * Created by Anton Bevza on 07.09.16.
 */
public final class DialogsListFixtures extends FixturesData {
    private DialogsListFixtures() {
        throw new AssertionError();
    }

    public static ArrayList<DefaultDialog> getChatList() {
        ArrayList<DefaultDialog> chats = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            chats.add(getDialog(i));
        }
        return chats;
    }

    private static IMessage getMessage(final Date date) {
        return new IMessage() {
            @Override
            public String getId() {
                return Long.toString(UUID.randomUUID().getLeastSignificantBits());
            }

            @Override
            public String getText() {
                return messages.get(rnd.nextInt(messages.size()));
            }

            @Override
            public IUser getUser() {
                return DialogsListFixtures.getUser();
            }

            @Override
            public Date getCreatedAt() {
                return date;
            }

            @Override
            public int getMessageType() {
                return MESSAGE_TYPE_TEXT;
            }
        };
    }

    private static DefaultDialog getDialog(int i) {
        ArrayList<IUser> users = getUsers();
//        return new DefaultDialog(String.valueOf(UUID.randomUUID().getLeastSignificantBits()),
//                users.size() > 1 ? groupChatTitles.get(users.size() - 2) : users.get(0).getName(),
//                users.size() > 1 ? groupChatImages.get(users.size() - 2) : avatars.get(rnd.nextInt(4)),
//                users,
//                getMessage(Calendar.getInstance().getTime()), i < 3 ? 3 - i : 0);
        return new DefaultDialog(String.valueOf(UUID.randomUUID().getLeastSignificantBits()),
                users.get(0).getName(),
                avatars.get(rnd.nextInt(4)),
                users,
                getMessage(Calendar.getInstance().getTime()), i < 3 ? 3 - i : 0);
    }

    private static ArrayList<IUser> getUsers() {
        ArrayList<IUser> users = new ArrayList<>();
        int usersCount = 1 + rnd.nextInt(4);
        for (int i = 0; i < usersCount; i++) {
            users.add(getUser());
        }
        return users;
    }

    @NonNull
    private static IUser getUser() {
        return new DefaultUser(String.valueOf(UUID.randomUUID().getLeastSignificantBits()),
                names.get(rnd.nextInt(names.size())), avatars.get(rnd.nextInt(4)), rnd.nextBoolean());
    }
}
