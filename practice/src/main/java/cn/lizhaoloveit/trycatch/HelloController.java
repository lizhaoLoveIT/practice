package cn.lizhaoloveit.trycatch;

import cn.lizhaoloveit.base.JsonResult;
import cn.lizhaoloveit.base.exception.AMError;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-06-06
 * Time:   22:22
 */
@Controller
public class HelloController {

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password) {
        JsonResult jsonResult = new JsonResult();
//        AMError.ARGS_NOT_NULL.assertNotNull(username, password);
        String string = "Red,Green,Green";
        string = string.replace("Green","Blue");
        System.out.println(string + "---------");
        string = string.replaceAll("Green","Blue");
        System.out.println(string);

        return jsonResult;
    }

}
