import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * <b>Time complexity:</b> O(Log(N))
 *
 * <p><b>Space complexity:</b> O(1)
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
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        // binary search
        int left = 0, right = N * 5 + 10;
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (possible(mid, N, X, Y)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        pw.println(right);
        pw.close();
    }

    static boolean possible(int givenTime, int N, int X, int Y) {
        int timeForFirstCopy = Math.min(X, Y);
        int copiesCount;
        if (givenTime < timeForFirstCopy) {
            copiesCount = 0;
        } else {
            givenTime -= timeForFirstCopy;
            copiesCount = 1 + givenTime / X + givenTime / Y;
        }

        return copiesCount >= N;
    }
}
