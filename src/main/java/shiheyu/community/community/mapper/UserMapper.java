package shiheyu.community.community.mapper;

import org.apache.ibatis.annotations.*;
import shiheyu.community.community.model.User;

/**
 * @Author: shiheyu
 * @Date: 2022/3/21 23:00
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id = #{account_id}")
    User findByAccountId(@Param("account_id") String account_id);

    @Update("update user set token = #{token} where account_id = #{account_id}")
    Integer updateToken(@Param("token") String token,@Param("account_id") String account_id);
}
