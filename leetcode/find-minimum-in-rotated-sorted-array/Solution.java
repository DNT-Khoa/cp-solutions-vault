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
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = -1, right = n - 1;
        
        while (right - left > 1) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= nums[n - 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return nums[right];
    }
}
