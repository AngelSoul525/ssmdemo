package xyz.angelsoul.ssmdemo.model;

import java.util.Objects;

public class Messager {
    private int userID;
    private String username;
    private String nickname;
    private String password;
    private String borndate;
    private String email;
    private String phone;
    private long createTime;
    private long deleteTime;
    private int isdelete;

    public Messager() {
    }

    public Messager(int userID, String username, String nickname, String password, String borndate, String email, String phone, long createTime, long deleteTime, int isdelete) {
        this.userID = userID;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.borndate = borndate;
        this.email = email;
        this.phone = phone;
        this.createTime = createTime;
        this.deleteTime = deleteTime;
        this.isdelete = isdelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Messager messager = (Messager) o;
        return createTime == messager.createTime &&
                username.equals(messager.username);
    }

    @Override
    public String toString() {
        return "Messager{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", borndate='" + borndate + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", deleteTime=" + deleteTime +
                ", isdelete=" + isdelete +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, createTime);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBorndate() {
        return borndate;
    }

    public void setBorndate(String borndate) {
        this.borndate = borndate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }
}
