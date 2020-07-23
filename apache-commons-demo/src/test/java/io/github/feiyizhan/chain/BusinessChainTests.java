package io.github.feiyizhan.chain;

import cn.hutool.core.lang.Console;
import io.github.feiyizhan.commons.chain.BusinessChain;
import io.github.feiyizhan.commons.chain.CustomerChain;
import io.github.feiyizhan.commons.chain.OrderChain;
import io.github.feiyizhan.commons.chain.command.*;
import io.github.feiyizhan.commons.chain.filter.BankCheckFilter;
import io.github.feiyizhan.commons.chain.filter.CustomerCheckFilter;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.impl.ContextBase;
import org.junit.jupiter.api.Test;

/**
 * 业务链测试代码
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class BusinessChainTests {


    /**
     * 测试简单链
     * @author 徐明龙 XuMingLong 2020-07-23
     * @return void
     */
    @Test
    public void test_1_SimpleChain() throws Exception {
        Context ctx = new ContextBase();
        Command process = BusinessChain.buildChain(OrderChain.getSimpleChain(),
            CustomerChain.getSimpleChain());
        process.execute(ctx);
        Console.log(ctx);
    }


    /**
     * 测试完整链
     * @author 徐明龙 XuMingLong 2020-07-23
     * @return void
     */
    @Test
    public void test_2_FullChain() throws Exception {
        Context ctx = new ContextBase();
        Command process = BusinessChain.buildChain(OrderChain.getFullChain(),
            CustomerChain.getFullChain());
        process.execute(ctx);
        Console.log(ctx);
    }


    /**
     * 测试过滤链
     * @author 徐明龙 XuMingLong 2020-07-23
     * @return void
     */
    @Test
    public void test_3_FilterChain() throws Exception {
        Context ctx = new ContextBase();
        Command process = BusinessChain.buildChain(OrderChain.getFilterChain(),
            CustomerChain.getFilterChain());
        process.execute(ctx);
        Console.log(ctx);
    }


    @Test
    public void test_4_FilterChain() throws Exception {
        Context ctx = new ContextBase();
        ctx.put("orderBankCheck",true);
        Command process = BusinessChain.buildChain(OrderChain.getFilterChain(),
            CustomerChain.getFilterChain());
        process.execute(ctx);
        Console.log(ctx);
    }

    @Test
    public void test_5_FilterChain() throws Exception {
        Context ctx = new ContextBase();
        ctx.put("orderBankCheckException",true);
        Command process = BusinessChain.buildChain(OrderChain.getFilterChain(),
            CustomerChain.getFilterChain());
        process.execute(ctx);
        Console.log(ctx);
    }

    @Test
    public void test_6_FilterChain() throws Exception {
        Context ctx = new ContextBase();
        ctx.put("orderCustomerCheckException",true);
        ChainBase process = new ChainBase();
        process.addCommand(new CreateCmd());
        process.addCommand(new SubmitCmd());
        process.addCommand(new ApproveCmd());
        process.addCommand(new BankCheckFilter());
        process.addCommand(new PayCmd());
        process.addCommand(new DeliveryCmd());
        process.addCommand(new CustomerCheckFilter());
        process.addCommand(new ReceiveCmd());
        process.addCommand(new EvaluateCmd());
        process.execute(ctx);
        Console.log(ctx);
    }

    @Test
    public void test_7_FilterChain() throws Exception {
        Context ctx = new ContextBase();
        ctx.put("orderCustomerCheck",true);
        ChainBase process = new ChainBase();
        process.addCommand(new CreateCmd());
        process.addCommand(new SubmitCmd());
        process.addCommand(new ApproveCmd());
        process.addCommand(new BankCheckFilter());
        process.addCommand(new PayCmd());
        process.addCommand(new DeliveryCmd());
        process.addCommand(new CustomerCheckFilter());
        process.addCommand(new ReceiveCmd());
        process.addCommand(new EvaluateCmd());
        process.execute(ctx);
        Console.log(ctx);
    }
}
