package io.github.feiyizhan.chain;

import cn.hutool.core.lang.Console;
import io.github.feiyizhan.commons.chain.OrderChain;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import org.junit.jupiter.api.Test;

/**
 * 订单链测试代码
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class OrderChainTests {

    /**
     * 测试简单链
     * @author 徐明龙 XuMingLong 2020-07-23
     * @return void
     */
    @Test
    public void test_1_SimpleChain() throws Exception {
        Command process = OrderChain.getSimpleChain();
        Context ctx = new ContextBase();
        process.execute(ctx);
        Console.log(ctx);
    }


    /**
     * 测试完整链
     * @author 徐明龙 XuMingLong 2020-07-23
     * @return void
     */
    @Test
    public void test_1_FullChain() throws Exception {
        Command process = OrderChain.getFullChain();
        Context ctx = new ContextBase();
        process.execute(ctx);
        Console.log(ctx);
    }
}
