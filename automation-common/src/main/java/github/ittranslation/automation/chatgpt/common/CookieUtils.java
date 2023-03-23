package github.ittranslation.common;

import cn.hutool.json.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: clawerlesson
 * @description: 解析 Cookie 工具
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-13 23:32
 **/
public class CookieUtils {
    static Pattern COOKIE_PATTERN = Pattern.compile("^.*?(?=\\=)");

    /**
     * 从 cookie 字符串中解析得到 cookie
     * @param cookieStr ';'分割的 cookie 串, 解析后组合成正确的 cookie 信息
     * @return cookie信息组成的数组
     */
    public static List<List<String>> creatCookieFromStr(String cookieStr) {
        //';'分割后, 根据'='再次分割即可
        String[] cookies = cookieStr.split(";");
        List<List<String>> result = new ArrayList<>();

        for(String cookie:cookies) {
            String pattern = "^.*?(?=\\=)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(cookie);
            if(!m.find())
                continue;
            String key = m.group(0);
            String value = cookie.substring(key.length()+1);
            List<String> buffList = new ArrayList<>();
            buffList.add(key);
            buffList.add(value);
            result.add(buffList);
        }
        return result;
    }

}
