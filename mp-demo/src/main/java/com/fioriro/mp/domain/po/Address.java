package com.fioriro.mp.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("address")
public class Address {

    @TableId("id")
    Long id;
    @TableField("user_id")
    Long userId;
    @TableField("province")
    String province;
    @TableField("city")
    String city;
    @TableField("town")
    String town;
    @TableField("mobile")
    String mobile;
    @TableField("street")
    String street;
    @TableField("contact")
    String contact;
    @TableField("is_default")
    short isDefault;
    @TableField("notes")
    String notes;
    @TableField("deleted")
    short deleted;
}
