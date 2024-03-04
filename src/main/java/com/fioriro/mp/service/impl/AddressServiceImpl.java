package com.fioriro.mp.service.impl;

import com.fioriro.mp.domain.po.Address;
import com.fioriro.mp.mapper.AddressMapper;
import com.fioriro.mp.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Fioriro
 * @since 2024-03-04
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
