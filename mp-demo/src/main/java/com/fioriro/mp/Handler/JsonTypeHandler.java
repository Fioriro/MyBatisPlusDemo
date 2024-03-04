package com.fioriro.mp.Handler;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fioriro.mp.domain.po.UserInfo;

public class JsonTypeHandler extends JacksonTypeHandler {
    public JsonTypeHandler(Class<UserInfo> type) {
        super(type);
    }
}