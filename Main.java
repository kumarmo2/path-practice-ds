import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test-01.
        // int[][] mat = { { 1, 2 }, { 3, 4 } };
        // System.out.println(findMaxLength(mat, 2, 3));

        // Test-02.
        // int[][] mat = { { 2, 6, 11 }, { 100, 7, 10 } };
        // System.out.println(findMaxLength(mat, 2, 3));

        // Test -03.
        // int[][] mat = { { 7, 6, 11 } };
        // System.out.println(findMaxLength(mat, 1, 3));

        // Test -04.
        // int[][] mat = { { 1 } };
        // System.out.println(findMaxLength(mat, 1, 1));

        // Test - 05
        int[][] mat = { {} };
        System.out.println(findMaxLength(mat, 0, 0));

    }

    static int findMaxLength(int[][] mat, int m, int n) {
        int maxPath = 0;
        if (mat == null || m == 0 || n == 0) {
            return maxPath;
        }
        int[][][] cache = new int[m][n][1];
        boolean[][][] isVisiting = new boolean[m][n][1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cache[i][j][0] < 1) {
                    recursive(i, j, 0, m, n, mat, isVisiting, cache);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cache[i][j][0] > maxPath) {
                    maxPath = cache[i][j][0];
                }
            }
        }
        /**
         * Debugging purpose.
         */
        // System.out.println("---------printing cache start----------");
        // for (int i = 0; i < m; i++) {
        // for (int j = 0; j < n; j++) {
        // System.out.print(cache[i][j][0] + " ");
        // }
        // System.out.println();
        // }
        // System.out.println("---------printing cache end----------");
        return maxPath;
    }

    static int recursive(int currX, int currY, int prevValue, int m, int n, int[][] mat, boolean[][][] isVisiting,
            int[][][] cache) {
        // System.out.println("X: " + currX + " m: " + m + " Y: " + currY + " n: " + n);
        // TODO: ask for the equality condition as well
        // Currently I have taken it as monotonically increasing only.
        if (currX < 0 || currY < 0 || currX >= m || currY >= n || isVisiting[currX][currY][0]
                || prevValue >= mat[currX][currY]) {
            return 0;
        }
        int cachedValued = cache[currX][currY][0];
        if (cachedValued > 0) {
            return cachedValued;
        }
        isVisiting[currX][currY][0] = true;
        int currValue = mat[currX][currY];
        int leftMax = recursive(currX, currY - 1, currValue, m, n, mat, isVisiting, cache);
        int rightMax = recursive(currX, currY + 1, currValue, m, n, mat, isVisiting, cache);
        int upMax = recursive(currX - 1, currY, currValue, m, n, mat, isVisiting, cache);
        int downMax = recursive(currX + 1, currY, currValue, m, n, mat, isVisiting, cache);
        isVisiting[currX][currY][0] = false;

        // Find some better approach of finding max int from multiple values.
        List<Integer> list = Arrays.asList(leftMax, rightMax, upMax, downMax);
        cache[currX][currY][0] = Collections.max(list) + 1;
        return cache[currX][currY][0];

    }

}