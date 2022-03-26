package shiheyu.community.community.model;

import lombok.Data;

/**
 * @Author: shiheyu
 * @Date: 2022/3/21 23:06
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
