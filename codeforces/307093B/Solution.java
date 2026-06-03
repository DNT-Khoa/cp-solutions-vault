import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        long S = Long.parseLong(st.nextToken());
        
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        long currSum = 0;
        int shortestGoodSegmentLength = Integer.MAX_VALUE;
        int left = 0;
        for (int right = 0; right < N; right++) {
            currSum += (long)nums[right];

            while (currSum >= S) {
                shortestGoodSegmentLength = Math.min(shortestGoodSegmentLength, right - left + 1);
                currSum -= nums[left];
                left++;
            }   
        }
        
        if (shortestGoodSegmentLength == Integer.MAX_VALUE) {
            pw.println(-1);
        } else {
            pw.println(shortestGoodSegmentLength);
        }

        pw.close();
    }
}
