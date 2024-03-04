package com.fioriro.mp.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fioriro.mp.Handler.JsonTypeHandler;
import com.fioriro.mp.domain.po.UserInfo;
import com.fioriro.mp.enums.UserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "用户VO实体")
public class UserVO {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("详细信息")
    @TableField(typeHandler = JsonTypeHandler.class)
    private UserInfo info;

    @ApiModelProperty("使用状态（1正常 2冻结）")
    private UserStatus status;

    @ApiModelProperty("账户余额")
    private Integer balance;

    @ApiModelProperty("收获地址列表")
    private List<AddressVO> addresses;
}
