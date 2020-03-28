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
    public Message saveMessage(String username, String messageInfo);

    /**
     * paging query messages
     * @param lastMessageID last message id
     * @return return messages
     */
    public List<Map<String, Object>> queryMessages(String lastMessageID);

    /**
     * paging query messages by username
     */
    public List<Map<String, Object>> queryMessagesByUsername(String username, int pageNum);
}
