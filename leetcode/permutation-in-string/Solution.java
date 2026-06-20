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
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) return false;
        int numOfLowercaseChars = 'z' - 'a' + 1;

        int[] s1Counter = new int[numOfLowercaseChars];
        for (int i = 0; i < s1.length(); i++) {
            s1Counter[s1.charAt(i) - 'a']++;
        }

        int[] s2Counter = new int[numOfLowercaseChars];
        int left = 0;
        for (int right = 0; right < s2.length(); right++) {
            s2Counter[s2.charAt(right) - 'a']++;
            if (right - left + 1 > s1.length()) {
                s2Counter[s2.charAt(left) - 'a']--;
                left++;
            }

            if (right - left + 1 == s1.length() && containsPermutation(s1Counter, s2Counter)) {
                return true;
            }
        }

        return false;
    }

    static boolean containsPermutation(int[] s1Counter, int[] s2Counter) {
        for (int i = 0; i < s1Counter.length; i++) {
            if (s1Counter[i] > 0 && s1Counter[i] != s2Counter[i]) {
                return false;
            }
        }

        return true;
    }
}
