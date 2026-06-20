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
    public int characterReplacement(String s, int k) {
        int[] countPerChar = new int['Z' - 'A' + 1];

        int res = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            countPerChar[s.charAt(right) - 'A']++;

            while (!validRange(countPerChar, right - left + 1, k)) {
                countPerChar[s.charAt(left) - 'A']--;
                left++;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    static boolean validRange(int[] countPerChar, int rangeSize, int k) {
        int charWithMaxOccurenceCount = 0;
        for (int count : countPerChar) {
            if (count > 0) {
                charWithMaxOccurenceCount = Math.max(count, charWithMaxOccurenceCount);
            }
        }

        return rangeSize - charWithMaxOccurenceCount <= k;
    }
}
