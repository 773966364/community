package shiheyu.community.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shiheyu.community.community.mapper.UserMapper;
import shiheyu.community.community.model.User;

/**
 * @Author: shiheyu
 * @Date: 2022/4/6 22:20
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {

        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if(dbUser == null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
            //update
        }
    }

}
