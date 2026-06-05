import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <b>Time complexity:</b> O(N)
 *
 * <p><b>Space complexity:</b> O(N)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // read inputs
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // two pointers
        long res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0;
        for (int right = 0; right < N; right++) {
            count.put(nums[right], count.getOrDefault(nums[right], 0) + 1);

            while (count.size() > K) {
                count.put(nums[left], count.getOrDefault(nums[left], 0) - 1);
                if (count.get(nums[left]) == 0) count.remove(nums[left]);
                left++;
            }

            res += (long)right - left + 1;
        }

        pw.println(res);
        pw.close();
    }
}
