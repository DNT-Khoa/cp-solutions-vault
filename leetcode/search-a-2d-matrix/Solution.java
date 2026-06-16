/**
 * <b>Time complexity:</b> O(log(m * n))
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = -1, right = m * n;

        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (matrix[mid / n][mid % n] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right >= 0 && right < m * n && matrix[right / n][right % n] == target;
    }
}
