package com.pratice.shopcar.pojo;

public class Comment extends BasePojo{
    private static final long serialVersionUID = 4724798182207191490L;
    Integer id;
    Integer uid;
    Integer gid;
    String content;
    String username;
    String avatar;

    public Comment() {
    }

    public Comment(Integer uid, Integer gid, String content, String username, String avatar) {
        this.uid = uid;
        this.gid = gid;
        this.content = content;
        this.username = username;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", uid=" + uid +
                ", gid=" + gid +
                ", content='" + content + '\'' +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
