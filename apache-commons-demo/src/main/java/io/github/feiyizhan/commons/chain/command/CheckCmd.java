package io.github.feiyizhan.commons.chain.command;

import cn.hutool.core.lang.Console;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 检查订单
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class CheckCmd implements Command {

    @Override public boolean execute(Context context) throws Exception {
        Console.log("订单【{}】已验货",context.get("order"));
        context.put("orderDeliveryInfo","2020-07-24 订单验货完成");
        return false;
    }
}
