package com.fioriro.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fioriro.mp.domain.po.Address;
import com.fioriro.mp.domain.po.User;
import com.fioriro.mp.mapper.AddressMapper;
import com.fioriro.mp.mapper.UserMapper;
import com.fioriro.mp.service.IAddressService;
import com.fioriro.mp.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {
}
