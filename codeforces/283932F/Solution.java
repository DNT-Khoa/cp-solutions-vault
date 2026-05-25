import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * <b>Time complexity:</b> O(T*log(T))
 *
 * <p><b>Space complexity:</b> O(T)
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
        String T = br.readLine();
        String P = br.readLine();
        int[] deletionOrder = new int[T.length()];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T.length(); i++) {
            deletionOrder[i] = Integer.parseInt(st.nextToken());
        }

        // binary search
        int left = 0, right = T.length();
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (canDelete(mid, deletionOrder, T, P)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        pw.println(left);

        pw.close();
    }

    static boolean canDelete(int numLettersDelete, int[] deletionOrder, String T, String P) {
        boolean[] excludedIndices = new boolean[T.length()];
        for (int i = 0; i < numLettersDelete; i++) {
            excludedIndices[deletionOrder[i] - 1] = true;
        }

        int left = 0;
        for (int i = 0; i < T.length(); i++) {
            if (excludedIndices[i]) continue;
            if (left < P.length() && T.charAt(i) == P.charAt(left)) {
                left++;
            }
        }

        return left == P.length();
    }
}
