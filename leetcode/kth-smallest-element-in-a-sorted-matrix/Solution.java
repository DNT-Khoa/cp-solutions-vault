/**
 * <b>Time complexity:</b> O(log(max - min) * (n + m))
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0] - 1;
        int right = matrix[n - 1][n - 1];

        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (largerThanOrEqualKAtNum(matrix, k, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    static boolean largerThanOrEqualKAtNum(int[][] matrix, int k, int num) {
        int n = matrix.length;
        int row = n - 1;
        int col = 0;
        int count = 0;

        while (row >= 0 && col < n) {
            if (matrix[row][col] <= num) {
                count += row + 1;
                col++;
            } else {
                row--;
            }
        }

        return count >= k;
    }
}
