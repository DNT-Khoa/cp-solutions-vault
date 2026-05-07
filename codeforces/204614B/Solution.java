import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // store all magnets to an array in order
        int N = Integer.parseInt(br.readLine());
        
        // loop through magnets and count groups
        String prev = "";
        int numOfGroups = 0;
        for (int i = 0; i < N; i++) {
            String magnet = br.readLine();
            if (!magnet.equals(prev)) {
                numOfGroups++;
                prev = magnet;
            }
        }

        pw.println(numOfGroups);

        pw.close();
    }
}
