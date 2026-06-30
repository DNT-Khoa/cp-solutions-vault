

/**
 * <b>Time complexity:</b> O(n)
 *
 * <p><b>Space complexity:</b> O(n)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        // find max left
        int maxLeft = 0;
        int[] prefixMax = new int[n];
        for (int i = 0; i < n; i++) {
            prefixMax[i] = maxLeft;
            if (height[i] > maxLeft) {
                maxLeft = height[i];
            }
        }

        // find max right
        int maxRight = 0;
        int[] suffixMax = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            suffixMax[i] = maxRight;
            if (height[i] > maxRight) {
                maxRight = height[i];
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.max(0, Math.min(prefixMax[i], suffixMax[i]) - height[i]);
        }

        return res;
    }
}
