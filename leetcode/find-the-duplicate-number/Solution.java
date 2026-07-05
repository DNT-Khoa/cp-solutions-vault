/**
 * <b>Time complexity:</b> O(n)
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;

        // First find the meeting point of fast and slow pointer
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];

            if (slow == fast) break;
        }

        // move fast back to index 0 and keep moving both pointers 1 step at a time until they meet
        fast = 0;
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return fast;
    }
}
