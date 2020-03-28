package xyz.angelsoul.ssmdemo.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.angelsoul.ssmdemo.dao.MessagerDao;
import xyz.angelsoul.ssmdemo.model.Messager;
import xyz.angelsoul.ssmdemo.service.MessagerOperateService;

import java.util.Date;

public class MessagerTest {
    private ApplicationContext ac;
    private MessagerDao messagerDao;
    private MessagerOperateService messagerOperateService;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("spring-mvc.xml", "spring-mybatis.xml");
        messagerDao = ac.getBean("messagerDao", MessagerDao.class);
        messagerOperateService = ac.getBean("messagerOperateService", MessagerOperateService.class);
    }

    @Test
    public void test() {
        Date date = new Date();
        long myTime = date.getTime();
        System.out.println(myTime);
        System.out.println(Integer.MAX_VALUE);
    }
    
    @Test
    public void testSaveMessager() {
        Messager messager = new Messager();
//        messager.setUsername("zhangsan");
//        messager.setNickname("张三");
//        messager.setPassword("zhangsan123");
//        messager.setBorndate("1994-05-25");
        messager.setUsername("lisi");
        messager.setNickname("李四");
        messager.setPassword("lisi123");
        messager.setBorndate("1993-07-28");
        messager.setCreateTime(new Date().getTime());

        int res = messagerDao.saveMessagerInfo(messager);
        System.out.println(res);
    }

    @Test
    public void testUpdateMessager() {
        Messager messager = new Messager();
//        messager.setUsername("zhangsan");
//        messager.setNickname("张三");
//        messager.setPassword("zhangsan123");
//        messager.setBorndate("1994-05-25");
        messager.setUsername("wangwu");
        messager.setNickname("wangwu123");

        int res = messagerDao.updateMessagerInfo(messager);
        System.out.println(res);
    }

    @Test
    public void testMessagerLogin() {
        Messager messager = new Messager();
        String username = "zhangsan";
        messager.setUsername(username);
        messager.setPassword("zhangsan123");

//        Object res = messagerOperateService.messagerLogin(messager, );

//        System.out.println(res);
    }
}
