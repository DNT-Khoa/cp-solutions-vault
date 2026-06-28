import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>Time complexity:</b> O(n^2)
 *
 * <p><b>Space complexity:</b> O(log(n))
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li>Arrays.sort(int[]) in Java uses Dual-Pivot Quicksort so space complexity for it is O(log(n))</li>
 * </ul>
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i <= nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = 0 - nums[i];
            twoSum(nums, i, i + 1, target, res);
        }

        return res;
    }

    void twoSum(int[] nums, int i, int start, int target, List<List<Integer>> res) {
        int j = start, k = nums.length - 1;
        while (j < k) {
            if (j > start && nums[j] == nums[j - 1]) {
                j++;
                continue;
            }
            
            int sum = nums[j] + nums[k];
            if (sum < target) {
                j++;
                continue;
            }

            if (sum > target) {
                k--;
                continue;
            } 

            res.add(List.of(nums[i], nums[j], nums[k]));
            j++;
            k--;
        }
    }
}
