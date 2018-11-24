package alogothms.dynamicprogram;

public class CoinChange {
    static int maxValue = Integer.MAX_VALUE - 1;
    public static int coinChange(int[] coins, int amount) {
        int result = search(coins, amount, coins.length - 1);
        if (result < maxValue) {
            return result;
        }
        return -1;
    }

    public static int search(int[] coins, int amount, int idx) {
        if (amount == 0) {
            return 0;
        }
        if (idx < 0 || amount < 0) {
            return maxValue;
        }
        return Math.min(search(coins, amount, idx - 1),
                search(coins, amount - coins[idx], idx) + 1);
    }

    public static int coinChange2(int[] coins, int amount) {
        int[][] result = new int[amount + 1][coins.length + 1];
        for (int i = 0; i <= amount; i++) {
            for (int j = 1; j <= coins.length; j++) {
                if (i >= coins[j - 1] && (i - coins[j - 1] < coins.length)) {
                    result[i][j] = Math.min(result[i][j - 1], result[i - coins[j - 1]][j] + 1);
                } else {
                    result[i][j] = -1;
                }

            }
        }
        return  result[amount][coins.length];
    }
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange2(coins, amount));
        System.out.println(Integer.MAX_VALUE);
    }
}
