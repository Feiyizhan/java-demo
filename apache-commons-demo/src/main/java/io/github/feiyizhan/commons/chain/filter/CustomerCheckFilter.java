package io.github.feiyizhan.commons.chain.filter;

import cn.hutool.core.lang.Console;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;

/**
 * 检查订单
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class CustomerCheckFilter implements Filter {

    @Override public boolean execute(Context context) throws Exception {
        Console.log("订单【{}】已验货",context.get("order"));
        context.put("orderCustomerCheckInfo","2020-07-24 订单验货完成");
        if(context.containsKey("orderCustomerCheck")){
            context.put("orderCustomerCheckResult","2020-07-24 检查了客户订单验货，商品缺失");
            return true;
        }
        if(context.containsKey("orderCustomerCheckException")){
            throw new Exception("订单商品损坏");
        }
        return false;
    }

    @Override public boolean postprocess(Context context, Exception e) {
        Console.log("订单【{}】已完成验货",context.get("order"));
        if(e==null){
            return false;
        }
        Console.log(e,"订单验货异常");
        return true;
    }
}
