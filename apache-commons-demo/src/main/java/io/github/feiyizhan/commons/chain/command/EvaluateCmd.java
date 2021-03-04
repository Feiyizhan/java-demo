package io.github.feiyizhan.commons.chain.command;

import cn.hutool.core.lang.Console;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 评价订单
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class EvaluateCmd implements Command {

    @Override public boolean execute(Context context) throws Exception {
        Console.log("订单【{}】已评价",context.get("order"));
        context.put("orderEvaluateInfo","2020-07-25 评价了订单");
        return false;
    }
}
