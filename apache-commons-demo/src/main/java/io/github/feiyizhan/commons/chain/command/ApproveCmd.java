package io.github.feiyizhan.commons.chain.command;

import cn.hutool.core.lang.Console;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 审批钉钉
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class ApproveCmd implements Command {
    @Override public boolean execute(Context context) throws Exception {
        Console.log("订单【{}】已审批",context.get("order"));
        context.put("orderApproveInfo","2020-07-23 审批了订单");
        return false;
    }
}
