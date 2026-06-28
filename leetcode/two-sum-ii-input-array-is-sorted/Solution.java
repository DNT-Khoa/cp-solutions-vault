/**
 * <b>Time complexity:</b> O(n)
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];

        int left = 0, right = numbers.length - 1;
        while (true) {
            int sum = numbers[left] + numbers[right];

            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                res[0] = left + 1;
                res[1] = right + 1;
                break;
            }
        }

        return res;
    }
}
