package xyz.angelsoul.ssmdemo.service;

import org.springframework.stereotype.Service;
import xyz.angelsoul.ssmdemo.dao.MessagesDao;
import xyz.angelsoul.ssmdemo.model.Message;
import xyz.angelsoul.ssmdemo.utils.CONSTANTS;

import javax.annotation.Resource;
import javax.management.remote.JMXServerErrorException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static sun.security.krb5.Confounder.intValue;

@Service("messageService")
public class MessageServiceImp implements MessageService {
    @Resource
    private MessagesDao messagesDao;

    @Override
    public Message saveMessage(String username, String messageInfo) {
        Map<String, Object> para = new HashMap<>();
        para.put("lastMessageID", 0);
        para.put("messagesNum", CONSTANTS.MESSAGES_PER_PAGE);

        List<Map<String, Object>> messages = messagesDao.selectMessages(para);

        Message newMessage = new Message();
        newMessage.setUsername(username);
        newMessage.setMessageTitle("new message");
        newMessage.setMessageContent(messageInfo);
        newMessage.setMessageID((Integer.parseInt(String.valueOf(messages.get(0).get("messageID")))) + 1);
        newMessage.setCreateTime(new Date().getTime());

        int res = messagesDao.saveMessage(newMessage);
        if(res > 0) {
            return newMessage;
        }
        System.out.println(res);

        return null;
    }

    @Override
    public List<Map<String, Object>> queryMessages(String lastMessageID) {
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("lastMessageID", lastMessageID);
        para.put("messagesNum", CONSTANTS.MESSAGES_PER_PAGE);
        
        System.out.println("[queryMessages] " + para);

        return messagesDao.selectMessages(para);
    }

    @Override
    public List<Map<String, Object>> queryMessagesByUsername(String username, String lastMessageID) {
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("lastMessageID", lastMessageID);
        para.put("username", username);
        para.put("messagesNum", CONSTANTS.MESSAGES_PER_PAGE);

        System.out.println("[queryMessagesByUsername] " + para);

        return messagesDao.selectMessagesByUsername(para);
    }

    @Override
    public int deleteMessage(int messageID) {
        Message message = new Message();

        message.setMessageID(messageID);
        message.setDeleteTime(new Date().getTime());
        message.setIsDelete(CONSTANTS.STATUS_DELETE);

        return messagesDao.updateMessage(message);
    }
}
