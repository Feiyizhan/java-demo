package io.github.feiyizhan.commons.chain.filter;

import cn.hutool.core.lang.Console;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;

/**
 * 银行检查过滤器
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class BankCheckFilter implements Filter {
    @Override public boolean postprocess(Context context, Exception e) {
        Console.log("订单【{}】已完成检查银行卡余额",context.get("order"));
        if(e==null){
            return false;
        }
        Console.log(e,"检查银行卡异常");
        return true;
    }

    @Override public boolean execute(Context context) throws Exception {
        Console.log("订单【{}】已检查银行卡余额",context.get("order"));
        context.put("orderBankCheckInfo","2020-07-23 检查了订单的银行卡余额");
        if(context.containsKey("orderBankCheck")){
            context.put("orderBankCheckResult","2020-07-23 检查了订单的银行卡余额时发现银行卡无效");
            return true;
        }
        if(context.containsKey("orderBankCheckException")){
            throw new Exception("银行卡余额不足");
        }
        return false;
    }
}
