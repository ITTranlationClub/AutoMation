package github.ittranslation.automation.chatgpt.utils;/*
 * ClassName: JsonUtils
 * Description:
 * @Author: zjh
 * @Create: 2023/3/23
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        return gson.toJson(obj);
    }
}
