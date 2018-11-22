package alogothms.dynamicprogram;

public class EditDistance {
    /**
     * 暴力搜索法
     * @param word1
     * @param word2
     * @param w1
     * @param w2
     * @return
     */
    public static int minDistance(String word1, String word2, int w1, int w2) {
        if (w1 < 0) {
            if (w2 >= 0){
                return w2 + 1;
            } else {
                return 0;
            }
        }

        if (w2 < 0) {
            if (w1 >= 0){
                return w1 + 1;
            } else {
                return 0;
            }
        }

        if (word1.charAt(w1) == word2.charAt(w2)) {
            return minDistance(word1, word2, w1 - 1, w2 - 1);
        } else {
            return Math.min(minDistance(word1, word2, w1 - 1, w2),
                    Math.min(minDistance(word1, word2, w1 - 1, w2 - 1), minDistance(word1, word2, w1, w2 - 1))) + 1;
        }
    }

    public static int minDistance2(String word1, String word2) {
        int w1 = word1.length();
        int w2 = word2.length();
        int[][] meno = new int[w1 + 1][w2 + 1];
        for (int i = 0; i < w1; i++) {
            for (int j = 0; j < w2; j++) {
                meno[i][j] = -1;
            }
        }
        return minDistanceMeno(word1, word2, w1, w2, meno);
    }

    public static int minDistanceMeno(String word1, String word2, int w1, int w2, int[][] meno) {
        if (w1 < 0) {
            if (w2 >= 0){
                return w2 + 1;
            } else {
                return 0;
            }
        }

        if (w2 < 0) {
            if (w1 >= 0){
                return w1 + 1;
            } else {
                return 0;
            }
        }

        if (meno[w1][w2] >= 0) {
            return meno[w1][w2];
        }
        if (word1.charAt(w1) == word2.charAt(w2)) {
            meno[w1][w2] = minDistanceMeno(word1, word2, w1 - 1, w2 - 1, meno);
            return meno[w1][w2];
        } else {
            meno[w1][w2] = Math.min(minDistanceMeno(word1, word2, w1 - 1, w2, meno),
                    Math.min(minDistanceMeno(word1, word2, w1 - 1, w2 - 1, meno),
                            minDistanceMeno(word1, word2, w1, w2 - 1, meno))) + 1;
            return meno[w1][w2];
        }
    }
    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(minDistance2(word1, word2));

    }


}
