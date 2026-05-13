import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // read inputs
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // build the cake grid
        char[][] cake = new char[R][C];
        for (int i = 0; i < R; i++) {
            cake[i] = br.readLine().toCharArray();
        }

        // Create two sets to store rows and cols with evil strawberries
        Set<Integer> devilRows = new HashSet<>();
        Set<Integer> devilCols = new HashSet<>();
        
        // Loop through the cake grid and update the two sets
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (cake[i][j] == 'S') {
                    devilCols.add(j);
                    devilRows.add(i);
                }
            }
        }

        // eaten = total − not-eaten;  not-eaten = bad rows × bad cols
        pw.println(R * C - devilRows.size() * devilCols.size());
        pw.close();
    }
}
