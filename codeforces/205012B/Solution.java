import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * <b>Time complexity:</b> O(2^(N+M))
 *
 * <p><b>Space complexity:</b> O(N+M)
 *
 * <p><b>Notes:</b>
    At each position you make a binary choice (player plays / player sits) and recurse on the next position. So:
    Level 0: 1 call (position = 0)
    Level 1: 2 calls
    Level 2: 4 calls
    …
    Level k: 2^k calls
    There are N + M players, so the tree has depth N + M, and the bottom level (the leaves — one per complete lineup) has 
    2 ^ (N + M)
 */
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // read inputs
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] players = new int[N + M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            players[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            players[i + N] = Integer.parseInt(st.nextToken());
        }

        pw.println(countLineups(players, 0, 0, 0, N));
        pw.close();
    }

    static int countLineups(int[] players, int position, int eStrength, int sStrength, int N) {
        if (position == players.length) {
            if (eStrength > 0 && sStrength > 0 && eStrength == sStrength) {
                return 1;
            } 
            return 0;
        }

        int countA = 0;
        int countB = 0;
        int strength = players[position];

        if (position < N) {
            countA = countLineups(players, position + 1, eStrength + strength, sStrength, N);
            countB = countLineups(players, position + 1, eStrength, sStrength, N);
        }
        if (position >= N) {
            countA = countLineups(players, position + 1, eStrength, sStrength + strength, N);
            countB = countLineups(players, position + 1, eStrength, sStrength, N);
        }

        return countA + countB;
    }
}
