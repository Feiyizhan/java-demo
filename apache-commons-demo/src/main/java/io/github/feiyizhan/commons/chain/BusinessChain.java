package io.github.feiyizhan.commons.chain;

import org.apache.commons.chain.impl.ChainBase;

/**
 * 业务链
 * @author 徐明龙 XuMingLong 2020-07-23
 */
public class BusinessChain extends ChainBase {

    /**
     * 构建一个业务链
     * @author 徐明龙 XuMingLong 2020-07-23
     * @param orderChain 订单链
     * @param customerChain 客户链
     * @return io.github.feiyizhan.commons.chain.BusinessChain
     */
    public static BusinessChain buildChain(OrderChain orderChain,CustomerChain customerChain){
        BusinessChain businessChain = new BusinessChain();
        businessChain.addCommand(orderChain);
        businessChain.addCommand(customerChain);
        return businessChain;
    }
}
