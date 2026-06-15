/**
 * <b>Time complexity:</b> O(log(n))
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = -1, right = n - 1;

        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (nums[right] == target) return right;
        return -1;
    }
}
