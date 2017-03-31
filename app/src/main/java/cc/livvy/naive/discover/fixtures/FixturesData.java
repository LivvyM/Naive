package cc.livvy.naive.discover.fixtures;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by livvy on 17-3-31.
 */

public class FixturesData {

    protected static ArrayList<String> avatars = new ArrayList<String>() {
        {
            add("http://img2.100bt.com/upload/ttq/20131103/1383470553132_middle.jpg");
            add("http://img3.imgtn.bdimg.com/it/u=292316087,2082810268&fm=11&gp=0.jpg");
            add("http://diy.qqjay.com/u2/2012/0913/55a80cdb4fca56c77c12a38c079bf6ab.jpg");
            add("http://diy.qqjay.com/u2/2015/0406/226d3624f8cfd569df104887814238d8.jpg");
            add("http://img.52touxiang.net/uploads/allimg/141023/152112G55-9.jpg");
            add("http://img.52touxiang.net/uploads/allimg/161130/19133aG2-10.jpg");
            add("http://img.52touxiang.net/uploads/allimg/161130/19133a2B-18.jpg");
        }
    };

    protected static ArrayList<String> names = new ArrayList<String>() {
        {
            add("Joseph Addison");
            add("Benjamin Franklin");
            add("Louis Pasteur");
            add("Voltaire");
            add("William");
            add("Stevenson");
            add("Burroughs");
            add("Thomas");
        }
    };

    protected static ArrayList<String> titles = new ArrayList<String>() {
        {
            add("You Have Only One Life");
            add("Joseph Addison");
            add("Benjamin Franklin");
            add("Thomas Edison");
            add("Samuel Johnson");
            add("Henry David Thoreau");
        }
    };

    protected static ArrayList<String> messages = new ArrayList<String>() {
        {
            add("There are moments in life when you miss someone so much that you just want to pick them from your dreams and hug them for real! Dream what you want to dream;go where you want to go;be what you want to be,because you have only one life and one chance to do all the things you want to do.");
            add("May you have enough happiness to make you sweet,enough trials to make you strong,enough sorrow to keep you human,enough hope to make you happy? Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too.");
            add("The happiest of people don’t necessarily have the best of everything;they just make the most of everything that comes along their way.Happiness lies for those who cry,those who hurt, those who have searched,and those who have tried,for only they can appreciate the importance of people");
            add("When you were born,you were crying and everyone around you was smiling.Live your life so that when you die,you're the one who is smiling and everyone around you is crying.");
            add("A contented mind is the greatest blessing a man can enjoy in this world.");
            add("Sometimes one pays most for the things one gets for nothing.");
        }
    };

    protected static ArrayList<String> pictures = new ArrayList<String>() {
        {
            add("http://img.hb.aicdn.com/6defcdafc22a787804cece3099a02391b868d7dbeaca4-bzbJK4");
            add("http://img.hb.aicdn.com/b7e146010b9c0f7b27ce5b69ec30eb77bbdab8e6db0a-s1DvPP");
            add("http://img.hb.aicdn.com/e4569ac5fbc73ed7fe788d615cba6e954a37e82738b6f-Qv4k6h");
            add("http://img.hb.aicdn.com/814552d415f0569e345303fc81a125404185c9483bc09-a7ZSnx");
            add("http://img.hb.aicdn.com/5279016e47913acaffad4d4678f68d1531b1687111e52-Hhgg3n");
        }
    };
    public static SecureRandom rnd = new SecureRandom();
}
