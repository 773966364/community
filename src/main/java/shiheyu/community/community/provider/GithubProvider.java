package shiheyu.community.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import shiheyu.community.community.dto.AccessTokenDTO;
import shiheyu.community.community.dto.GithubUser;

import java.io.IOException;

/**
 * @Author: shiheyu
 * @Date: 2022/3/20 17:26
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post((okhttp3.RequestBody) body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();

            String token = string.split("&")[0].split("=")[1];
            return token;

        } catch (Exception e) {
                e.printStackTrace();
        }
    return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser=JSON.parseObject(string,GithubUser.class);
            System.out.println(string);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
