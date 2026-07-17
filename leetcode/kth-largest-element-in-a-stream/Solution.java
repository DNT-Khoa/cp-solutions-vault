
import java.util.PriorityQueue;

/**
 * <b>Time complexity: Check each method
 * <b>Space complexity:</b> Check each method
 *
 * <p>
 * <b>Notes:</b>
 * <ul>
 * <li></li>
 * </ul>
 */
class KthLargest {
    private final PriorityQueue<Integer> pq;
    private final int k;

    // Time complexity: O(nlogk)
    // Space complexity: O(k)
    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k)
                pq.poll();
        }
        this.k = k;
    }

    // Time complexity: O(logk)
    // Space complexity: O(1)
    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k)
            pq.poll();
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
