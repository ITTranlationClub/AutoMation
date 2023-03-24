package github.ittranslation.automation.chatgpt.common;

import github.ittranslation.automation.chatgpt.common.vo.InfoVo;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @program: EduAutomation
 * @description: 文章内容结构解析工具类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-21 11:13
 **/
public class MarkdownParseUtils {

    static String MARKDOWN_EDITOR_TYPE = "MarkdownEditor";
    static String STRUCTURED_QUIZ = "StructureQuiz";
    static String SLATE_HTML_TYPE = "SlateHTML";

    static String EDIT_MODE = "edit";

    public static InfoVo parseMarkdownJson(String markdownStr) {
        JSONObject markJson = JSONUtil.parseObj(markdownStr);
        JSONObject summaryJson = JSONUtil.parseObj(markJson.getJSONObject("summary"));
        JSONArray componentArray = markJson.getJSONArray("components");

        // 分别识别 Markdown 文本 与 html 文本, 写入不同结果集中保存
//        for() {
=======
//package github.ittranslation.common;
>>>>>>> 5f8efe14b3024a1a43148c5d64b105df9d12cc84
//
//import github.ittranslation.common.vo.InfoVo;
//import cn.hutool.json.JSONArray;
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//
//import javax.swing.text.html.HTML;
//
///**
// * @program: EduAutomation
// * @description: 文章内容结构解析工具类
// * @author: gaoxiang
// * @email: 630268696@qq.com
// * @create: 2023-03-21 11:13
// **/
//public class MarkdownParseUtils {
//
//    static String MARKDOWN_EDITOR_TYPE = "MarkdownEditor";
//    static String LAZY_LOAD_PLACE_HOLDER = "LazyLoadPlaceholder";
//    static String STRUCTURED_QUIZ = "StructuredQuiz";
//    static String COMPONENTS = "components";
//    static String SUMMARY = "summary";
//
//    static String SLATE_HTML_TYPE = "SlateHTML";
//    static String TITLE = "title";
//    static String TITLE_MD_HTML = "titleMdHtml";
//
//    static String DESCRIPTION = "description";
//    static String CONTENT = "content";
//    static String TEXT = "text";
//    static String MD_HTML = "mdHtml";
//
//    static String QUESTIONS = "questions";
//    static String QUESTION_TEXT = "questionText";
//    static String QUESTION_TEXT_HTML = "questionTextHtml";
//    static String ANSWER_TEXT = "answerText";
//    static String ANSWER_TEXT_HTML = "answerTextHtml";
//
//    static String EDIT_MODE = "edit";
//    static String SYSTEM_SPILT = System.lineSeparator();
//
//    public static InfoVo parseMarkdownJson(String markdownStr) {
//        JSONObject markJson = JSONUtil.parseObj(markdownStr);
//        JSONObject summaryJson = markJson.getJSONObject(SUMMARY);
//        JSONArray componentArray = markJson.getJSONArray(COMPONENTS);
//
//        // 分别识别 Markdown 文本 与 html 文本, 写入不同结果集中保存
//        InfoVo infoVo = new InfoVo();
//        infoVo.setTitle(summaryJson.getStr(TITLE));
//        infoVo.setDescription(summaryJson.getStr(DESCRIPTION));
//
//        StringBuilder markdownBuilder = new StringBuilder();
//        StringBuilder htmlBuilder = new StringBuilder();
//
//        for(JSONObject jsonObject:componentArray.jsonIter()) {
//            String componentType = jsonObject.getStr("type");
//            if(MARKDOWN_EDITOR_TYPE.equals(componentType)) {
//                if(!jsonObject.getJSONObject(CONTENT).containsKey(TEXT))
//                    continue;
//                markdownBuilder.append(jsonObject.getJSONObject(CONTENT).getStr(TEXT));
//                markdownBuilder.append(SYSTEM_SPILT);
//
//                htmlBuilder.append(jsonObject.getJSONObject(CONTENT).getStr(MD_HTML));
//                htmlBuilder.append(SYSTEM_SPILT);
//            } else if(LAZY_LOAD_PLACE_HOLDER.equals(componentType)) {
//                continue;
//            } else if(STRUCTURED_QUIZ.equals(componentType)) {
//                JSONObject titleJSON = jsonObject.getJSONObject(CONTENT);
//                if(!titleJSON.containsKey(TITLE))
//                    continue;
//                markdownBuilder.append(titleJSON.getStr(TITLE));
//                markdownBuilder.append(SYSTEM_SPILT);
//
//                htmlBuilder.append(titleJSON.getStr(TITLE_MD_HTML));
//                htmlBuilder.append(SYSTEM_SPILT);
//
//                JSONArray questionArray =  titleJSON.getJSONArray(QUESTIONS);
//
//                for(JSONObject questionObj:questionArray.jsonIter()) {
//                    markdownBuilder.append(questionObj.getStr(QUESTION_TEXT));
//                    markdownBuilder.append(SYSTEM_SPILT);
//                    markdownBuilder.append(questionObj.getStr(ANSWER_TEXT));
//                    markdownBuilder.append(SYSTEM_SPILT);
//
//                    htmlBuilder.append(questionObj.getStr(QUESTION_TEXT_HTML));
//                    htmlBuilder.append(SYSTEM_SPILT);
//                    htmlBuilder.append(questionObj.getStr(ANSWER_TEXT_HTML));
//                    htmlBuilder.append(SYSTEM_SPILT);
//                }
//            } else if(SLATE_HTML_TYPE.equals(componentType)) {
//                JSONObject contentJson = jsonObject.getJSONObject(CONTENT);
//                markdownBuilder.append(contentJson.getStr("html"));
//                htmlBuilder.append("html");
//            }
//            infoVo.setText(markdownBuilder.toString());
//            infoVo.setMdHtml(htmlBuilder.toString());
//        }
//        return infoVo;
//    }
//
//    public static void main(String[] args) {
//        InfoVo infoVo = parseMarkdownJson(jsonStr);
//
//        String dirPath = "/book";
//        FileUtils.writeMDFile(dirPath, infoVo.getTitle(), infoVo.getText());
//        FileUtils.writeHTMLFile(dirPath, infoVo.getTitle(), infoVo.getMdHtml());
////        System.out.println(infoVo.getText());
////        System.out.println();
////        System.out.println(infoVo.getMdHtml());
//    }
//
//    static String jsonStr = "{\"components\":[{\"type\":\"MarkdownEditor\",\"mode\":\"edit\",\"content\":{\"version\":\"2.0\",\"text\":\"## What is load balancing?\\n\\nMillions of requests could arrive per second in a typical data center. To serve these requests, thousands (or a hundred thousand) servers work together to share the load of incoming requests. \\n\\n> **Note:** Here, it's important that we consider how the incoming requests will be divided among all the available servers.\\n\\nA **load balancer** (**LB**) is the answer to the question. The job of the load balancer is to fairly divide all clients’ requests among the pool of available servers. Load balancers perform this job to avoid overloading or crashing servers.\\n\\nThe load balancing layer is the first point of contact within a data center after the firewall. A load balancer may not be required if a service entertains a few hundred or even a few thousand requests per second. However, for increasing client requests, load balancers provide the following capabilities:\\n\\n- **Scalability**: By adding servers, the capacity of the application/service can be increased seamlessly. Load balancers make such upscaling or downscaling transparent to the end users.\\n- **Availability**: Even if some servers go down or suffer a fault, the system still remains available. One of the jobs of the load balancers is to hide faults and failures of servers.\\n- **Performance**: Load balancers can forward requests to servers with a lesser load so the user can get a quicker response time. This not only improves performance but also improves resource utilization.\\n\\nHere’s an abstract depiction of how load balancers work:\\n\",\"mdHtml\":\"<h2>What is load balancing?</h2>\\n<p>Millions of requests could arrive per second in a typical data center. To serve these requests, thousands (or a hundred thousand) servers work together to share the load of incoming requests.</p>\\n<blockquote>\\n<p><strong>Note:</strong> Here, it’s important that we consider how the incoming requests will be divided among all the available servers.</p>\\n</blockquote>\\n<p>A <strong>load balancer</strong> (<strong>LB</strong>) is the answer to the question. The job of the load balancer is to fairly divide all clients’ requests among the pool of available servers. Load balancers perform this job to avoid overloading or crashing servers.</p>\\n<p>The load balancing layer is the first point of contact within a data center after the firewall. A load balancer may not be required if a service entertains a few hundred or even a few thousand requests per second. However, for increasing client requests, load balancers provide the following capabilities:</p>\\n<ul>\\n<li><strong>Scalability</strong>: By adding servers, the capacity of the application/service can be increased seamlessly. Load balancers make such upscaling or downscaling transparent to the end users.</li>\\n<li><strong>Availability</strong>: Even if some servers go down or suffer a fault, the system still remains available. One of the jobs of the load balancers is to hide faults and failures of servers.</li>\\n<li><strong>Performance</strong>: Load balancers can forward requests to servers with a lesser load so the user can get a quicker response time. This not only improves performance but also improves resource utilization.</li>\\n</ul>\\n<p>Here’s an abstract depiction of how load balancers work:</p>\\n\",\"comp_id\":\"MFZ3gB6RHbA3KljeacsfU\",\"cursorPosition\":13},\"iteration\":2,\"hash\":0,\"saveVersion\":86,\"children\":[{\"text\":\"\"}]},{\"type\":\"LazyLoadPlaceholder\",\"content\":{\"actualType\":\"MxGraphWidget\",\"widgetIndex\":1,\"contentRevision\":\"149\"}},{\"type\":\"MarkdownEditor\",\"content\":{\"version\":\"2.0\",\"text\":\"## Placing load balancers\\n\\nGenerally, LBs sit between clients and servers. Requests go through to servers and back to clients via the load balancing layer. However, that isn’t the only point where load balancers are used.\\n\\nLet’s consider the three well-known groups of servers. That is the web, the application, and the database servers. To divide the traffic load among the available servers, load balancers can be used between the server instances of these three services in the following way:\\n\\n- Place LBs between end users of the application and web servers/application gateway.\\n- Place LBs between the web servers and application servers that run the business/application logic.\\n- Place LBs between the application servers and database servers.\\n\\n\\n\",\"mdHtml\":\"<h2>Placing load balancers</h2>\\n<p>Generally, LBs sit between clients and servers. Requests go through to servers and back to clients via the load balancing layer. However, that isn’t the only point where load balancers are used.</p>\\n<p>Let’s consider the three well-known groups of servers. That is the web, the application, and the database servers. To divide the traffic load among the available servers, load balancers can be used between the server instances of these three services in the following way:</p>\\n<ul>\\n<li>Place LBs between end users of the application and web servers/application gateway.</li>\\n<li>Place LBs between the web servers and application servers that run the business/application logic.</li>\\n<li>Place LBs between the application servers and database servers.</li>\\n</ul>\\n\",\"cursorPosition\":704,\"comp_id\":\"CIM4oUCQez1bKwUSNcN18\"},\"iteration\":0,\"hash\":2,\"saveVersion\":26,\"children\":[{\"text\":\"\"}]},{\"type\":\"LazyLoadPlaceholder\",\"content\":{\"actualType\":\"MxGraphWidget\",\"widgetIndex\":3,\"contentRevision\":\"149\"}},{\"type\":\"MarkdownEditor\",\"mode\":\"edit\",\"content\":{\"version\":\"2.0\",\"text\":\"In reality, load balancers can be potentially used between any two services with multiple instances within the design of a system.\\n\\n## Services offered by load balancers\\n\\nLBs not only enable services to be scalable, available, and highly performant, they offer some key services like the following:\\n- **Health checking**: LBs use the #key# heartbeat protocol: The heartbeat protocol is a way of identifying failures in distributed systems. Using this protocol, every node in a cluster periodically reports its health to a monitoring service. #key# to monitor the health and, therefore, reliability of end-servers. Another advantage of health checking is the improved user experience.\\n- **#key# TLS termination: TLS termination, also called TLS/SSL offloading, is the establishment of secure communication channels between clients and servers through encryption/decryption of data. #key#**: LBs reduce the burden on end-servers by handling TLS termination with the client.\\n- **Predictive analytics**: LBs can predict traffic patterns through analytics performed over traffic passing through them or using statistics of traffic obtained over time.\\n- **Reduced human intervention**: Because of LB automation, reduced system administration efforts are required in handling failures.\\n- **Service discovery**: An advantage of LBs is that the clients’ requests are forwarded to appropriate hosting servers by inquiring about the  #key# service registry: Service registry is a repository of the (micro)services and the instances available against each service. #key#.\\n- **Security**: LBs may also improve security by mitigating attacks like #key# denial-of-service (DoS): The DoS is an attack where a client floods the server with traffic to exhaust the server's resources (processing and/or memory) such that it is unable to process a legitimate user's request. #key# at different layers of the #key# OSI model: The open systems interconnection (OSI) model is a conceptual framework that divides the problem of connecting any two machines into seven different layers. #key# (layers 3, 4, and 7).\\n\\nAs a whole, load balancers provide  #key# flexibility: Add or remove machines transparently on the fly. #key#,  #key# reliability: Buggy hosts can be removed through health monitoring which makes the system reliable. #key#, #key# redundancy: Multiple paths leading to the same destination or failed server's load is rerouted to the failover machine. #key#, and #key# efficiency: Divide load evenly on all machines to use them effectively from the point of view of the service provider. #key# to the overall design of the system.\",\"mdHtml\":\"<p>In reality, load balancers can be potentially used between any two services with multiple instances within the design of a system.</p>\\n<h2>Services offered by load balancers</h2>\\n<p>LBs not only enable services to be scalable, available, and highly performant, they offer some key services like the following:</p>\\n<ul>\\n<li><strong>Health checking</strong>: LBs use the <keyword><word>heartbeat protocol</word><meaning>The heartbeat protocol is a way of identifying failures in distributed systems. Using this protocol, every node in a cluster periodically reports its health to a monitoring service.</meaning></keyword> to monitor the health and, therefore, reliability of end-servers. Another advantage of health checking is the improved user experience.</li>\\n<li><strong><keyword><word>TLS termination</word><meaning>TLS termination, also called TLS/SSL offloading, is the establishment of secure communication channels between clients and servers through encryption/decryption of data.</meaning></keyword></strong>: LBs reduce the burden on end-servers by handling TLS termination with the client.</li>\\n<li><strong>Predictive analytics</strong>: LBs can predict traffic patterns through analytics performed over traffic passing through them or using statistics of traffic obtained over time.</li>\\n<li><strong>Reduced human intervention</strong>: Because of LB automation, reduced system administration efforts are required in handling failures.</li>\\n<li><strong>Service discovery</strong>: An advantage of LBs is that the clients’ requests are forwarded to appropriate hosting servers by inquiring about the  <keyword><word>service registry</word><meaning>Service registry is a repository of the (micro)services and the instances available against each service.</meaning></keyword>.</li>\\n<li><strong>Security</strong>: LBs may also improve security by mitigating attacks like <keyword><word>denial-of-service (DoS)</word><meaning>The DoS is an attack where a client floods the server with traffic to exhaust the server’s resources (processing and/or memory) such that it is unable to process a legitimate user’s request.</meaning></keyword> at different layers of the <keyword><word>OSI model</word><meaning>The open systems interconnection (OSI) model is a conceptual framework that divides the problem of connecting any two machines into seven different layers.</meaning></keyword> (layers 3, 4, and 7).</li>\\n</ul>\\n<p>As a whole, load balancers provide  <keyword><word>flexibility</word><meaning>Add or remove machines transparently on the fly.</meaning></keyword>,  <keyword><word>reliability</word><meaning>Buggy hosts can be removed through health monitoring which makes the system reliable.</meaning></keyword>, <keyword><word>redundancy</word><meaning>Multiple paths leading to the same destination or failed server’s load is rerouted to the failover machine.</meaning></keyword>, and <keyword><word>efficiency</word><meaning>Divide load evenly on all machines to use them effectively from the point of view of the service provider.</meaning></keyword> to the overall design of the system.</p>\\n\",\"cursorPosition\":2334,\"comp_id\":\"KDJQP6u4fONWHzdDfjGK8\"},\"iteration\":2,\"hash\":4,\"children\":[{\"text\":\"\"}],\"saveVersion\":3},{\"type\":\"StructuredQuiz\",\"mode\":\"edit\",\"content\":{\"version\":\"1.0\",\"title\":\"Food for thought\",\"renderMode\":\"continuous\",\"questions\":[{\"questionText\":\"What if load balancers fail? Are they not a single point of failure (SPOF)?\",\"questionTextHtml\":\"<p>What if load balancers fail? Are they not a single point of failure (SPOF)?</p>\\n\",\"answerText\":\"Load balancers are usually deployed in pairs as a means of disaster recovery. If one load balancer fails, and there’s nothing to failover to, the overall service will go down. Generally, to maintain high availability, enterprises use clusters of load balancers that use heartbeat communication to check the health of load balancers at all times. On failure of primary LB, the backup can take over. But, if the entire cluster fails, manual rerouting can also be performed in case of emergencies.\",\"answerTextHtml\":\"<p>Load balancers are usually deployed in pairs as a means of disaster recovery. If one load balancer fails, and there’s nothing to failover to, the overall service will go down. Generally, to maintain high availability, enterprises use clusters of load balancers that use heartbeat communication to check the health of load balancers at all times. On failure of primary LB, the backup can take over. But, if the entire cluster fails, manual rerouting can also be performed in case of emergencies.</p>\\n\"}],\"titleMdHtml\":\"<p>Food for thought</p>\\n\",\"comp_id\":\"tR_Q-zGhlyEelByVK7JoY\"},\"iteration\":0,\"hash\":5,\"saveVersion\":23,\"children\":[{\"text\":\"\"}]},{\"type\":\"SlateHTML\",\"content\":{\"html\":\"<p>In the coming lessons, we’ll see how load balancers can be used in complex applications and which type of load balancer is appropriate for which use case.</p>\",\"comp_id\":\"yaYDCLkuAlV3cPUnAM37u\"},\"hash\":6}],\"summary\":{\"title\":\"Introduction to Load Balancers\",\"titleUpdated\":true,\"description\":\"Learn about the basics of load balancers and the services offered by them.\\n\"}}";
//
//}
