import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // read initial input
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> boxPerColumn = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            boxPerColumn.add(Integer.valueOf(st.nextToken()));
        }

        int M = Collections.max(boxPerColumn);
        // store data into a 2d grid
        int[][] grid = new int[M][N];
        for (int i = 0; i < N; i++) {
            int boxCount = boxPerColumn.get(i);
            for (int j = 0; j < boxCount; j++) {
                grid[j][i] = 1;
            }
        }

        // now we shift all box to the right
        for (int i = 0; i < M; i++) {
            int[] row = grid[i];
            // count number of 1s in the row
            int ones = 0;
            for (int num : row) {
                ones += num;
            }
            // move all ones to the right
            Arrays.fill(row, 0);
            for (int j = N - 1; j >= N - ones; j--) {
                row[j] = 1;
            }
        }

        // Finally count box per row again
        for (int i = 0; i < N; i++) {
            int countPerColumn = 0;
            for (int j = 0; j < M; j++) {
                countPerColumn += grid[j][i];
            }
            pw.print(countPerColumn);
            pw.print(" ");
        }

        pw.close();
    }
}
