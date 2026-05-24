import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <b>Time complexity:</b> O(N)
 *
 * <p><b>Space complexity:</b> O(N)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li>Regarding time complexity, each possible() calls runs in O(N), which is called 100 times (constant)</li>
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
        List<Integer> ropes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ropes.add(Integer.valueOf(br.readLine()));
        }

        // binary search
        double left = 0, right = Collections.max(ropes) + 1;
        for (int i = 0; i < 100; i++) {
            double mid = left + (right - left) / 2;
            if (possible(mid, ropes, K)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        pw.printf("%.6f%n", left);
        pw.close();
    }

    static boolean possible(double maxLength, List<Integer> ropes, int K) {
        long count = 0;
        for (var rope : ropes) {
            count += (rope / maxLength);            
        }

        return count >= K;
    }
}
