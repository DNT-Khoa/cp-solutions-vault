import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * <b>Time complexity:</b> O(NLog(M))
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
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] assistantsWorkRate = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            assistantsWorkRate[i][0] = Integer.parseInt(st.nextToken());
            assistantsWorkRate[i][1] = Integer.parseInt(st.nextToken());
            assistantsWorkRate[i][2] = Integer.parseInt(st.nextToken());
        }

        // binary search
        int left = -1, right = 200 * M;
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (canInflateWithGivenTime(mid, M, assistantsWorkRate)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        // print total time it takes to inflate M balloons
        pw.println(right);

        // now print the optimal balloons inflated by each of the invited assistants
        int remaining = M;
        for (int[] workRate : assistantsWorkRate) {
            int dedicated = Math.min(remaining, ballonInflatedWithGivenWorkRate(right, workRate[0], workRate[1], workRate[2]));
            pw.print(dedicated);
            pw.print(" ");

            remaining -= dedicated;
        }
            
        pw.close();
    }

    static boolean canInflateWithGivenTime(int givenTime, int M, int[][] assistantsWorkRate) {
        int balloonsInflated = 0;
        for (int[] workRate : assistantsWorkRate) {
            balloonsInflated += ballonInflatedWithGivenWorkRate(givenTime, workRate[0], workRate[1], workRate[2]);
        }
        return balloonsInflated >= M;
    }

    static int ballonInflatedWithGivenWorkRate(int givenTime, int T, int Z, int Y) {
        // Let's say to make Z balloons, we have (T * Z) + Y
        int cycle = T * Z + Y;
        int numOfZBalloonsInflated = givenTime / cycle;
        int remainderTime = givenTime % cycle;

        int balloonsInflated = numOfZBalloonsInflated * Z + Math.min(Z, remainderTime / T);
        return balloonsInflated;
    }

}
