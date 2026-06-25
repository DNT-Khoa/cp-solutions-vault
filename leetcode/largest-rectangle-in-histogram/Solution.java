
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
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerFirst(-1);

        int res = 0;
        for (int i = 0; i <= heights.length; i++) {
            int height = i == heights.length ? 0 : heights[i];
            while (stack.peek() != -1 && height < heights[stack.peek()]) {
                int barIdx = stack.pollFirst();
                int width = i - stack.peek() - 1;
                res = Math.max(res, heights[barIdx] * width);
            }
            stack.offerFirst(i);
        }

        return res;
    }
}
