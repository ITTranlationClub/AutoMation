package github.ittranslation.automation.chatgpt.utils;/*
 * ClassName: PathConstant
 * Description:
 * @Author: zjh
 * @Create: 2023/3/24
 */

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PathConstant implements InitializingBean {
    @Value("${open.ai.url}")
    private String url;

    @Value("${open.ai.key}")
    private String key;

    public static String OPENAI_URL;

    public static String API_KEY;

    @Override
    public void afterPropertiesSet() throws Exception {
        OPENAI_URL = url;
        API_KEY = key;
    }
}
