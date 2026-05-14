import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // read inputs
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int minimumDifficulty = Integer.MAX_VALUE;
        for (int i = 1; i < N - 1; i++) {
            minimumDifficulty = Math.min(minimumDifficulty, calculateDifficulty(nums, i));
        }

        pw.println(minimumDifficulty);
        pw.close();
    }

    static int calculateDifficulty(int[] nums, int removedHoldIdx) {
        int difficulty = Integer.MIN_VALUE;
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (right == removedHoldIdx) continue;
            difficulty = Math.max(difficulty, nums[right] - nums[left]);
            left++;
            if (left == removedHoldIdx) left++;
        }
        return difficulty;
    }
}
