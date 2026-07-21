import java.util.ArrayList;
import java.util.List;

/**
 * <b>Time complexity:</b> O(n.2^n)
 *
 * <p><b>Space complexity:</b> O(n)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li>The recursion generates exactly 2^n subsets (each element is either in or out</li>
 *   <li>For each subset we do new ArrayList<>(selected). That's O(subset size) = around O(n)</li>
 * </ul>
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        explore(new ArrayList<>(), 0, nums, res);
        return res;
    }

    void explore(List<Integer> selected, int idx, int[] nums, List<List<Integer>> res) {
        res.add(new ArrayList<>(selected));

        for (int i = idx; i < nums.length; i++) {
            selected.add(nums[i]);
            explore(selected, i + 1, nums, res);
            selected.removeLast();
        }
    }
}
