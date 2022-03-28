package shiheyu.community.community.dto;

import lombok.Data;

/**
 * @Author: shiheyu
 * @Date: 2022/3/20 18:34
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
