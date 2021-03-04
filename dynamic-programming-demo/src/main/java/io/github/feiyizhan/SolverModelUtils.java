package io.github.feiyizhan;

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
 * 规划求值工具类
 * @author 徐明龙 XuMingLong 2021-02-22
 */
public class SolverModelUtils {


    /**
     * 规划求值
     * @author 徐明龙 XuMingLong 2021-02-22
     * @param initCost
     * @param calcCost
     * @param sortCost
     * @param combineCost
     * @param filterCost
     * @param itemList
     * @return java.util.List<com.expertsplatform.commons.pojo.result.SolverModelResult<C,E>>
     */
    public static <C,E> List<SolverModelResult<C,E>> solverModel(Supplier<C> initCost,
        BiFunction<C,E,C> calcCost, Comparator<C> sortCost, BinaryOperator<C> combineCost, Predicate<C> filterCost,
        E... itemList){
        List<SolverModelResult<C,E>> resultList = new ArrayList<>();
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

            SolverModelResult<C,E> initResult = new SolverModelResult<>();
            initResult.setCost(cost);
            initResult.setItemList(items);
            initResult.setItemIndexes(Arrays.copyOf(itemIndexes,items.size()));
            resultList.add(initResult);
            solverModel_1(resultList,initResult,i,initCost,calcCost,combineCost,filterCost,itemList);
        }



        //获取最终结果
        E [] itemListBak = Arrays.copyOf(itemList,itemList.length);

        resultList = resultList.stream()
            //排序
            .sorted((r1,r2)->sortCost.compare(r1.getCost(),r2.getCost()))
            //过滤
            .filter(r->{
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
    private static <C,E> void solverModel_1(final List<SolverModelResult<C,E>> resultList,final SolverModelResult<C,E> initResult,int begin,
        Supplier<C> initCost,BiFunction<C,E,C> calcCost,BinaryOperator<C> combineCost, Predicate<C> filterCost,
        E... itemList){

        int len = itemList.length - begin -1;
        if(len<=0){
            return;
        }
        for(int j=0;j<len;j++){
            int index = j+begin+1;
            //准备每一步的初始
            SolverModelResult<C,E> beginResult = cloneResult(initResult,initCost,combineCost);
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
     * @author 徐明龙 XuMingLong 2021-02-22
     * @param result
     * @param initCost
     * @param combineCost
     * @return com.expertsplatform.commons.pojo.result.SolverModelResult<C,E>
     */
    private static <C,E> SolverModelResult<C,E> cloneResult(SolverModelResult<C,E> result,
        Supplier<C> initCost, BinaryOperator<C> combineCost){
        SolverModelResult<C,E> newResult = new SolverModelResult<>();
        newResult.setCost(combineCost.apply(initCost.get(), result.getCost()));
        newResult.setItemIndexes(Arrays.copyOf(result.getItemIndexes(),result.getItemIndexes().length));
        List<E> itemList = new ArrayList<>();
        itemList.addAll(result.getItemList());
        newResult.setItemList(itemList);
        return newResult;
    }
}
