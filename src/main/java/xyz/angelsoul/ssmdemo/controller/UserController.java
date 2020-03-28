package xyz.angelsoul.ssmdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.angelsoul.ssmdemo.model.Messager;
import xyz.angelsoul.ssmdemo.service.MessagerOperateService;
import xyz.angelsoul.ssmdemo.utils.JsonResult;
import xyz.angelsoul.ssmdemo.utils.RETURN_CODE;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class UserController extends AbstractController {
    @Resource
    private MessagerOperateService messagerOperateService;

    /**
     * query username request handle
     */
    @RequestMapping("/queryUsername")
    @ResponseBody
    public JsonResult<Object> queryUsername(String username) {
        String queryResult;
        System.out.println("[queryUsername] " + username);

        queryResult = messagerOperateService.queryUsername(username);
        if(queryResult != null) {
            return new JsonResult<>(new RuntimeException("用户名已存在"));
        }

        return new JsonResult<>();
    }

    /**
     * query nickname request handle
     */
    @RequestMapping("/queryNickname")
    @ResponseBody
    public JsonResult<Object> queryNickname(String nickname) {
        String queryResult;
        System.out.println("[queryNickname] " + nickname);

        queryResult = messagerOperateService.queryNickname(nickname);
        if(queryResult != null) {
            return new JsonResult<>(new RuntimeException("昵称已存在"));
        }

        return new JsonResult<>();
    }

    /**
     * register request handle
     */
    @RequestMapping("/register")
    @ResponseBody
    public JsonResult<Object> register(Messager registerMessager) {
        System.out.println(registerMessager.toString());
        registerMessager = messagerOperateService.saveMessager(registerMessager);
        if(registerMessager == null) {
            return new JsonResult<>(new RuntimeException("注册失败"));
        }

        return new JsonResult<Object>(registerMessager);
    }

    /**
     * login request handle
     */
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult<Object> login(Messager loginMessager, HttpServletResponse response) {
        System.out.println("[login] " + loginMessager.toString());
        Object res = messagerOperateService.messagerLogin(loginMessager, response);
        if(res == null) {
            return new JsonResult<>(new RuntimeException("用户名有误，请重新输入！"));
        }
        if(res instanceof Integer) {
            if((int)res == RETURN_CODE.ERROR_PASSWORD) {
                return new JsonResult<>(new RuntimeException("用户名或密码错误，请重新输入！"));
            }
//            switch ((Integer) res)
//            {
//                case RETURN_CODE.ERROR_PASSWORD:
//
//                    break;
//
//                default:
//                    break;
//            }
        }
        if(res instanceof Messager) {
            return new JsonResult<>(res);
        }

        return new JsonResult<>(new RuntimeException("未知错误！"));
    }

    /**
     * get messager information by username request handle
     */
    @RequestMapping("/getMessagerInfoByUsername")
    @ResponseBody
    public JsonResult<Object> getMessagerInfoByUsername(String username) {
        System.out.println("[getMessagerInfoByUsername] " + username);
        Messager res = messagerOperateService.getMessagerInfoByUsername(username);
        if(res == null) {
            return new JsonResult<>(new RuntimeException("信息查询错误"));
        }
        return new JsonResult<Object>(res);

    }

    /**
     * update messager information request handle
     */
    @RequestMapping("/requestModifyMessagerInfo")
    @ResponseBody
    public JsonResult<Object> requestModifyMessagerInfo(Messager messager) {
        System.out.println("[requestModifyMessagerInfo] " + messager.toString());
        Messager res = messagerOperateService.requestModifyMessagerInfo(messager);
        if(res == null) {
            return new JsonResult<>(new RuntimeException("服务器较忙，请稍后再试"));
        }
        return new JsonResult<Object>(res);

    }

    /**
     * update messager password request handle
     */
    @RequestMapping("/requestModifyPassword")
    @ResponseBody
    public JsonResult<Object> requestModifyPassword(String username, String originalPassword, String newPassword) {
        System.out.println("[requestModifyMessagerInfo] " + username + " " + originalPassword + " " + newPassword);
        int res = messagerOperateService.requestModifyPassword(username, originalPassword, newPassword);
        if(res == -1) {
            return new JsonResult<>(new RuntimeException("服务器较忙，请稍后再试"));
        }
        if(res == 0) {
            return new JsonResult<Object>(res);
        }

        return new JsonResult<>(new RuntimeException("未知错误！"));
    }
}
