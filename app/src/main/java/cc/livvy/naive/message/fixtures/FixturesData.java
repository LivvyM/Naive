package cc.livvy.naive.message.fixtures;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by Anton Bevza on 1/13/17.
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
    protected static final ArrayList<String> groupChatImages = new ArrayList<String>() {
        {
            add("http://i.imgur.com/hRShCT3.png");
            add("http://i.imgur.com/zgTUcL3.png");
            add("http://i.imgur.com/mRqh5w1.png");
        }
    };
    protected static final ArrayList<String> names = new ArrayList<String>() {
        {
            add("盖世小逗比");
            add("浮生未歇");
            add("懒猪");
            add("归去如风");
            add("沐瑾");
            add("人间四月芳菲尽");
            add("没了对象");
            add("南笙兮墨汐");
        }
    };
    protected static final ArrayList<String> groupChatTitles = new ArrayList<String>() {
        {
            add("盖世小逗比, 浮生未歇");
            add("归去如风, 没了对象, 盖世小逗比");
            add("南笙兮墨汐, 没了对象, 盖世小逗比, 懒猪");
        }
    };
    protected static final ArrayList<String> messages = new ArrayList<String>() {
        {
            add("Hello!");
            add("你总是猜测对方说的某句话的潜在意思是什么？");
            add("和高分、心仪异性沟通不了，却对和自身价值差不多甚至更低一些的人就没问题？");
            add("并且这种感觉让人百爪挠心，纠结无比，此心态会直接影响人的行为模式，致使本来一个简单事情会变复杂或低级失误或是在追女孩的过程中让自己处在被动局面。");
            add("人为什么有时候会有患得患失的感觉？");
            add("我们每个人的大脑里都有两个“我”，其中一个“我”就是对于各种自身行为的好坏下判断而产生情绪的“我”，在这里称之为“我1”，另外一个“我”是行动的无意识的“我”，在这里称之为“我2”。");
            add("比如你成绩不是很好，害怕年终考试挂课。比如，现在就业压力很大，你害怕毕业后就失业。比如你父母得了重病，你害怕失去他们。");
            add("假设你平常就是个严肃、冷酷的人，不会搞笑幽默，不会根据现场情况活跃气氛的人，虽然不一定有幽默感才能赢得朋友或异性欢心。但你却是少了一个有力的社交武器。幽默感有时候就像润滑剂、催化剂、助燃剂。能有效快速拉近你与他人的关系、能让你与他人的关系更亲密，也更容易让他人喜欢你。");
        }
    };
    public static SecureRandom rnd = new SecureRandom();

}
