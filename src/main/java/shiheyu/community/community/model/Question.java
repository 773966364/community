package shiheyu.community.community.model;

import lombok.Data;

/**
 * @Author: shiheyu
 * @Date: 2022/3/25 22:34
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
