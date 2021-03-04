package io.github.feiyizhan.commons.chain.pojo;

import lombok.Data;

/**
 * 订单信息
 * @author 徐明龙 XuMingLong 2020-07-23
 */
@Data
public class OrderInfo {

    /**
     * 订单id
     * @author 徐明龙 XuMingLong 2020-07-23
     */
    private Integer id;

    /**
     * 订单编号
     * @author 徐明龙 XuMingLong 2020-07-23
     */
    private String code;

    /**
     * 订单描述
     * @author 徐明龙 XuMingLong 2020-07-23
     */
    private String description;
}
