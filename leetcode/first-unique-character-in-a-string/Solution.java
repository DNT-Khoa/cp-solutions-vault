/**
 * <b>Time complexity:</b> O(n)
 *
 * <p><b>Space complexity:</b> O(n)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (counter.get(s.charAt(i)) == 1) return i;
        }
        
        return -1;
    }
}
