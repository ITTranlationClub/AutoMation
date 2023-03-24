package github.ittranslation.automation.chatgpt.common;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

import java.io.IOException;
import java.util.List;

/**
 * @program: clawerlesson
 * @description: 根据需要创建 WebClient 对象, 作为获取网页的凭证
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-03 00:07
 **/
public class MyWebClient {

    // 网页访问凭证
    List<List<String>> cookieList;

    WebClient webClient;

    public MyWebClient() {
        this.cookieList = cookieList;
        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);//不启用ActiveX
        webClient.getOptions().setDownloadImages(false);//不下载图片
        // 设置webClient的相关参数
        webClient.setCssErrorHandler(new SilentCssErrorHandler());
        //设置ajax
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        //设置支持js
        webClient.getOptions().setJavaScriptEnabled(true);
        //CSS渲染禁止
        webClient.getOptions().setCssEnabled(false);
        //设置忽略证书
        webClient.getOptions().setUseInsecureSSL(true);
        //超时时间
        webClient.getOptions().setTimeout(50000);
        //设置js抛出异常:false
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //允许重定向
        webClient.getOptions().setRedirectEnabled(true);
        //允许cookie
        webClient.getCookieManager().setCookiesEnabled(true);
    }

    public MyWebClient(String domain, List<List<String>> cookieList) {
        this();
        for(List<String> cookieKV:cookieList) {
            if(cookieKV.size()<2)
                continue;
            Cookie cookie = new Cookie(domain, cookieKV.get(0), cookieKV.get(1));
            webClient.getCookieManager().addCookie(cookie);
        }
    }

    public MyWebClient(List<List<String>> cookieList) {
        this();
        for(List<String> buffList:cookieList) {
            // 只处理三个值的 cookie
            if(buffList.size()<3)
                continue;
            Cookie cookie = new Cookie(buffList.get(0),
                    buffList.get(1), buffList.get(2));
            webClient.getCookieManager().addCookie(cookie);
        }
    }

    public HtmlPage getPageByUrl(String url) throws IOException {
        return webClient.getPage(url);
    }
}
