package github.ittranslation.common.vo;

import github.ittranslation.common.vo.categories.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: automation
 * @description: 课程列表实体类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-22 12:21
 **/
@Data
public class CourseListVo {

    String title;
    String summary;
    String briefSummary;
    String details;
    // 课程目标
    List<String> clos = new ArrayList<>();
    // 课程章节目录
    List<Category> categories = new ArrayList<>();
}
