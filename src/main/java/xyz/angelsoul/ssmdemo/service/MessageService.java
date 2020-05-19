package xyz.angelsoul.ssmdemo.service;

import xyz.angelsoul.ssmdemo.dao.MessagesDao;
import xyz.angelsoul.ssmdemo.model.Message;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public interface MessageService {
    /**
     * save new message
     * @param username username want to save message
     * @param messageInfo message info
     * @return success return message info else return null
     */
    Message saveMessage(String username, String messageInfo);

    /**
     * paging query messages
     * @param lastMessageID last message id
     * @return return messages
     */
    List<Map<String, Object>> queryMessages(String lastMessageID);

    /**
     * paging query messages by username
     * @param username username want to query message
     * @param lastMessageID if 0 query since the first message or query since lastMessageID
     * @return return messages
     */
    List<Map<String, Object>> queryMessagesByUsername(String username, String lastMessageID);

    /**
     * delete message
     * @param messageID the message ID want to delete
     * @return return if success delete
     */
    int deleteMessage(int messageID);
}
