
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Time complexity:</b> O(n * k) where n is length of strs and k is the max length of each string
 *
 * <p><b>Space complexity:</b> O(n * k)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        int numOfLowercaseLetters = 'z' - 'a' + 1;
        Map<String, List<String>> anMap = new HashMap<>();

        for (String s : strs) {
            int[] lettersCount = new int[numOfLowercaseLetters];
            for (int i = 0; i < s.length(); i++) {
                lettersCount[s.charAt(i) - 'a']++;
            }
            String key = generateKey(lettersCount);
            if (!anMap.containsKey(key)) {
                anMap.put(key, new ArrayList<>());
            }

            anMap.get(key).add(s);
        }

        List<List<String>> ans = new ArrayList<>();
        for (List<String> group : anMap.values()) {
            ans.add(group);
        }

        return ans;
    }

    String generateKey(int[] counter) {
        StringBuilder sb = new StringBuilder();
        for (int count : counter) {
            sb.append(count);
            sb.append("#");
        }

        return sb.toString();
    }
}
