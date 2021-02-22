package io.github.feiyizhan;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 规划求解
 * 元素个数在100以内的整数集合（允许有重复的元素），求满足累加（或累减、或累乘）的结果小于等于X的所有组合的列表？
 * 要求：
 *   1、每个元素只允许使用一次
 *   2、所有元素都至少被使用一次
 *   3、优先取等于X的组合，如果剩余的所有组合都不等于X，则按照组合的结果从大到小顺序取组合
 *   4、相同值的组合可任意选择一个。
 *
 * 例如：
 *    元素集合： [14, 4, 5, 3, 11, 2, 10, 4, 6, 13]，X=15，运算方法是为累加
 *
 *    需要的结果：[2, 13],   [11, 4],  [5, 10],  [14],  [4, 3, 6]
 * @author 徐明龙 XuMingLong 2021-02-05
 */
public class SolverModelTests {


    @Test
    public void test_sum2(){
        int size = 10;
        Integer[] data = new Integer[size];
//        data= Arrays.asList(14, 4, 5, 3, 11, 2, 10, 4, 6, 13).toArray(data);
        for(int i=0;i<size;i++){
            data[i] = RandomUtil.randomInt(1,16);
        }
        Console.log(data);
        final int target = 15;
        Comparator<Integer> sortCost = (r1,r2)->{
            if(r1==target){
                return -1;
            }
            if(r2==target){
                return 1;
            }
            return r2.compareTo(r1);
        };
        List<Result<Integer,Integer>> resultList = solverModel(()->Integer.valueOf(0),(c,e)->c+e,
            sortCost, (c1,c2)->c1+c2, c->c<=target,
            data);
        Console.log("最终满足条件的组合个数:{}",resultList.size());
        Console.log(resultList);
    }


    /**
     * 规划求值
     * @author 徐明龙 XuMingLong 2021-02-22
     * @param initCost
     * @param calcCost
     * @param sortCost
     * @param combineCost
     * @param filterCost
     * @param itemList
     * @return java.util.List<io.github.feiyizhan.SolverModelTests.Result<C,E>>
     */
    private <C,E> List<Result<C,E>> solverModel(Supplier<C> initCost,
        BiFunction<C,E,C> calcCost, Comparator<C> sortCost, BinaryOperator<C> combineCost,Predicate<C> filterCost,
        E... itemList){
        List<Result<C,E>> resultList = new ArrayList<>();
        int len = itemList.length;
        for(int i=0;i<len;i++){
            //准备初始结果
            C cost = initCost.get();
            E item =itemList[i];
            cost = calcCost.apply(cost,item);
            List<E> items = new ArrayList<>();
            items.add(item);
            int[] itemIndexes = new int[len];
            itemIndexes[0] = i;

            Result<C,E> initResult = new Result<>();
            initResult.setCost(cost);
            initResult.setItemList(items);
            initResult.setItemIndexes(Arrays.copyOf(itemIndexes,items.size()));
            // 1
            resultList.add(initResult);
            solverModel_1(resultList,initResult,i,initCost,calcCost,combineCost,filterCost,itemList);
        }
        Console.log("满足条件的组合个数:{}",resultList.size());
        Console.log(resultList);

        //排序
        resultList.sort((r1,r2)->sortCost.compare(r1.getCost(),r2.getCost()));
        Console.log(resultList);
        //获取最终结果
        E [] itemListBak = Arrays.copyOf(itemList,itemList.length);
        resultList = resultList.stream().filter(r->{
            int[] itemIndex = r.getItemIndexes();
            boolean hasAny = false;
            for(int index:itemIndex){
                if(itemListBak[index]==null){
                    hasAny = true;
                    break;
                }
            }
            if(hasAny){
                return false;
            }else{
                for(int index:itemIndex){
                    itemListBak[index]=null;
                }
                return true;
            }
        }).collect(Collectors.toList());

        return resultList;
    }

    /**
     * 规划求值的递归处理
     * @author 徐明龙 XuMingLong 2021-02-22
     * @param resultList
     * @param initResult
     * @param begin
     * @param initCost
     * @param calcCost
     * @param combineCost
     * @param filterCost
     * @param itemList
     * @return void
     */
    private <C,E> void solverModel_1(final List<Result<C,E>> resultList,final Result<C,E> initResult,int begin,
        Supplier<C> initCost,BiFunction<C,E,C> calcCost,BinaryOperator<C> combineCost, Predicate<C> filterCost,
        E... itemList){

        int len = itemList.length - begin -1;
        if(len<=0){
            return;
        }
        for(int j=0;j<len;j++){
            int index = j+begin+1;
            //准备每一步的初始
            Result<C,E> beginResult = cloneResult(initResult,initCost,combineCost);
            C cost =beginResult.getCost();
            List<E> items = beginResult.getItemList();
            int[] itemIndexes = new int[itemList.length];
            System.arraycopy(beginResult.getItemIndexes(),0,itemIndexes,0,items.size());
            E item =itemList[index];
            items.add(item);
            cost = calcCost.apply(cost,item);
            itemIndexes[items.size()-1] = index;
            beginResult.setCost(cost);
            beginResult.setItemList(items);
            beginResult.setItemIndexes(Arrays.copyOf(itemIndexes,items.size()));
            // 1+2,1+3,1+4...
            if(filterCost.test(cost)){
                resultList.add(beginResult);
            }
            solverModel_1(resultList,beginResult,index,initCost,calcCost,combineCost,filterCost,itemList);
        }

    }

    /**
     * 复制一个新的结果
     * @author 徐明龙 XuMingLong 2021-02-18
     * @param result
     * @param initCost
     * @param combineCost
     * @return io.github.feiyizhan.SolverModelTests.Result<C,E>
     */
    private <C,E> Result<C,E> cloneResult(Result<C,E> result, Supplier<C> initCost,BinaryOperator<C> combineCost){
        Result<C,E> newResult = new Result<>();
        newResult.setCost(combineCost.apply(initCost.get(), result.getCost()));
        newResult.setItemIndexes(Arrays.copyOf(result.getItemIndexes(),result.getItemIndexes().length));
        List<E> itemList = new ArrayList<>();
        itemList.addAll(result.getItemList());
        newResult.setItemList(itemList);
        return newResult;
    }

    @Data
    class Result<C,E>{
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
}
