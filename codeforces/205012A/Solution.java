import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * <b>Time complexity:</b> O(N &middot; N!)
 *
 * <p><b>Space complexity:</b> O(N)
 *
 * <p><b>Notes:</b> backtracking, choose / recurse / un-choose.
 * <ul>
 *   <li>Time: N! permutations, O(N) to build/print each.</li>
 *   <li>Space: recursion depth + <code>currentPath</code> + <code>used</code>; output is
 *       streamed, not stored, so it survives N = 10 where accumulating runs out of memory.</li>
 *   <li>Candidates iterated ascending &rarr; output is lexicographically sorted for free.</li>
 *   <li>End on a <code>depth</code> counter, not <code>currentPath.length()</code> &mdash;
 *       length counts chars, breaks at N &ge; 10 (two-digit values).</li>
 *   <li>Undo with <code>setLength(mark)</code>, not <code>deleteCharAt</code> &mdash;
 *       rewinds any number of appended chars.</li>
 * </ul>
 */
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int N = Integer.parseInt(br.readLine());
        boolean[] used = new boolean[N + 1];

        generatePermutations(N, 0, pw, used, new StringBuilder());

        pw.close();
    }

    static void generatePermutations(int N, int depth, PrintWriter pw, boolean[] used, StringBuilder currentPath) {
        if (depth == N) {
            pw.println(currentPath.toString());
            return;
        }

        for (int num = 1; num <= N; num++) {
            if (!used[num]) {
                int currStringLength = currentPath.length();
                used[num] = true;
                currentPath.append(num);
                generatePermutations(N, depth + 1, pw, used, currentPath);
                currentPath.setLength(currStringLength);
                used[num] = false;
            }
        }
    }
}
