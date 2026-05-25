import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * <b>Time complexity:</b> O(1)
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
        double C = Double.parseDouble(br.readLine());

        // binary search
        double left = 0.0, right = Math.pow(10.0, 5.0);

        for (int i = 0; i < 100; i++) {
            double mid = left + (right - left) / 2;
            if (good(mid, C)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        pw.printf("%.6f%n", right);
        pw.close();
    }

    static boolean good(double x, double C) {
        return x * x + Math.sqrt(x) >= C;
    } 
}
