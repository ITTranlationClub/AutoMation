package github.ittranslation.automation.chatgpt;/*
 * ClassName: AutomationChatgptApplicationTests
 * Description:
 * @Author: zjh
 * @Create: 2023/3/23
 */

import github.ittranslation.automation.chatgpt.utils.ChatGPTAPI;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutomationChatgptApplicationTests.class)
public class AutomationChatgptApplicationTests {

//    @Autowired
////    @Resource
//    private ChatGPTAPI chatGPTAPI;

    @Test
    public void chatgptTest() {
        System.out.println(ChatGPTAPI.chat("Please translate the following into Chinese: By clicking “Accept all cookies”, you agree Stack Exchange can store cookies on your device and disclose information in accordance with our Cookie Policy"));
    }
}
