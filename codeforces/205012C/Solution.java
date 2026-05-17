import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * <b>Time complexity:</b> O(12!/(3!)^4) &asymp; 3.7&times;10^5, pruned from O(4^12);
 * constant (input is always 12 cows).
 *
 * <p><b>Space complexity:</b> O(12) recursion depth; constant.
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li>Each cow picks a team in fixed cow order (no used[]); the teamCount==3
 *       cap forces 4 teams of 3 and prunes 4^12 to 12!/(3!)^4.</li>
 * </ul>
 */
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // read inputs
        int[] skillLevels = new int[12];
        for (int i = 0; i < 12; i++) {
            skillLevels[i] = Integer.parseInt(br.readLine());
        }

        // run DFS
        pw.println(dfs(0, new int[4], new int[4], skillLevels));
        pw.close();
    }

    static int dfs(int cowId, int[] teamStrengths, int[] teamCount, int[] skillLevels) {
        if (cowId == 12) {
            int minStrength = Arrays.stream(teamStrengths).min().getAsInt();
            int maxStrength = Arrays.stream(teamStrengths).max().getAsInt();
            return maxStrength - minStrength;
        }

        int res = Integer.MAX_VALUE;
        for (int team = 0; team < 4; team++) {
            if (teamCount[team] == 3) {
                continue;
            }
            teamStrengths[team] += skillLevels[cowId];
            teamCount[team]++;
            res = Math.min(res, dfs(cowId + 1, teamStrengths, teamCount, skillLevels));
            teamStrengths[team] -= skillLevels[cowId];
            teamCount[team]--;
        }

        return res;
    }
}
