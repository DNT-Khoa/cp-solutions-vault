import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // read inputs
        long num = Long.parseLong(br.readLine());
        
        // brute force
        long res = -1;
        for (long i = 0; i <= num; i++) {
            if (i * i <= num) {
                res = i;
            } else {
                break;
            }
        }

        pw.println(res);
        pw.close();
    }
}
