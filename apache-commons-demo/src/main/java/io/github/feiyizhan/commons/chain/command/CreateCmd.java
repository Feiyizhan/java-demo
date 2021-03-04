package io.github.feiyizhan.commons.chain.command;

import cn.hutool.core.lang.Console;
import io.github.feiyizhan.commons.chain.pojo.OrderInfo;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 创建命令
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class CreateCmd implements Command {
    @Override public boolean execute(Context context) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(1);
        orderInfo.setCode("ORD202007230001");
        orderInfo.setDescription("这个一个新的订单");
        context.put("order",orderInfo);
        Console.log("订单【{}】已创建",orderInfo);
        return false;
    }
}
