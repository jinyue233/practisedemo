package alogothms;

import java.util.Arrays;

public class Fib {
    /**
     * 方法一：暴力搜索法
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n < 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 方法二：自顶向下带备忘录
     * @param n
     * @return
     */
    static int[] meno;
    public static int fib2(int n) {
        meno = new int[n + 1];
        Arrays.fill(meno,-1);
        return fibRecursion(n);
    }
    public static int fibRecursion(int n) {
        if (n < 2) {
            return 1;
        }
        if (meno[n] >= 0) {
            return meno[n];
        }
        int result = fibRecursion(n - 1) + fibRecursion(n - 2);
        meno[n] = result;
        return result;
    }

    /**
     * 方法三：自底向上
     * @param n
     * @return
     */
    public static int fib3(int n) {
        if (n < 2) {
            return 1;
        }
        int[] result = new int[n+1];
        result[0] = result[1] =1;
        for (int i = 2; i <= n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n];
    }

    public static void main(String[] args) {
        System.out.println(fib2(10000));
    }
}
