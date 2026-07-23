import java.util.PriorityQueue;

/**
 * <b>Time complexity:</b> O(nlogn)
 *
 * <p><b>Space complexity:</b> O(n)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int[] numberGame(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) pq.add(num);

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i+=2) {
            res[i + 1] = pq.poll();
            res[i] = pq.poll();
        }
        return res;
    }
}
