import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * <b>Time complexity:</b> O(Nlog(R)) where R = sum(a[i]) / K
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
        int K = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int[] groups = new int[N];
        for (int i = 0; i < N; i++) {
            groups[i] = Integer.parseInt(br.readLine());
        }

        // binary search
        long left = 1, right = 25 * (long)Math.pow(10, 9) + 1;
        while (right - left > 1) {
            long mid = left + (right - left) / 2;
            if (canForm(mid, groups, K)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        pw.println(left);
        pw.close();
    }

    static boolean canForm(long numOfCouncils, int[] groups, int K) {
        long maximumSeatsCanBeFilled = 0L;
        for (int studentsPerGroup : groups) {
            maximumSeatsCanBeFilled += Math.min(studentsPerGroup, numOfCouncils);
        }
        return maximumSeatsCanBeFilled >= numOfCouncils * K;
    }
}
