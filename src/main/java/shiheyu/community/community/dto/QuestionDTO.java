package shiheyu.community.community.dto;

import lombok.Data;
import shiheyu.community.community.model.User;

/**
 * @Author: shiheyu
 * @Date: 2022/3/27 23:07
 */
@Data
public class QuestionDTO {
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
    private User user;
}
