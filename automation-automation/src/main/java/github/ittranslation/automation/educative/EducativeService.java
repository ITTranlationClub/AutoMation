package github.ittranslation.automation.educative;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import github.ittranslation.common.CookieUtils;
import github.ittranslation.common.FileUtils;
import github.ittranslation.common.MyWebClient;
import github.ittranslation.common.vo.CourseListVo;
import github.ittranslation.common.vo.categories.Category;
import github.ittranslation.common.vo.categories.ContentItem;
import github.ittranslation.common.vo.categories.PageItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: automation
 * @description: educative 爬虫业务逻辑实现类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-22 12:15
 **/
public class EducativeService {

    static String INSTANCE = "instance";
    static String DETAILS = "details";
    static String TITLE = "title";
    static String SUMMARY = "summary";
    static String BRIEF_SUMMARY = "brief_summary";
    static String CLOS = "clos";
    static String TOC = "toc";
    static String CATEGORIES = "categories";
    static String ID = "id";
    static String PAGES = "pages";
    static String TYPE = "type";
    static String AUTHOR_ID = "author_id";
    static String COLLECTION_ID = "collection_id";
    static String PAGE_ID = "page_id";
    static String MARKDOWN_EDITOR_TYPE = "MarkdownEditor";
    static String LAZY_LOAD_PLACE_HOLDER = "LazyLoadPlaceholder";
    static String STRUCTURED_QUIZ = "StructuredQuiz";
    static String COMPONENTS = "components";
    static String SLATE_HTML_TYPE = "SlateHTML";
    static String TITLE_MD_HTML = "titleMdHtml";
    static String DESCRIPTION = "description";
    static String CONTENT = "content";
    static String TEXT = "text";
    static String MD_HTML = "mdHtml";
    static String QUESTIONS = "questions";
    static String QUESTION_TEXT = "questionText";
    static String QUESTION_TEXT_HTML = "questionTextHtml";
    static String ANSWER_TEXT = "answerText";
    static String ANSWER_TEXT_HTML = "answerTextHtml";
    static String EDIT_MODE = "edit";
    static String SYSTEM_SPILT = System.lineSeparator();


    /**
     * 根据目录结构信息, 构造详细页面结构
     * @param courseListStr
     * @return
     */
    public CourseListVo getCourseListFromStr(String courseListStr) {
        JSONObject courseJson = JSONUtil.parseObj(courseListStr);
        JSONObject detailsJson = courseJson.getJSONObject(INSTANCE).getJSONObject(DETAILS);
        CourseListVo courseListVo = new CourseListVo();
        courseListVo.setTitle(detailsJson.getStr(TITLE));
        courseListVo.setSummary(detailsJson.getStr(SUMMARY));
        courseListVo.setBriefSummary(detailsJson.getStr(BRIEF_SUMMARY));
        courseListVo.setDetails(detailsJson.getStr(DETAILS));
        courseListVo.setClos(detailsJson.getBeanList(CLOS, String.class));
        List<Category> categoryList = new ArrayList<>();
        courseListVo.setCategories(categoryList);
        // 填充章节信息
        JSONArray categoryArray = detailsJson.getJSONObject(TOC).getJSONArray(CATEGORIES);
        for(JSONObject categoryJson: categoryArray.jsonIter()) {
            Category category = new Category();
            category.setId(categoryJson.getStr(ID));
            category.setTitle(categoryJson.getStr(TITLE));
            category.setType(categoryJson.getStr(TYPE));
            List<PageItem> pageItemList = new ArrayList<>();
            category.setPageItems(pageItemList);
            categoryList.add(category);

            JSONArray pageArray = categoryJson.getJSONArray(PAGES);
            for(JSONObject pageObject:pageArray.jsonIter()) {
                PageItem pageItem = new PageItem();
                pageItem.setId(pageObject.getStr(ID));
                pageItem.setTitle(pageObject.getStr(TITLE));
                pageItem.setType(pageObject.getStr(TYPE));
                pageItem.setAuthorId(pageObject.getStr(AUTHOR_ID));
                pageItem.setCollectionId(pageObject.getStr(COLLECTION_ID));
                pageItem.setPageId(pageObject.getStr(PAGE_ID));
                pageItemList.add(pageItem);
            }
        }
        return courseListVo;
    }

    public static ContentItem parseMarkdownJson(String markdownStr) {
        JSONObject markJson = JSONUtil.parseObj(markdownStr);
        JSONObject summaryJson = markJson.getJSONObject(SUMMARY);
        JSONArray componentArray = markJson.getJSONArray(COMPONENTS);

        // 分别识别 Markdown 文本 与 html 文本, 写入不同结果集中保存
        ContentItem infoVo = new ContentItem();
        infoVo.setTitle(summaryJson.getStr(TITLE));
        infoVo.setDescription(summaryJson.getStr(DESCRIPTION));

        StringBuilder markdownBuilder = new StringBuilder();
        StringBuilder htmlBuilder = new StringBuilder();

        for(JSONObject jsonObject:componentArray.jsonIter()) {
            String componentType = jsonObject.getStr("type");
            if(MARKDOWN_EDITOR_TYPE.equals(componentType)) {
                if(!jsonObject.getJSONObject(CONTENT).containsKey(TEXT))
                    continue;
                markdownBuilder.append(jsonObject.getJSONObject(CONTENT).getStr(TEXT));
                markdownBuilder.append(SYSTEM_SPILT);

                htmlBuilder.append(jsonObject.getJSONObject(CONTENT).getStr(MD_HTML));
                htmlBuilder.append(SYSTEM_SPILT);
            } else if(LAZY_LOAD_PLACE_HOLDER.equals(componentType)) {
                continue;
            } else if(STRUCTURED_QUIZ.equals(componentType)) {
                JSONObject titleJSON = jsonObject.getJSONObject(CONTENT);
                if(!titleJSON.containsKey(TITLE))
                    continue;
                markdownBuilder.append(titleJSON.getStr(TITLE));
                markdownBuilder.append(SYSTEM_SPILT);

                htmlBuilder.append(titleJSON.getStr(TITLE_MD_HTML));
                htmlBuilder.append(SYSTEM_SPILT);

                JSONArray questionArray =  titleJSON.getJSONArray(QUESTIONS);

                for(JSONObject questionObj:questionArray.jsonIter()) {
                    markdownBuilder.append(questionObj.getStr(QUESTION_TEXT));
                    markdownBuilder.append(SYSTEM_SPILT);
                    markdownBuilder.append(questionObj.getStr(ANSWER_TEXT));
                    markdownBuilder.append(SYSTEM_SPILT);

                    htmlBuilder.append(questionObj.getStr(QUESTION_TEXT_HTML));
                    htmlBuilder.append(SYSTEM_SPILT);
                    htmlBuilder.append(questionObj.getStr(ANSWER_TEXT_HTML));
                    htmlBuilder.append(SYSTEM_SPILT);
                }
            } else if(SLATE_HTML_TYPE.equals(componentType)) {
                JSONObject contentJson = jsonObject.getJSONObject(CONTENT);
                markdownBuilder.append(contentJson.getStr("html"));
                htmlBuilder.append("html");
            }
            infoVo.setText(markdownBuilder.toString());
            infoVo.setMdHtml(htmlBuilder.toString());
        }
        return infoVo;
    }


    public static void main(String[] args) {
        String courseList = "https://www.educative.io/api/collection/grokking-modern-system-design-interview-for-engineers-managers?work_type=collection";

        EducativeService educativeService = new EducativeService();
        MyWebClient myWebClient = new MyWebClient();
        WebClient webClient = myWebClient.getWebClient();
        CourseListVo courseListVo = new CourseListVo();
        try {
            String htmlStr = webClient.getPage(courseList).getWebResponse().getContentAsString();
            courseListVo = educativeService.getCourseListFromStr(htmlStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 尝试获取页面信息
        String cookieStr = "_gcl_au=1.1.1948032207.1675430620; _ga=GA1.1.844986539.1675430620; _ga=GA1.1.844986539.1675430620; hubspotutk=99e4e0f9774e72b74ec73362e09b5b81; __hssrc=1; _hjSessionUser_1142875=eyJpZCI6IjBhMzM1M2MyLTlkNmYtNTVlNi05MTEzLWRlMDViOTU5NDczMyIsImNyZWF0ZWQiOjE2NzU0MzA2MjY0NzIsImV4aXN0aW5nIjp0cnVlfQ==; enterprise_nav=false; enterprise_new_sidebar=true; OneTrustWPCCPAGoogleOptOut=false; _fbp=fb.1.1675482997360.2122218640; session=eyJsb3VfaWRlbnRpZmllciI6MTIzMDM5ODAyNDMwNjk0NDA3MTYwNzM1NDYyODYwNzk0NjIxMTY1fQ==|1675847562|eebc8bcf5410285efcf73f318c83b7532d2be934; _gaexp=GAX1.2.kgblMDZ4SUyncdZ9oy0TPQ.19474.0; customizeFontCountdown=1153.614; _ga_MWGSGCW5SP=deleted; use_system_preference=personal; usprivacy=1---; _gid=GA1.1.926829408.1679279100; g_state={\"i_p\":1681748021716,\"i_l\":4}; ln_or=eyIxMTA3Mjc2IjoiZCJ9; __cf_bm=Y24MLqtW81mKCWxhz.iP7smg7pF3melOK7zygZRmYRw-1679567512-0-AQRMNXnfqeYHMebVx+VvDQbMW/+Y3gToCD1WQzy/j+vtt2Kl0p0Tw1EaWSsa3Dsdnr5uFJ1wYtH5Lhl12GOZ2Rk=; _hjIncludedInSessionSample_1142875=0; _hjSession_1142875=eyJpZCI6ImU1NDIyOWYzLWY2MzMtNDQ5MS1hMjUyLTAxZTM1ZDFkNmNkMSIsImNyZWF0ZWQiOjE2Nzk1Njc1MTc0MzEsImluU2FtcGxlIjpmYWxzZX0=; _hjIncludedInPageviewSample=1; _hjAbsoluteSessionInProgress=0; __hstc=10449898.99e4e0f9774e72b74ec73362e09b5b81.1675430664394.1679556116376.1679567521440.95; logged_in=; theme=dark; cache_token=1679567573-4/5%2BSUdR1fzMufk7LRIs4P0ObQvLu%2BzwcIJYgKK8J5I%3D; _gat_UA-68399453-1=1; OptanonConsent=isGpcEnabled=0&datestamp=Thu+Mar+23+2023+18%3A38%3A16+GMT%2B0800+(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)&version=202212.1.0&isIABGlobal=false&hosts=&consentId=e805f32a-4888-4eab-aeec-f25134240c68&interactionCount=1&landingPath=NotLandingPage&groups=C0001%3A1%2CC0002%3A1%2CC0003%3A1%2CC0004%3A1&AwaitingReconsent=false&geolocation=SG%3B; OptanonAlertBoxClosed=2023-03-23T10:38:16.656Z; _ga_MWGSGCW5SP=GS1.1.1679567509.119.1.1679567901.0.0.0; __hssc=10449898.4.1679567521440; auth=eyJfdXNlciI6WzUxMjA1MDU0MzMwMzA2NTYsMSwiQU90TzRrdEpSZzZsQVZobkdoTUJTUiIsMTY3OTU2NzU3MSwxNjc5NTY3ODcyLCJ4aWFvYmFvdGFvMTk5MEBnbWFpbC5jb20iLCJ4aWFvYmFvdGFvMTk5MEBnbWFpbC5jb20iLHRydWUsZmFsc2UsbnVsbCwzLG51bGxdfQ==|1679567902|cfc805433e4ce6b7175c62fdeefdfa4397bfcd1d";
        List<List<String>> cookieList = CookieUtils.creatCookieFromStr(cookieStr);
        MyWebClient client = new MyWebClient(cookieList);

        String filePath = "/book/" + courseListVo.getTitle();
        String PRE_DOMAIN = "https://www.educative.io/api/collection/";
        String PAGE_PATH = "page/";
        String suffix_DOMAIN = "?work_type=collection";

        for(Category category:courseListVo.getCategories()) {
            for(PageItem pageItem:category.getPageItems()) {
                if(pageItem.getAuthorId()==null)
                    continue;

                StringBuilder pagePath = new StringBuilder();
                StringBuilder urlPath = new StringBuilder();
                pagePath.append(filePath).append(category.getTitle())
                        .append(pageItem.getTitle());
                urlPath.append(PRE_DOMAIN).append(pageItem.getAuthorId())
                        .append("/").append(pageItem.getCollectionId()).append("/")
                        .append(PAGE_PATH).append(pageItem.getPageId())
                        .append(suffix_DOMAIN);
                WebClient webClient1 = client.getWebClient();
                String pageJson = null;

                try {
                    pageJson = webClient1.getPage(urlPath.toString()).getWebResponse().getContentAsString();
                    ContentItem contentItem = parseMarkdownJson(pageJson);
                    pageItem.setContentItem(contentItem);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                FileUtils.writeMDFile(pagePath.toString(), pageItem.getTitle(), "<h1>"+pageItem.getTitle()+"<h1>\n"+pageItem.getContentItem().getMdHtml());
                FileUtils.writeHTMLFile(pagePath.toString(),pageItem.getTitle(), pageItem.getContentItem().getText());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
