package github.ittranslation.common.vo.categories;

import lombok.Data;

/**
 * @program: automation
 * @description: 章节内容信息
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-22 15:22
 **/
@Data
public class PageItem {
    String id;

    String title;

    String type;

    String authorId;

    String collectionId;

    String pageId;

    ContentItem contentItem;
}
