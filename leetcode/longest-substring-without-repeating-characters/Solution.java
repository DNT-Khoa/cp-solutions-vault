import java.util.HashSet;
import java.util.Set;

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
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int res = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            res = Math.max(right - left + 1, res);
        }

        return res;
    }
}
