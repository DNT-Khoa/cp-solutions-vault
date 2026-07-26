
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * <b>Time complexity:</b> O(N*n) where N is the number of tasks
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    record Item(int count, int readyTime) {}
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char c : tasks) {
            count[c - 'A']++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int c : count) {
            if (c > 0) pq.offer(c);
        }
        Deque<Item> cooldownQueue = new ArrayDeque<>();
        int interval = 0;
        while (!pq.isEmpty() || !cooldownQueue.isEmpty()) {
            interval++;

            if (!pq.isEmpty()) {
                int mostFrequentCount = pq.poll();
                mostFrequentCount--;
                if (mostFrequentCount > 0) cooldownQueue.offer(new Item(mostFrequentCount, interval + n));
            }

            if (cooldownQueue.peek() != null && cooldownQueue.peek().readyTime == interval) {
                pq.offer(cooldownQueue.poll().count);
            }
        }

        return interval;
    }
}
