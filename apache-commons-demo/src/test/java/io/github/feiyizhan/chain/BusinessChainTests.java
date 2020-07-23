package io.github.feiyizhan.chain;

import cn.hutool.core.lang.Console;
import io.github.feiyizhan.commons.chain.BusinessChain;
import io.github.feiyizhan.commons.chain.CustomerChain;
import io.github.feiyizhan.commons.chain.OrderChain;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
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
}
