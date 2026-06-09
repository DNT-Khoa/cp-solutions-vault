
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> visited = new HashSet<>();
        for (int num : nums) {
            if (!visited.add(num)) {
                return true;
            }
        }

        return false;
    }
}
