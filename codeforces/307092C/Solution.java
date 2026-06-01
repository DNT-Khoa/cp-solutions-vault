import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * <b>Time complexity:</b> O(N + M)
 *
 * <p><b>Space complexity:</b> O(N + M)
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
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] B = new int[M];
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // two pointers
        int left = 0, right = 0;
        long numOfPairs = 0;
        while (left < N && right < M) {
            if (A[left] < B[right]) {
                left++;
            } else if (B[right] < A[left]) {
                right++;
            } else {
                int val = A[left];
                int countA = 0;
                while (left < N && A[left] == val) {
                    countA++;
                    left++;
                }
                int countB = 0;
                while (right < M && B[right] == val) {
                    countB++;
                    right++;
                }
                numOfPairs +=  (long)countA * countB;
            }
        }
        pw.println(numOfPairs);
        pw.close();
    }
}
