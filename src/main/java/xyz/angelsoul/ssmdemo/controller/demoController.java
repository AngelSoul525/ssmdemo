package xyz.angelsoul.ssmdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.angelsoul.ssmdemo.utils.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("demo")
public class demoController {

    @RequestMapping("/hello")
    @ResponseBody
    public JsonResult<Object> hello(HttpServletRequest request, HttpServletResponse reponse) {
        System.out.println(request.getRequestURI());

        return new JsonResult<Object>("Hello World!!");
    }
}
