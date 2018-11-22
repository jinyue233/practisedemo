package alogothms.dynamicprogram;

public class HouseRoober {
    /**
     * 方法一：使用暴力搜索（递归）
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        return robRecursion(nums.length - 1, nums);

    }

    public int robRecursion(int n, int[] nums) {
        if (n < 0) {
            return 0;
        }
        return Math.max(nums[n] + robRecursion(n-2, nums), robRecursion(n-1, nums));
    }

    /**
     * 方法二：使用带备忘录的自顶向下的方法
     * 每次都将递归计算的自结果放到一个数组里面，若每次递归时，该子集已经计算出，那么直接返回就是了。
     */
    static int[] meno = null;
    public int rob2(int[] nums) {
        meno = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            meno[i] = -1;
        }
        return robRecursion2(nums.length - 1, nums);

    }

    public int robRecursion2(int n, int[] nums) {
        if (n < 0) {
            return 0;
        }
        if (meno[n] >= 0) {
            return meno[n];
        }
        int result = Math.max(nums[n] + robRecursion2(n-2, nums), robRecursion2(n-1, nums));
        meno[n] = result;
        return result;
    }
    /**
     * 方法二的变种：将备忘录数组放进方法里去递归，注意这里方法逻辑不变
     * 只不过是在原来的方法上声明的时候声明一下这个meno数组参数，调用的时候
     * 传入meno数组就行了，其他逻辑不变。
     * 这个方法跟方法二定义静态变量的方法比对，好像慢了点。
     */
    /*public int rob(int[] nums) {
        int[] meno = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            meno[i] = -1;
        }
        return robRecursion(nums.length - 1, nums, meno);

    }

    public int robRecursion(int n, int[] nums, int[] meno) {
        if (n < 0) {
            return 0;
        }
        if (meno[n] >= 0) {
            return meno[n];
        }
        meno[n] = Math.max(nums[n] + robRecursion(n-2, nums, meno), robRecursion(n-1, nums, meno));
        return meno[n];
    }*/

    /**
     * 方法三：采用传统的自底向上的方法（可由递归转化而来）
     * @param nums
     * @return
     */
    public int rob3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] result = new int[nums.length];
        result[0] = nums[0];
        result[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            result[i] = Math.max(nums[i] + result[i-2], result[i-1]);
        }
        return result[nums.length - 1];
    }



    /**
     * 方法四：Iterative + memo (bottom-up)
     * 也是自底向上求解，带备忘录的自底向上
     * @param nums
     * @return
     */
    public int rob4(int[] nums) {
        if (nums.length == 0) return 0;
        int[] memo = new int[nums.length + 1];
        memo[0] = 0;
        memo[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int val = nums[i];
            memo[i+1] = Math.max(memo[i], memo[i-1] + val);
        }
        return memo[nums.length];
    }




    /**
     * 方法五：由方法四改进而来
     *  Iterative + 2 variables (bottom-up)
     *  We can notice that in the previous step we use only memo[i] and memo[i-1], so going just 2 steps back.
     *  We can hold them in 2 variables instead. This optimization is met in Fibonacci sequence creation
     *  and some other problems [to paste links].
     * @param nums
     * @return
     */
    /* the order is: prev2, prev1, num  */
    public int rob5(int[] nums) {
        if (nums.length == 0) return 0;
        int prev1 = 0;
        int prev2 = 0;
        for (int num : nums) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = tmp;
        }
        return prev1;
    }
}
