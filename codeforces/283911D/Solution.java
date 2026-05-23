import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <b>Time complexity:</b> O(N + NLog(N) + KLog(N))
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
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int K = Integer.parseInt(br.readLine());

        // First sort nums
        Arrays.sort(nums);

        // Do two binary search
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            int firstIndexAtLeast = findFirstIndexAtLeast(nums, left);
            int lastIndexAtMost = findLastIndexAtMost(nums, right);

            pw.print(lastIndexAtMost - firstIndexAtLeast + 1);
            pw.print(" ");
        }

        pw.close();
    }

    static int findFirstIndexAtLeast(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    static int findLastIndexAtMost(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
