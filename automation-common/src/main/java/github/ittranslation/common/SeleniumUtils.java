package github.ittranslation.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @program: clawerlesson
 * @description: 自动化脚本工具类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-15 18:43
 **/
public class SeleniumUtils {

    // 网页的防爬机制比较完善, 只能自动化脚本获取网页内容了
    static SeleniumUtils seleniumUtils;
    static WebDriver driver;

    private SeleniumUtils() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        System.setProperty("webdriver.chrome.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("debuggerAddress", "localhost:9527");
        // 设置指定端口, 避免重新登录 Cookie 丢失
        driver = new ChromeDriver(chromeOptions);
        // 实际访问不成功 猜测是超时问题

    }

    public static SeleniumUtils getInstance() {
        if(seleniumUtils == null) {
            seleniumUtils = new SeleniumUtils();
        }
        return seleniumUtils;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
