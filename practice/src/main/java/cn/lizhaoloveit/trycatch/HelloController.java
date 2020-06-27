package cn.lizhaoloveit.trycatch;

import cn.hutool.core.bean.BeanUtil;
import cn.lizhaoloveit.base.JsonResult;
import cn.lizhaoloveit.base.exception.AMError;
import cn.lizhaoloveit.domain.Person;
import cn.lizhaoloveit.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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

        Person person = new Person();
        User user = new User();
        user.setPerson1(person);

        Map<String, Object> map = new HashMap<>();
        map.put("person", person);

        BeanUtil.beanToMap(user, map, false, true);

        System.out.println(map);




        AMError.ARGS_NOT_NULL.assertNotNull(person);



        return jsonResult;
    }

}
