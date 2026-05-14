import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // read inputs
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] possibleLengths = new int[3];
        for (int i = 0; i < 3; i++) {
            possibleLengths[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(possibleLengths);
        
        pw.println(cutRibbon(possibleLengths, 0, 0, 0, N));
        pw.close();
    }

    static int cutRibbon(int[] possibleLengths, int size, int currentIndex, int sum, int N) {
        if (sum > N) return 0;
        if (sum == N) return size;
        
        int maxSize = 0;
        for (int i = currentIndex; i < 3; i++) {
            int currSize = cutRibbon(possibleLengths, size + 1, i, sum + possibleLengths[i], N);
            maxSize = Math.max(maxSize, currSize);
        }

        return maxSize;
    }
}
