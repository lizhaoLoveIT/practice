package cn.lizhaoloveit.springmvc.handlers;

import cn.lizhaoloveit.springmvc.annotation.Controller;
import cn.lizhaoloveit.springmvc.annotation.RequestMapping;
import cn.lizhaoloveit.springmvc.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * DESCRIPTION:
 * Author: ammar
 * Date:   2020-06-13
 * Time:   16:17
 */
@Controller
public class UserController {
    @RequestMapping(value = "/query")
    @ResponseBody
    public Map<String, Object> query(Integer id, String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        return map;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(Integer id) {
        return "OK";
    }
}
