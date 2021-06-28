package io.github.feiyizhan;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;

import java.util.Comparator;
import java.util.List;

/**
 * 2021-06-28：最接近目标值的子序列和。给你一个整数数组 nums 和一个目标...如何解答呢？
 * 2021-06-28：最接近目标值的子序列和。给你一个整数数组 nums 和一个目标值 goal 。
 * 你需要从 nums 中选出一个子序列，使子序列元素总和最接近 goal 。
 * 也就是说，如果子序列元素和为 sum ，你需要 最小化绝对差 abs(sum - goal) 。返回 abs(sum - goal) 可能的 最小值 。
 * 注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。
 * 输入：nums = [7,-9,15,-2], goal = -5。输出：1。解释：选出子序列 [7,-9,-2] ，元素和为 -4 。
 * 绝对差为 abs(-4 - (-5)) = abs(1) = 1 ，是可能的最小值。
 * @author 徐明龙 XuMingLong 2021-06-28
 */
public class MinimizeTheAbsoluteDifferenceDemo {

    public static void main(String[] args) {
        int size = 10;
        Integer[] data = new Integer[size];
//        data= Arrays.asList(14, 4, 5, 3, 11, 2, 10, 4, 6, 13).toArray(data);
        for(int i=0;i<size;i++){
            data[i] = RandomUtil.randomInt(-16,16);
        }
        Console.log(data);
        final int target = 15;
        Comparator<Integer> sortCost = (r1,r2)->Integer.compare(0-Math.abs(r2-target),0-Math.abs(r1-target));
        List<SolverModelResult<Integer,Integer>> resultList =
            SolverModelUtils.solverModel(
                //初始cost
                ()->Integer.valueOf(0),
                // 计算cost
                (c,e)->c+e,
                // cost 排序
                sortCost,
                // cost 合并
                (c1,c2)->c1+c2,
                // cost 过滤
                c->true,
                data);
        Console.log("最终满足条件的组合个数:{}",resultList.size());
        Console.log(resultList);
    }
}
