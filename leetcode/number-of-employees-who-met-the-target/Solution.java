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
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int res = 0;
        for (int hour : hours) {
            if (hour >= target) res++;
        }

        return res;
    }
}
