package alogothms.dynamicprogram;

import java.util.Arrays;

public class NFactorial {
    /**
     * 方法一：暴力搜索法
     * @param n
     * @return
     */
    public static int nFactorial (int n) {
        if (n == 0) {
            return 1;
        }
        return n * nFactorial(n - 1);
    }

    /**
     * 方法二：带备忘录的暴力搜索
     */
    static int[] meno;
    public static int nFactorial2(int n) {
        meno = new int[n + 1];
        Arrays.fill(meno, -1);
        return factorial2(n);
    }
    public static int factorial2(int n) {
        if (n == 0) {
            return 1;
        }
        if (meno[n] >= 0) {
            return meno[n];
        }
        int result = n * factorial2(n - 1);
        meno[n] = result;
        return result;
    }

    /**
     * 方法三：自底向上的方法
     * @param n
     * @return
     */
    public static int nFactorial3(int n) {
        int[] result  = new int[n + 1];
        result[0] = 1;
        for (int i = 1; i <= n; i++) {
            result[i] = i * result[i - 1];
        }
        return result[n];
    }
    public static void main(String[] args) {
        System.out.println(nFactorial3(10));
    }
}
