package com.jiuge.user.util;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jiuge.common.utils.R;

public class MyExceptionUtil {

    public static R fallback(Integer id, Throwable e) {
        return R.error(-2, "===被异常降级啦===");
    }

    public static R handleException(Integer id, BlockException e) {
        return R.error(-2, "===被限流啦===");

    }
}
