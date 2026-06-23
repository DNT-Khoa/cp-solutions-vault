import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <b>Time complexity:</b> O(N) amortized
 *
 * <p><b>Space complexity:</b> O(K)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();

        int[] res = new int[nums.length - k + 1];
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[right]) {
                dq.pollLast();
            }
            dq.offer(right);

            if (right - left + 1 > k) {
                if (dq.peek() == left) {
                    dq.poll();
                }
                left++;
            }

            if (right - left + 1 == k) {
                res[left] = nums[dq.peek()];
            }
        }

        return res;
    }
}
