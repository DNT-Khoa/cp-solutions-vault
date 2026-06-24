
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <b>Time complexity:</b> O(N)
 *
 * <p><b>Space complexity:</b> O(N)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int rightNum = stack.pollFirst();
                int leftNum = stack.pollFirst();

                switch (token) {
                    case "+" -> stack.offerFirst(rightNum + leftNum);
                    case "-" -> stack.offerFirst(leftNum - rightNum);
                    case "*" -> stack.offerFirst(leftNum * rightNum);
                    default -> stack.offerFirst(leftNum / rightNum);
                }
            } else {
                stack.offerFirst(Integer.valueOf(token));
            }
        }

        return stack.pollFirst();
    }
}
