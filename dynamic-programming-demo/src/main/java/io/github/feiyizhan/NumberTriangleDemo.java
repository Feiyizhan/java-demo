package io.github.feiyizhan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数字三角形问题
 * <p>给定一个数字三角形，找到从顶部到底部的最小路径和。每一步可以移动到下面一行的相邻数字上。
 * <p> 例如：
 * <p>　　[
 * <p>　　     [2],
 * <p> 　　   [3,4],
 * <p> 　　  [6,5,7],
 * <p>　　  [4,1,8,3]
 * <p>　　]
 * <p>　　从顶到底部的最小路径和为11 ( 2 + 3 + 5 + 1 = 11)。
 * <p>  解析：
 * <p>  第一层 只有一个2，因此第一层的最小路径和：2，步骤为：2
 * <p>  第一层-> 第二层 ，有两种组合，2+3、2+4 ，其中2+3最小，因此取2+3， 第一层-> 第二层的最小路径和 5，步骤为：2+3
 * <p>  第一层-> 第二层 ->第三层 ，有三种组合，5+6、5+5、5+7 ，其中5+5，最小因此取5+5， 第一层-> 第二层 ->第三层 的最小路径和 10，步骤为：2+3+5
 * <p>  第一层-> 第二层 ->第三层 ->第四层 ，有四种组合，10+4、10+1、10+8、10+3 ，其中10+1，最小因此取10+1， 第一层-> 第二层 ->第三层->第四层 的最小路径和 11，步骤为：2+3+5+1
 *
 * @author 徐明龙 XuMingLong 2019-09-24
 */
public class NumberTriangleDemo {

    public static void main(String[] args) {
        int[][] data = {
            {2},
            {3,4},
            {6,5,7},
            {4,1,8,3}
        };
        Result minCostResult1 = minimumTotal_1(data);
        System.out.println(minCostResult1);
        Result minCostResult2 = minimumTotal_2(data);
        System.out.println(minCostResult2);
        Result minCostResult3 = minimumTotal_3(data);
        System.out.println(minCostResult3);
        Result minCostResult =minimumTotal(data);
        System.out.println(minCostResult);
    }

    /**
     * 计算最小路径和（常规方法）
     * @author 徐明龙 XuMingLong 2019-09-25
     * @param triangle
     * @return io.github.feiyizhan.NumberTriangleDemo.Result
     */
    public static Result minimumTotal_1(int[][] triangle) {

        if(triangle==null||triangle.length==0){
            return getResult(0,"0");
        }

        //从底往上，把每一行的元素改为其下一行能与之相加的两个数得到的和的最小值
        int row = triangle.length;

        if(row == 1){
            return getResult(triangle[0][0],String.valueOf(triangle[0][0]));
        }
        Result result = getResult(triangle[0][0],String.valueOf(triangle[0][0]));
        for(int i=1;i<row;i++){
            int min = triangle[i][0];
            for(int j=1;j<triangle[i].length;j++){
                min = Math.min(min,triangle[i][j]);
            }
            result = getResult(result.getCost()+min, String.join("+",result.getSteps(),String.valueOf(min)));
        }
        return result;
    }

    /**
     * 计算最小路径和（状态转移方程-递归方法）
     * @author 徐明龙 XuMingLong 2019-09-25
     * @param triangle
     * @return io.github.feiyizhan.NumberTriangleDemo.Result
     */
    public static Result minimumTotal_2(int[][] triangle) {

        if(triangle==null||triangle.length==0){
            return getResult(0,"0");
        }
        int row = triangle.length;
        if(row == 1){
            return getResult(triangle[0][0],String.valueOf(triangle[0][0]));
        }

        Result result = calcMinimumTotal(triangle,0,0);
        return result;
    }

    /**
     * 计算最小路径和
     * <p>状态转移方程:
     * <p> f(i,j) = min(f(i+1,j),f(i+1,j+1)) + Value(i,j)。
     * @author 徐明龙 XuMingLong 2019-09-26
     * @param triangle
     * @param i
     * @param j
     * @return io.github.feiyizhan.NumberTriangleDemo.Result
     */
    private static Result calcMinimumTotal(int[][] triangle,int i,int j){
        if(i==triangle.length-1){
            return getResult(triangle[i][j],String.valueOf(triangle[i][j]));
        }else{
            Result minResult =
                getMinResult(
                    calcMinimumTotal(triangle,i+1,j),
                    calcMinimumTotal(triangle,i+1,j+1)
                );
            return getResult(minResult.getCost()+triangle[i][j],
                String.join("+",String.valueOf(triangle[i][j]),minResult.getSteps()));
        }
    }

    /**
     * 计算最小路径和（动态规划方法）
     * <p>状态转移方程:
     * <p> f(i,j) = min(f(i+1,j),f(i+1,j+1)) + Value(i,j)。
     * @author 徐明龙 XuMingLong 2019-09-25
     * @param triangle
     * @return io.github.feiyizhan.NumberTriangleDemo.Result
     */
    public static Result minimumTotal_3(int[][] triangle) {
        if(triangle==null||triangle.length==0){
            return null;
        }
        int len = triangle.length;
        //用来记录每一步的状态
        Result [][] cost = new Result[len][];
        cost[0] = new Result[1];
        cost[0][0]= getResult(triangle[0][0],String.valueOf(triangle[0][0]));
        for(int i=1; i<len; i++){
            cost[i] = new Result[triangle[i].length];
            Result minResult = cost[i-1][0];
            //获取上一步的最小路径和的结果
            for(int k=0;k<cost[i-1].length;k++){
                minResult = getMinResult(minResult,cost[i-1][k]);
            }
            for(int j=0; j<triangle[i].length; j++){
                cost[i][j]= getResult(minResult.getCost()+triangle[i][j],
                    String.join("+",minResult.getSteps(),String.valueOf(triangle[i][j])));
            }
        }
        Result minCostResult = cost[len-1][0];
        for(int k=0; k<cost[len-1].length; k++){
            minCostResult = getMinResult(minCostResult,cost[len-1][k]);
        }
        return minCostResult;
    }

    /**
     * 计算最小路径和（动态规划方法）
     * @author 徐明龙 XuMingLong 2019-09-25
     * @param triangle
     * @return io.github.feiyizhan.NumberTriangleDemo.Result
     */
    public static Result minimumTotal(int[][] triangle) {
        if(triangle==null||triangle.length==0){
            return getResult(0,"0");
        }
        int len = triangle.length;
        //用来记录每一步的状态
        Result [][] cost = new Result[len][];
        cost[0] = new Result[1];
        cost[0][0]= getResult(triangle[0][0],String.valueOf(triangle[0][0]));

        for(int i=1; i<len; i++){
            cost[i] = new Result[triangle[i].length];
            for(int j=0; j<triangle[i].length; j++){
                //计算上一个状态的时候，防止出现越界问题
                int lower = Math.max(0,j-1);
                int upper = Math.min(j,triangle[i-1].length-1);
                //状态转移方程
                Result minResult = getMinResult(cost[i-1][lower],cost[i-1][upper]);
                int currentCost=minResult.getCost()+triangle[i][j];
                String currentSteps = String.join("+",minResult.steps,String.valueOf(triangle[i][j]));
                cost[i][j]= getResult(currentCost,currentSteps);
            }
        }
        Result minCostResult = getResult(Integer.MAX_VALUE,String.valueOf(Integer.MAX_VALUE));
        for(int k=0; k<triangle[len-1].length; k++){
            minCostResult = getMinResult(minCostResult,cost[len-1][k]);
        }
        return minCostResult;
    }

    /**
     * 取两个结果中的最小结果
     * @author 徐明龙 XuMingLong 2019-09-25
     * @param r1
     * @param r2
     * @return io.github.feiyizhan.NumberTriangleDemo.Result
     */
    private static Result getMinResult(Result r1,Result r2){
        if(Math.min(r1.getCost(),r2.getCost())==r1.cost){
            return r1;
        }else{
            return r2;
        }
    }

    /**
     * 获取一个结果对象
     * @author 徐明龙 XuMingLong 2019-09-25
     * @param cost
     * @param steps
     * @return io.github.feiyizhan.NumberTriangleDemo.Result
     */
    private static Result getResult(int cost,String steps){
        return Result.builder().cost(cost).steps(steps).build();
    }

    /**
     * 结果对象
     * @author 徐明龙 XuMingLong 2019-09-25
     */
    @Data@NoArgsConstructor@AllArgsConstructor@Builder
    static class Result{
        private int cost;
        private String steps;
    }
}
