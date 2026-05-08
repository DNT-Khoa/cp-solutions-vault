import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // read N and M
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // read M puzzles
        st = new StringTokenizer(br.readLine());
        List<Integer> puzzles = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            puzzles.add(Integer.valueOf(st.nextToken()));
        }

        // sort the puzzle so we can minimize the difference
        Collections.sort(puzzles);

        // keep a window of size N and loop through the puzzle list
        int leastPossibleDifference = 2000;
        for (int i = 0; i + N - 1 < M; i++) {
            leastPossibleDifference = Math.min(leastPossibleDifference, puzzles.get(i + N - 1) - puzzles.get(i));
        }
        
    
        pw.print(leastPossibleDifference);
        pw.close();
    }
}
