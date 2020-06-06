package cn.lizhaoloveit.trycatch;

import cn.lizhaoloveit.base.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-06-06
 * Time:   22:22
 */
@Controller
public class HelloController {
    
    @RequestMapping("/login")
    public JsonResult login() {
        JsonResult jsonResult = new JsonResult();
        try { 
            
            return jsonResult.success();
        } catch (Exception e) {
            return jsonResult.failed(e.getMessage());
        }
    }
    
}
