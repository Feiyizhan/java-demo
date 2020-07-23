package io.github.feiyizhan.commons.chain;

import io.github.feiyizhan.commons.chain.command.*;
import org.apache.commons.chain.impl.ChainBase;

/**
 * 订单链
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class OrderChain extends ChainBase {

    /**
     * 获取一个简单的订单链
     * @author 徐明龙 XuMingLong 2020-07-23
     * @param
     * @return io.github.feiyizhan.commons.chain.OrderChain
     */
    public static OrderChain getSimpleChain(){
        OrderChain chain = new OrderChain();
        chain.addCommand(new CreateCmd());
        chain.addCommand(new PayCmd());
        chain.addCommand(new DeliveryCmd());
        return chain;
    }


    /**
     * 获取一个完整的订单链
     * @author 徐明龙 XuMingLong 2020-07-23
     * @param
     * @return io.github.feiyizhan.commons.chain.OrderChain
     */
    public static OrderChain getFullChain(){
        OrderChain chain = new OrderChain();
        chain.addCommand(new CreateCmd());
        chain.addCommand(new SubmitCmd());
        chain.addCommand(new ApproveCmd());
        chain.addCommand(new PayCmd());
        chain.addCommand(new DeliveryCmd());
        return chain;
    }
}
