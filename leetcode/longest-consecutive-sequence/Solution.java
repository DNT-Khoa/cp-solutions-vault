import java.util.HashSet;
import java.util.Set;

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
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int res = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) { // if num is the start of a possible consecutive sequence
                int curMaxLength = 0;
                while (set.contains(num)) {
                    curMaxLength++;
                    num++;
                }
                res = Math.max(res, curMaxLength);
            }
        }

        return res;
    }
}
