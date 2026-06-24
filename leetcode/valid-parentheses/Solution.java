
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Time complexity:</b> O(N) where N = length of s 
 *
 * <p><b>Space complexity:</b> O(N)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> closeToOpenBrackets = new HashMap<>();
        closeToOpenBrackets.put(')', '(');
        closeToOpenBrackets.put('}', '{');
        closeToOpenBrackets.put(']', '[');

        Deque<Character> dq = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char currentCharacter = s.charAt(i);

            // open brackets
            if (!closeToOpenBrackets.containsKey(currentCharacter)) {
                dq.offerFirst(currentCharacter);
            } else { // closing brackets
                if (dq.isEmpty() || !dq.peek().equals(closeToOpenBrackets.get(currentCharacter))) {
                    return false;
                }
                dq.pollFirst();
            }
        }

        return dq.isEmpty();
    }
}
