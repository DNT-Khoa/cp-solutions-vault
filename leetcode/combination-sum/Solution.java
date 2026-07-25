
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Time complexity:</b> O(n^(t/m + 1)) where n = candidates.length, t = target, m = min(candidates)
 *
 * <p><b>Space complexity:</b> O(t/m)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        explore(candidates, target, 0, new ArrayList<>(), 0, result);
        return result;
    }

    void explore(int[] candidates, int target, int startIdx, List<Integer> selected, int sum, List<List<Integer>> result) {
        if (sum > target) return;
        if (sum == target) {
            result.add(new ArrayList<>(selected));
            return;
        } 
        for (int i = startIdx; i < candidates.length; i++) {
            selected.add(candidates[i]);
            explore(candidates, target, i, selected, sum + candidates[i], result);
            selected.remove(selected.size() - 1);
        }
    }
}
