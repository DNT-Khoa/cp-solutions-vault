import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * <b>Time complexity:</b> O(log(max(W, H) * N))
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
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // binary search
        long left = -1;
        long right = (long)Math.max(W, H) * N + 1;

        while (right - left > 1) {
            long mid = left + (right - left) / 2;
            if (fit(mid, W, H, N)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        pw.println(right);
        pw.close();
    }

    static boolean fit(long side, int W, int H, int N) {
        long cappedWidthCount = Math.min(side / W, N);
        long cappedHeightCount = Math.min(side / H, N);
        return cappedWidthCount * cappedHeightCount >= N;
    }
}
