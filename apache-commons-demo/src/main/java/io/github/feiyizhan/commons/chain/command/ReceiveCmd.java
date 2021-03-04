package io.github.feiyizhan.commons.chain.command;

import cn.hutool.core.lang.Console;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 收货命令
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class ReceiveCmd implements Command {
    @Override public boolean execute(Context context) throws Exception {
        Console.log("订单【{}】已收货",context.get("order"));
        context.put("orderReceiveInfo","2020-07-24 收到了订单的商品");
        return false;
    }
}
