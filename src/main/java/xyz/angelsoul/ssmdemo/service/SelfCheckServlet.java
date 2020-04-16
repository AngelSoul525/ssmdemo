package xyz.angelsoul.ssmdemo.service;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import xyz.angelsoul.ssmdemo.dao.MessagerDao;
import xyz.angelsoul.ssmdemo.model.Messager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SelfCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CheckThread checkThread;

    public SelfCheckServlet() {
    }

    @Override
    public void init() {
        String str = null;
        if(str == null && checkThread == null) {
            checkThread = new CheckThread();
            checkThread.start();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        if(checkThread != null && checkThread.isInterrupted()) {
            checkThread.interrupt();
        }
    }
}

class CheckThread extends Thread {
    WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
    MessagerDao messagerDao = context.getBean("messagerDao", MessagerDao.class);

    @Override
    public void run() {
        super.run();
        while(!this.isInterrupted()) {
            try {
                Thread.sleep(3600000);//2 hour: 3600000ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Messager messager = messagerDao.selectMessager();
            System.out.println("check thread messager: " + messager);
            System.out.println("check thread time: " + System.currentTimeMillis());
        }
    }
}