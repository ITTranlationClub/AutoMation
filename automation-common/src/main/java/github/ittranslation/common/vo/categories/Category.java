package github.ittranslation.common.vo.categories;

import lombok.Data;

import java.util.List;

/**
 * @program: automation
 * @description: 章节 相关信息实体类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-22 15:22
 **/
@Data
public class Category {
    String id;

    String title;

    Boolean editMode;

    String type;

    List<PageItem> pageItems;
}
