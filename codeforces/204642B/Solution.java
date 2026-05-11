import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // read N and integers
        int N = Integer.parseInt(br.readLine());
        int[] integers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            integers[i] = Integer.parseInt(st.nextToken());
        }

        // brute force
        int maxOnes = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                maxOnes = Math.max(maxOnes, countOnes(integers, i, j));
            }
        }
        pw.println(maxOnes);

        pw.close();
    }

    static int countOnes(int[] integers, int start, int end) {
        int count = 0;
        for (int i = 0; i < integers.length; i++) {
            if (i >= start && i <= end) {
                if (integers[i] == 0) {
                    count++;
                }
            } else {
                if (integers[i] == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}
