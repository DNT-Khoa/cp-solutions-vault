
import java.util.PriorityQueue;

/**
 * <b>Time complexity:</b> O(nlogk)
 *
 * <p><b>Space complexity:</b> O(k)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) pq.poll();
        }

        return pq.peek();
    }
}
