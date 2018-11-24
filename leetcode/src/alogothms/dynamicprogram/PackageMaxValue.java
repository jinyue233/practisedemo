package alogothms.dynamicprogram;

/**
 * 问题描述
 * 给定N中物品和一个背包。物品i的重量是Wi,其价值位Vi ，背包的容量为C。问应该如何选择装入背包的物品，使得转入背包的物品的总价值为最大？？
 * 在选择物品的时候，对每种物品i只有两种选择，即装入背包或不装入背包。不能讲物品i装入多次，也不能只装入物品的一部分。因此，该问题被称为0-1背包问题。
 *
 * 分析：
 *  (1)V(i,j)=V(i-1,j)  j<wi  
 * （2）V(i,j)=max{V(i-1,j) ,V(i-1,j-wi)+vi) } j>wi
 *
 * (1)式表明：如果第i个物品的重量大于背包的容量，则装人前i个物品得到的最大价值和装入前i-1个物品得到的最大价是相同的，即物品i不能装入背包；
 * 第(2)个式子表明:如果第i个物品的重量小于背包的容量，则会有一下两种情况：(a)如果把第i个物品装入背包，
 * 则背包物品的价值等于第i-1个物品装入容量位j-wi 的背包中的价值加上第i个物品的价值vi; (b)如果第i个物品没有装入背包，
 * 则背包中物品价值就等于把前i-1个物品装入容量为j的背包中所取得的价值。显然，取二者中价值最大的作为把前i个物品装入容量为j的背包中的最优解。
 */
public class PackageMaxValue {
    /**
     * 方法一：暴力搜索法
     * @param value
     * @param weight
     * @param n
     * @param W
     * @return
     */
    public static int maxValue(int[] value, int[] weight, int n, int W) {
        if (W <= 0 || n < 0) {
            return  0;
        }
        //若没有这个判断分支的话，那么虽有前面的if (W <= 0 || n < 0)判断返回0，但是一旦出现这种情况的话，也会执行maxValue(value, weight, n - 1, W - weight[n]) + value[n]的+value[n],从而使得不满足条件。
        if (weight[n] > W) {//当物品中有个重量大于背包总重量，那么则不取该物品，直接跳至前一个物品
            return maxValue(value, weight, n - 1, W);
        } else {//当每个物品重量都小于或等于背包总重量时，那么此时要么取该物品（此时要加上重量，相应的价值也要增加），要么不取该物品，此时返回前面两种方法价值最大值的取法。
            int tmp1 = maxValue(value, weight, n - 1, W);//不取该物品的总价值
            int tmp2 = maxValue(value, weight, n - 1, W - weight[n]) + value[n];//取该物品的总价值
            return Math.max(tmp1, tmp2);//返回总价值最大的取法即可。
        }
    }

    /**
     * 带备忘录的递归法
     * @param value
     * @param weight
     * @param n
     * @param W
     * @return
     */
    public static int maxValue2(int[] value, int[] weight, int n, int W) {
        int[] meno = new int[n];
        for (int i = 0; i < n; i++) {
            meno[i] = -1;
        }
        return  search(value, weight, n - 1, W, meno);
    }

    public static int search(int[] value, int[] weight, int n, int W, int[] meno) {
        if (W <= 0 || n < 0) {
            return  0;
        }
        if (meno[n] >= 0) {
            return meno[n];
        }
        if (weight[n] > W) {
            return maxValue(value, weight, n - 1, W);
        } else {
            int tmp1 = maxValue(value, weight, n - 1, W);
            int tmp2 = maxValue(value, weight, n - 1, W - weight[n]) + value[n];
            return Math.max(tmp1, tmp2);
        }
    }

    /**
     * 自底向上的解法
     * 总结：自底向上的解法一般有以下规律：
     * 1，定义变量res时，长度一般都是n + 1，然后最后返回res[n]。
     * 此时需要注意遍历数组时一般都是从i = 1开始遍历，此时遍历数组取值的时候一般用value[i - 1]来取值。
     *
     * @param value
     * @param weight
     * @param n
     * @param W
     * @return
     */
    public static int maxValue3(int[] value, int[] weight, int n, int W) {
        int[][] res = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (weight[i - 1] > j) {
                    res[i][j] = res[i - 1][j];
                } else {
                    res[i][j] = Math.max(res[i - 1][j], res[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        return res[n][W];
    }

    public static void main(String[] args) {
        int[] value = {100, 4, 5, 6};
        int[] weight = {2, 3, 4, 5};
        int W = 4;
        System.out.println(maxValue2(value, weight, 4, W));
        System.out.println(maxValue3(value, weight, 4, W));
    }
}
