package com.fioriro.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fioriro.mp.domain.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("update user set balance = balance - #{money} ${ew.customSqlSegment}")
    //note that the custom syntax should be annotated by @Param("ew")
    void deductBalanceByIds(@Param("money") int money, @Param("ew") QueryWrapper<User> wrapper);

    @Select("select u.* from user u inner join mp.address a on u.id = a.user_id ${ew.customSqlSegment}")
    List<User> queryUserByWrapper(@Param("ew") QueryWrapper<User> wrapper);



//    void saveUser(User user);
//
//    void deleteUser(Long id);
//
//    void updateUser(User user);
//
//    User queryUserById(@Param("id") Long id);
//
//    List<User> queryUserByIds(@Param("ids") List<Long> ids);
}
