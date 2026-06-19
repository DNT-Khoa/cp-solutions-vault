/**
 * <b>Time complexity:</b> O(N)
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int maxProfit(int[] prices) {
        int left = 0, maxProfit = 0;
        for (int right = 0; right < prices.length; right++) {
            while (prices[left] > prices[right]) {
                left++;
            }
            maxProfit = Math.max(prices[right] - prices[left], maxProfit);
        }

        return maxProfit;
    }
}
