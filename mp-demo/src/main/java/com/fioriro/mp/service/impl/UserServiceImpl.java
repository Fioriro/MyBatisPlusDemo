package com.fioriro.mp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.fioriro.mp.domain.dto.PageDTO;
import com.fioriro.mp.domain.po.Address;
import com.fioriro.mp.domain.po.User;
import com.fioriro.mp.domain.query.PageQuery;
import com.fioriro.mp.domain.query.UserQuery;
import com.fioriro.mp.domain.vo.AddressVO;
import com.fioriro.mp.domain.vo.UserVO;
import com.fioriro.mp.enums.UserStatus;
import com.fioriro.mp.mapper.UserMapper;
import com.fioriro.mp.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Provider;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 扣除用户余额
     * @param id
     * @param money
     */
    @Override
    @Transactional
    public void deductBalance(Long id, Integer money) {
        //1. query user
        User user = getById(id);
        //2. judge user status
        if(user == null || user.getStatus().equals(UserStatus.FREEZE)){
            throw new RuntimeException("用户状态异常");
        }
        //3. judge user balance
        if(user.getBalance() < money){
            throw new RuntimeException("用户余额不足");
        }
        //4.deduct balance, update tb_user set balance = balance - ?
        int remainBalance = user.getBalance() - money;
        lambdaUpdate()
                .set(User::getBalance, remainBalance)//update balance
                .set(remainBalance == 0, User::getStatus, 2)//dynamically determining whether to update the status or not
                .eq(User::getId, id)
                .eq(User::getBalance, user.getBalance())//乐观锁
                .update();
    }

    @Override
    public UserVO queryUserAddressById(Long userId) {
        //1. query user
        User user = getById(userId);
        if(user == null){
            return null;
        }
        //2. query address
        List<Address> addresses = Db.lambdaQuery(Address.class)
                .eq(Address::getUserId, userId)
                .list();
        //3.process VO
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        userVO.setAddresses(BeanUtil.copyToList(addresses, AddressVO.class));
        return userVO;
    }


    @Override
    public PageDTO<UserVO> queryUsersPage(PageQuery query) {
//        // 1.构建条件
//        // 1.1.分页条件
//        Page<User> page = Page.of(query.getPageNo(), query.getPageSize());
//        // 1.2.排序条件
//        if (query.getSortBy() != null) {
//            page.addOrder(new OrderItem(query.getSortBy(), query.getIsAsc()));
//        }else{
//            // 默认按照更新时间排序
//            page.addOrder(new OrderItem("update_time", false));
//        }
//        // 2.查询
//        page(page);
//        // 3.数据非空校验
//        List<User> records = page.getRecords();
//        if (records == null || records.size() <= 0) {
//            // 无数据，返回空结果
//            return new PageDTO<>(page.getTotal(), page.getPages(), Collections.emptyList());
//        }
//        // 4.有数据，转换
//        List<UserVO> list = BeanUtil.copyToList(records, UserVO.class);
//        // 5.封装返回
//        return new PageDTO<UserVO>(page.getTotal(), page.getPages(), list);
        //1.construct conditions
        Page<User> page = query.toMpPageDefaultSortByCreateTimeDesc();
        //2.query
        page(page);
        //3.encapsulate and return
        return PageDTO.of(page, UserVO.class);
    }
}
