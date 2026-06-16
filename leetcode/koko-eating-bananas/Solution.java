/**
 * <b>Time complexity:</b> O(n*log(maxPile))
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = piles[0];
        for (int pile : piles) {
            if (pile > maxPile) maxPile = pile;
        } 

        int left = 0, right = maxPile;
        while (right - left > 1) {
            int mid = left + (right - left) / 2;

            if (canFinish(piles, h, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    static boolean canFinish(int[] piles, int h, int bananaPerHour) {
        int hourTaken = 0;
        for (int pile : piles) {
            hourTaken += Math.ceil((double) pile / bananaPerHour);
        }

        return hourTaken <= h;
    } 
}
