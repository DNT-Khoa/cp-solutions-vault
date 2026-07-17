import java.util.Comparator;
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
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stoneWeight : stones) pq.offer(stoneWeight);

        while (pq.size() > 1) {
            // pick the two heaviest stone
            int y = pq.poll();
            int x = pq.poll();
            if (y - x > 0) pq.offer(y - x);
        }

        Integer res = pq.poll();
        return res == null ? 0 : res;
    }
}
