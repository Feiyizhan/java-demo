package io.github.feiyizhan.commons.chain.command;

import cn.hutool.core.lang.Console;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 付款命令
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class PayCmd implements Command {
    @Override public boolean execute(Context context) throws Exception {
        Console.log("订单【{}】已付款",context.get("order"));
        context.put("orderPayInfo","2020-07-23 订单付款了200元");
        return false;
    }
}
