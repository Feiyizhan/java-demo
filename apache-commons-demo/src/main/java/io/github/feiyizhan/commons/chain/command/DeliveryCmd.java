package io.github.feiyizhan.commons.chain.command;

import cn.hutool.core.lang.Console;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 发货命令
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class DeliveryCmd implements Command {

    @Override public boolean execute(Context context) throws Exception {
        Console.log("订单【{}】已发货",context.get("order"));
        context.put("orderDeliveryInfo","2020-07-23 订单发货完成");
        return false;
    }
}
