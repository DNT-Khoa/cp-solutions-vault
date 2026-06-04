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

        // solve with two pointers
        long numOfGoodSegments = 0;
        long currentSum = 0;
        int left = 0;
        for (int right = 0; right < N; right++) {
            currentSum += (long)nums[right];
            
            while (currentSum > S) {
                currentSum -= nums[left];
                left++;
            }

            numOfGoodSegments += (long)right - left + 1;
        }

        pw.println(numOfGoodSegments);

        pw.close();
    }
}
