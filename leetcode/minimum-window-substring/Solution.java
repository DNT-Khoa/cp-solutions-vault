/**
 * <b>Time complexity:</b> O(m + n) where m = length of s and n = length of t
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public String minWindow(String s, String t) {
        int uppercaseAndLowercaseCount = 'z' - 'A' + 1; // A-Za-z

        // count occurences of characters in t
        int[] tCount = new int[uppercaseAndLowercaseCount];
        for (int i = 0; i < t.length(); i++) {
            tCount['z' - t.charAt(i)]++;
        }

        int[] sCount = new int[uppercaseAndLowercaseCount];
        int left = 0;
        int sizeOfMinimumString = Integer.MAX_VALUE;
        int minLeft = 0, minRight = 0; // keep track of the left and right pointer of the minimum substring

        for (int right = 0; right < s.length(); right++) {
            sCount['z' - s.charAt(right)]++;
            while (included(tCount, sCount)) {
                if (right - left + 1 < sizeOfMinimumString) {
                    sizeOfMinimumString = right - left + 1;
                    minLeft = left;
                    minRight = right + 1; // [left, right) for s.substring(left, right) in java
                }
                sCount['z' - s.charAt(left)]--;
                left++;
            }
        }

        return s.substring(minLeft, minRight);
    }

    static boolean included(int[] tCount, int[] sCount) {
        for (int i = 0; i < tCount.length; i++) {
            if (tCount[i] > 0 && sCount[i] < tCount[i]) {
                return false;
            }
        }

        return true;
    }
}
