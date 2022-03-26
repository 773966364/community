package shiheyu.community.community.dto;

import lombok.Data;

/**
 * @Author: shiheyu
 * @Date: 2022/3/20 17:30
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
