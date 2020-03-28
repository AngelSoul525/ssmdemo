package xyz.angelsoul.ssmdemo.model;

import java.util.Objects;

/**
 * messageID start since 0 000 000 000 to 0 999 999 999
 */

public class Message {
    private int messageID;
    private String username;
    private long createTime;
    private long deleteTime;
    private String messageTitle;
    private String messageContent;
    private int starNum;
    private int isDelete;

    public Message() {
    }

    public Message(int messageID, String username, long createTime, long deleteTime, String messageTitle, String messageContent, int starNum, int isDelete) {
        this.messageID = messageID;
        this.username = username;
        this.createTime = createTime;
        this.deleteTime = deleteTime;
        this.messageTitle = messageTitle;
        this.messageContent = messageContent;
        this.starNum = starNum;
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageID=" + messageID +
                ", username='" + username + '\'' +
                ", createTime=" + createTime +
                ", deleteTime=" + deleteTime +
                ", messageTitle='" + messageTitle + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", starNum=" + starNum +
                ", isDelete=" + isDelete +
                '}' +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageID == message.messageID &&
                createTime == message.createTime &&
                username.equals(message.username) &&
                messageTitle.equals(message.messageTitle) &&
                messageContent.equals(message.messageContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageID, username, createTime, messageTitle, messageContent);
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
