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

        BufferedReader in = null;

        try {
            URL httpUrl = new URL("https://api.pubg.com/shards/$platform/players?filter[playerNames]=lucklot001");
            HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(8000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.connect();

            StringBuilder result = new StringBuilder();
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            if (in != null) {
                in.close();
            }
            System.out.println(result.toString());
            return jsonResult.success(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult.failed("");
        }
    }
}
