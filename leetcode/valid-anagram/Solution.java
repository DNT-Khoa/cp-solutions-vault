/**
 * <b>Time complexity:</b> O(n + m) where n and m are length of s and t respectively
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public boolean isAnagram(String s, String t) {
        int numOfLowercaseLetters = 'z' - 'a' + 1;
        
        int[] sCount = new int[numOfLowercaseLetters];
        for (int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
        }

        for (int j = 0; j < t.length(); j++) {
            sCount[t.charAt(j) - 'a']--;
        }

        for (int count : sCount) {
            if (count != 0) return false;
        }
        return true;
    }
}
