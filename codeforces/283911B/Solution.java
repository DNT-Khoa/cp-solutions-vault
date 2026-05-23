import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * <b>Time complexity:</b> O(N + K + KLog(N))
 *
 * <p><b>Space complexity:</b> O(N + K)
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

        st = new StringTokenizer(br.readLine());
        int[] targets = new int[K];
        for (int i = 0; i < K; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
        }

        // Loop through each query and do binary search
        for (int target : targets) {
            int left = -1, right = N;
            
            while (right - left > 1) {
                int mid = left + (right - left) / 2;
                if (nums[mid] <= target) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            
            pw.println(left + 1);
        }

        pw.close();
    }
}
