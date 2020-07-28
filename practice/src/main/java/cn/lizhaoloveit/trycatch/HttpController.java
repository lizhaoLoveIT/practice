package cn.lizhaoloveit.trycatch;

import cn.lizhaoloveit.base.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * DESCRIPTION:
 * Author: ammar
 * Date:   2020-06-29
 * Time:   12:20
 */
@Controller
public class HttpController {

    @RequestMapping("/http")
    @ResponseBody
    public JsonResult login(String username, String password) throws Exception {
        JsonResult jsonResult = new JsonResult();

        return jsonResult;
    }
}
