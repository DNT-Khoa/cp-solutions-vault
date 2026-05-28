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

        int[] mergedArray = new int[N + M];
        int i = 0, j = 0;
        while (i < N || j < M) {
            if (j == M || i < N && A[i] <= B[j]) {
                mergedArray[i + j] = A[i];
                i++;
            } else {
                mergedArray[i + j] = B[j];
                j++;
            }
        }

        for (int num : mergedArray) {
            pw.print(num);
            pw.print(" ");
        }

        pw.close();
    }
}
