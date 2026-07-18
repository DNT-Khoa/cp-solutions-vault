
import java.util.PriorityQueue;

/**
 * <b>Time complexity:</b> O(nlogk) where n is the number of points
 *
 * <p><b>Space complexity:</b> O(k)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> euclidDistance(p2) - euclidDistance(p1));

        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > k) pq.poll();
        }

        int[][] res = new int[k][2];
        int i = 0;
        while (!pq.isEmpty()) {
            res[i++] = pq.poll();
        }
        return res;
    }

    int euclidDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
