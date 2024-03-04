package com.fioriro.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fioriro.mp.domain.dto.PageDTO;
import com.fioriro.mp.domain.po.User;
import com.fioriro.mp.domain.query.PageQuery;
import com.fioriro.mp.domain.query.UserQuery;
import com.fioriro.mp.domain.vo.UserVO;

public interface IUserService extends IService<User> {
    /**
     * 扣除用户余额
     * @param id
     * @param money
     */
    void deductBalance(Long id, Integer money);

    UserVO queryUserAddressById(Long userId);

    PageDTO<UserVO> queryUsersPage(PageQuery query);
    //拓展自定义方法
}
