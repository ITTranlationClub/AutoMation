package github.ittranslation.automation.chatgpt.utils;/*
 * ClassName: ChatGPTAPI
 * Description:
 * @Author: zjh
 * @Create: 2023/3/23
 */

import cn.hutool.core.convert.ConvertException;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
//@Data
//@Service
public class ChatGPTAPI {
    /**
     * 聊天端点 国内免费调用ChatGPT站点，不需要开梯子，请求稍微慢一点，请耐心等待哦
     */
//    String chatEndpoint = "http://nginx.web-framework-1qoh.1045995386668294.us-west-1.fc.devsapp.net/v1/chat/completions";
    //    String chatEndpoint = "https://api.openai.com/v1/chat/completions";
    String chatEndpoint = PathConstant.OPENAI_URL + "v1/chat/completions";


    /**
     * api密匙
     */
//    String apiKey = "Bearer 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";
    String apiKey = "Bearer " + PathConstant.API_KEY;

    /**
     * 发送消息
     *
     * @param txt 内容
     * @return {@link String}
     */
    public String chat(String txt) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model", "gpt-3.5-turbo");
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>() {{
            put("role", "user");
            put("content", txt);
        }});
        paramMap.put("messages", dataList);
        JSONObject message = null;
        try {
            String body = HttpRequest.post(chatEndpoint)
                    .header("Authorization", apiKey)
                    .header("Content-Type", "application/json")
                    .body(JsonUtils.toJson(paramMap))
                    .execute()
                    .body();
            JSONObject jsonObject = JSONUtil.parseObj(body);
            JSONArray choices = jsonObject.getJSONArray("choices");
            JSONObject result = choices.get(0, JSONObject.class, Boolean.TRUE);
            message = result.getJSONObject("message");
        } catch (HttpException e) {
            return "出现问题";
        } catch (ConvertException e) {
            return "出现问题";
        }
        return message.getStr("content");
    }
}
