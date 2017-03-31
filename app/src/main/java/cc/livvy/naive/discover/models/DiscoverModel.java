package cc.livvy.naive.discover.models;

/**
 * Created by livvy on 17-3-31.
 */

public class DiscoverModel {

    private String avatar;
    private String name;
    private String time;
    private String photo;
    private String contentTitle;
    private String content;

    public DiscoverModel(String avatar,String name,String time,String photo,String contentTitle,String content){
        this.avatar = avatar;
        this.name = name;
        this.time = time;
        this.photo = photo;
        this.content = content;
        this.contentTitle = contentTitle;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
