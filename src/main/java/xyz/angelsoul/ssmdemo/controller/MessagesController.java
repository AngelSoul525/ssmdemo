package xyz.angelsoul.ssmdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.angelsoul.ssmdemo.model.Message;
import xyz.angelsoul.ssmdemo.service.MessageService;
import xyz.angelsoul.ssmdemo.utils.JsonResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("message")
public class MessagesController extends AbstractController {
    @Resource
    private MessageService messageService;

    /**
     * publish new message
     * @param username username want to publish message
     * @param messageInfo message info
     * @return return response body
     */
    @RequestMapping("/publishMessage")
    @ResponseBody
    public JsonResult<Object> publishMessage(String username, String messageInfo) {
        if(null == username || "".equals(username)) {
            return new JsonResult<>(new RuntimeException("用户不存在"));
        }
        if(null == messageInfo || "".equals(messageInfo)) {
            return new JsonResult<>(new RuntimeException("留言内容为空"));
        }

        Message message = messageService.saveMessage(username, messageInfo);
        if(null != message) {
            return new JsonResult<Object>(message);
        }

        return new JsonResult<>(new RuntimeException("服务器繁忙，请稍后再试"));
    }

    /**
     * paging query messages
     * @param lastMessageID last message ID
     * @return return response body
     */
    @RequestMapping("/queryMessage")
    @ResponseBody
    public JsonResult<Object> queryMessage(String lastMessageID) {
        if(lastMessageID == null || lastMessageID.equals("")) {
            throw new RuntimeException("服务器异常，请重试");
        }

        List<Map<String, Object>> res = messageService.queryMessages(lastMessageID);
        if(res != null) {
            System.out.println("[queryMessage] " + lastMessageID + " " + res);
            return new JsonResult<Object>(res);
        }

        return new JsonResult<>(new RuntimeException("当前没有留言"));
    }

    /**
     * query messages by username
     * @param username query messages by username
     * @return return response body
     */
    @RequestMapping("/queryMessageByUsername")
    @ResponseBody
    public JsonResult<Object> queryMessageByUsername(String username) {
        if(username == null) {
            throw new RuntimeException("用户不存在");
        }

        return null;
    }
}
