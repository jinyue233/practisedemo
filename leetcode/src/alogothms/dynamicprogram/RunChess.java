package alogothms.dynamicprogram;

public class RunChess {
    //题目描述：小兵从左下角往右上角走，只能向上或向右走一步，走法总共有几种？

    /**
     * 方法一：暴力搜索法。。。。。
     * @param m
     * @param n
     * @return
     */
    public static int runChess(int m, int n) {
        //若棋盘都没有，那么走法为0.
        if (m == 0 || n == 0) {
            return 0;
        }
        //若是条形棋盘，那么走法只有一种
        if (m == 1 || n == 1) {
            return 1;
        }
        return runChess(m - 1, n) + runChess(m, n - 1);
    }

    /**
     * 怎么将暴力搜索发转化为带备忘录的自顶向下方法？
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(runChess(2,2));
    }
}
