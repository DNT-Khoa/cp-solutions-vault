import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * <b>Time complexity:</b> O(N + KLog(N))
 *
 * <p><b>Space complexity:</b> O(N + K)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li>O(N) read + K binary searches at O(Log(N)) each = O(N + KLog(N)).</li>
 *   <li>Input is already sorted (non-decreasing), so no sort needed.</li>
 *   <li>Loop ends when left &gt; right, meaning the value is absent.</li>
 *   <li>mid = left + (right - left) / 2 avoids index overflow.</li>
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
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int[] queries = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            queries[i] = Integer.parseInt(st.nextToken());
        }

        // loop through each query and do binary search
        for (int query : queries) {

            int left = 0, right = N - 1;
            boolean found = false;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < query) {
                    left = mid + 1;
                } else if (nums[mid] > query) {
                    right = mid - 1;
                } else {
                    found = true;
                    break;
                }
            }
            
            if (found) {
                pw.println("YES");
            } else {
                pw.println("NO");
            }
        }

        pw.close();
    }
}
