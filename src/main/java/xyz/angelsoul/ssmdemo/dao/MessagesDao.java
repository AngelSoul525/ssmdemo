package xyz.angelsoul.ssmdemo.dao;

import org.springframework.stereotype.Repository;
import xyz.angelsoul.ssmdemo.model.Message;
import xyz.angelsoul.ssmdemo.model.Messager;

import java.util.List;
import java.util.Map;

@Repository
public interface MessagesDao {
    /**
     * save message
     */
    public int saveMessage(Message message);

    /**
     * select message information by messageID
     */
    public Message selectMessageByID(String messageID);

    /**
     * paging select messages information
     */
    public List<Map<String, Object>> selectMessages(Map<String, Object> para);

    /**
     * paging select messages information by username
     */
    public List<Map<String, Object>> selectMessagesByUsername(Map<String, Object> para);
}
