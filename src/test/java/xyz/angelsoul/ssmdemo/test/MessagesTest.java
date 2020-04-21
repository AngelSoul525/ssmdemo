package xyz.angelsoul.ssmdemo.test;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.angelsoul.ssmdemo.dao.MessagerDao;
import xyz.angelsoul.ssmdemo.dao.MessagesDao;
import xyz.angelsoul.ssmdemo.model.Message;
import xyz.angelsoul.ssmdemo.model.Messager;
import xyz.angelsoul.ssmdemo.service.MessageService;
import xyz.angelsoul.ssmdemo.service.MessagerOperateService;
import xyz.angelsoul.ssmdemo.utils.CONSTANTS;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesTest {
    private ApplicationContext ac;
    private MessagesDao messagesDao;
    private MessageService messageService;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("spring-mvc.xml", "spring-mybatis.xml");
        messagesDao = ac.getBean("messagesDao", MessagesDao.class);
        messageService = ac.getBean("messageService", MessageService.class);
    }

    @Test
    public void test() {
        Date date = new Date();
        long myTime = date.getTime();
        System.out.println(myTime);
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void testSelectMessages() {
        Message message = new Message();
        String username = "zhangsan";
        Map<String, Object> para = new HashMap<>();
        para.put("lastMessageID", 0);
        para.put("messagesNum", CONSTANTS.MESSAGES_PER_PAGE);

        List<Map<String, Object>> res = messageService.queryMessages("0");
//                messagesDao.selectMessages(para);

//        System.out.println(res.get(0).get("messageID"));

        System.out.println(res);
    }

    @Test
    public void testSelectMessagesByService() {
        List<Map<String, Object>> res = messageService.queryMessagesByUsername("wangwu", "0");
        
        System.out.println(res);
    }

    @Test
    public void testSelectMessagesByUsername() {
        Message message = new Message();
        String username = "zhangsan";
        Map<String, Object> para = new HashMap<>();
        para.put("lastMessageID", 0);
        para.put("username", username);
        para.put("messagesNum", CONSTANTS.MESSAGES_PER_PAGE);

        List<Map<String, Object>> res = messagesDao.selectMessagesByUsername(para);

//        System.out.println(res.get(0).get("messageID"));

        System.out.println(res);
    }


    @Test
    public void testSaveMessage() {
        Date date = new Date();
        Message message = new Message();
//        messager.setUsername("zhangsan");
//        messager.setNickname("张三");
//        messager.setPassword("zhangsan123");
//        messager.setBorndate("1994-05-25");
        message.setMessageID(13);
        message.setUsername("Green001");
        message.setCreateTime(new Date().getTime());
        message.setMessageTitle("测试测试");
        message.setMessageContent("想去旅游啊");

        int res = messagesDao.saveMessage(message);
        System.out.println(res);
    }

    /*@Test
    public void testUpdateMessage() {
        Message message = new Message();
//        messager.setUsername("zhangsan");
//        messager.setNickname("张三");
//        messager.setPassword("zhangsan123");
//        messager.setBorndate("1994-05-25");
        message.setUsername("wangwu");
        message.setNickname("wangwu123");

        int res = messagesDao.updateMessagerInfo(message);
        System.out.println(res);
    }*/
}
