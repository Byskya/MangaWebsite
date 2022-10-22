package com.pratice.shopcar.pojo;

public class User extends BasePojo{
    private static final long serialVersionUID = -3726873812831273447L;
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private Integer gender;
    private String phone;
    private String email;
    private String avatar;
    private Integer isDelete;
    public User() {
    }
    public User(Integer uid, String username, String password, String salt, Integer gender, String phone, String email, String avatar, Integer isDelete) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
        this.isDelete = isDelete;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
