package io.github.feiyizhan.commons.chain.command;

import cn.hutool.core.lang.Console;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 提交命令
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class SubmitCmd implements Command {
    @Override public boolean execute(Context context) throws Exception {
        Console.log("订单【{}】已提交",context.get("order"));
        context.put("orderSubmitInfo","2020-07-23 提交了订单");
        return false;
    }
}
