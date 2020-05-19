package xyz.angelsoul.ssmdemo.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.angelsoul.ssmdemo.model.Message;
import xyz.angelsoul.ssmdemo.service.MessageService;
import xyz.angelsoul.ssmdemo.utils.CONSTANTS;
import xyz.angelsoul.ssmdemo.utils.JsonResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static xyz.angelsoul.ssmdemo.utils.CONSTANTS.SUCCESS_DB_OPERATE;

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
    public JsonResult<Object> queryMessage(String username, String lastMessageID) {
        if(lastMessageID == null || lastMessageID.equals("")) {
            throw new RuntimeException("服务器异常，请重试");
        }
        List<Map<String, Object>> res;
        System.out.println("queryMessage " + username + " " + lastMessageID);

        if(username.equals(CONSTANTS.HOME_REQUEST_USERNAME)) {
            res = messageService.queryMessages(lastMessageID);
        } else {
            res = messageService.queryMessagesByUsername(username, lastMessageID);
        }
        if(res != null) {
            System.out.println("[queryMessage] " + lastMessageID + " " + res);
            return new JsonResult<Object>(res);
        }

        return new JsonResult<>(new RuntimeException("当前没有留言"));
    }

    /**
     * delete message
     * @param messageID the id want to delete
     * @return if delete success
     */
    @RequestMapping("/deleteMessage")
    @ResponseBody
    public JsonResult<Object> deleteMessage(int messageID) {
        if(messageID == 0) {
            throw new RuntimeException("服务器繁忙，请稍后重试");
        }

        System.out.println("deleteMessage " + messageID);
        int result = messageService.deleteMessage(messageID);

        if(result == SUCCESS_DB_OPERATE) {
            System.out.println("deleteMessage " + result);
            return new JsonResult<Object>(result);
        }

        return new JsonResult<>(new RuntimeException("删除失败，请稍后重试"));
    }
}
