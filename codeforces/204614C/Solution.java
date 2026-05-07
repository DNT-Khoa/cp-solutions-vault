import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // readline and parse rows and columns
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // no matter how many intersections there are. If both Akshat and Malvia do optimal move, they would
        // try to remove all rows first if number of rows is less than columns and vice versa so the number of intersections
        // can be reduced as fast possible
        int minMove = Math.min(n, m);

        // Simple observation
        if (minMove % 2 == 0) {
            pw.println("Malvika");
        } else {
            pw.println("Akshat");
        }

        pw.close();
    }
}
