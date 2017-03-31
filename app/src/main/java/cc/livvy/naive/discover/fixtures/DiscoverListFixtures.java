package cc.livvy.naive.discover.fixtures;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;

import cc.livvy.naive.discover.models.DiscoverModel;

import static cc.livvy.naive.discover.fixtures.FixturesData.avatars;
import static cc.livvy.naive.discover.fixtures.FixturesData.messages;
import static cc.livvy.naive.discover.fixtures.FixturesData.names;
import static cc.livvy.naive.discover.fixtures.FixturesData.pictures;
import static cc.livvy.naive.discover.fixtures.FixturesData.titles;

/**
 * Created by livvy on 17-3-31.
 */

public class DiscoverListFixtures {

    public static ArrayList<DiscoverModel> getDiscoverList(){
        ArrayList<DiscoverModel> discoverModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            discoverModels.add(getDiscover(i));
        }
        return discoverModels;
    }


    private static DiscoverModel getDiscover(int i) {
        return new DiscoverModel(avatars.get(new SecureRandom().nextInt(7)),
                names.get(new SecureRandom().nextInt(7)),
                Calendar.getInstance().getTime().toString(),
                pictures.get(new SecureRandom().nextInt(4)),
                titles.get(new SecureRandom().nextInt(5)),
                messages.get(new SecureRandom().nextInt(5)));
    }
}

