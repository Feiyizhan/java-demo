package io.github.feiyizhan.commons.chain;

import io.github.feiyizhan.commons.chain.command.EvaluateCmd;
import io.github.feiyizhan.commons.chain.command.ReceiveCmd;
import io.github.feiyizhan.commons.chain.filter.CustomerCheckFilter;
import org.apache.commons.chain.impl.ChainBase;

/**
 * 客户链
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class CustomerChain extends ChainBase {

    /**
     * 获取简单的链
     * @author 徐明龙 XuMingLong 2020-07-23
     * @return io.github.feiyizhan.commons.chain.CustomerChain
     */
    public static CustomerChain getSimpleChain(){
        CustomerChain chain = new CustomerChain();
        chain.addCommand(new ReceiveCmd());
        return chain;
    }

    /**
     * 获取完整的链
     * @author 徐明龙 XuMingLong 2020-07-23
     * @return io.github.feiyizhan.commons.chain.CustomerChain
     */
    public static CustomerChain getFullChain(){
        CustomerChain chain = new CustomerChain();
        chain.addCommand(new ReceiveCmd());
        chain.addCommand(new EvaluateCmd());
        return chain;
    }

    /**
     * 获取带过滤的链
     * @author 徐明龙 XuMingLong 2020-07-23
     * @return io.github.feiyizhan.commons.chain.CustomerChain
     */
    public static CustomerChain getFilterChain(){
        CustomerChain chain = new CustomerChain();
        chain.addCommand(new CustomerCheckFilter());
        chain.addCommand(new ReceiveCmd());
        chain.addCommand(new EvaluateCmd());
        return chain;
    }
}
