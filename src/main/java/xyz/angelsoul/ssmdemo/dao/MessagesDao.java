package xyz.angelsoul.ssmdemo.dao;

import org.springframework.stereotype.Repository;
import xyz.angelsoul.ssmdemo.model.Message;
import xyz.angelsoul.ssmdemo.model.Messager;

import javax.swing.*;
import java.util.List;
import java.util.Map;

@Repository
public interface MessagesDao {
    /**
     * save message
     */
    int saveMessage(Message message);

    /**
     * update message information
     * @param message include information need to update
     * @return if update success
     */
    int updateMessage(Message message);

    /**
     * select message information by messageID
     */
    Message selectMessageByID(String messageID);

    /**
     * paging select messages information
     */
    List<Map<String, Object>> selectMessages(Map<String, Object> para);

    /**
     * paging select messages information by username
     */
    List<Map<String, Object>> selectMessagesByUsername(Map<String, Object> para);
}
