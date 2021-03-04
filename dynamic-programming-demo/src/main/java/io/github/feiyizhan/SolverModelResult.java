package io.github.feiyizhan;

import lombok.Data;

import java.util.List;

/**
 * 规划求值的结果
 * @author 徐明龙 XuMingLong 2021-02-22
 */
@Data
public class SolverModelResult<C,E> {
    /**
     * 成本
     * @author 徐明龙 XuMingLong 2021-02-07
     */
    private C cost;
    /**
     * 元素个数
     * @author 徐明龙 XuMingLong 2021-02-07
     */
    private List<E> itemList;

    /**
     * 元素的下标列表
     * @author 徐明龙 XuMingLong 2021-02-08
     */
    private int[] itemIndexes;
}
