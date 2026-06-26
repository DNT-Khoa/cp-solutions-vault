
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Time complexity:</b> O(n)
 *
 * <p>
 * <b>Space complexity:</b> O(n)
 *
 * <p>
 * <b>Notes:</b>
 * <ul>
 * <li></li>
 * </ul>
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countByNum = new HashMap<>();

        for (int num : nums) {
            countByNum.put(num, countByNum.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucketList = new ArrayList[nums.length + 1];
        for (Integer num : countByNum.keySet()) {
            int count = countByNum.get(num);
            if (bucketList[count] == null) {
                bucketList[count] = new ArrayList<>();
            }

            bucketList[count].add(num);
        }

        int[] res = new int[k];
        int idx = 0;

        for (int i = bucketList.length - 1; i >= 0; i--) {
            if (bucketList[i] != null) {
                for (int num : bucketList[i]) {
                    res[idx] = num;
                    idx++;
                    if (idx == k) return res;
                }
            }
        }

        return res;
    }
}
