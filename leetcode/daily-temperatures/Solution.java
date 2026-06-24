import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <b>Time complexity:</b> O(N) amortized
 *
 * <p><b>Space complexity:</b> O(N)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int colderTemperatureIdx = stack.pop();
                res[colderTemperatureIdx] = i - colderTemperatureIdx;
            }
            stack.offerFirst(i);
        }

        return res;
    }
}
