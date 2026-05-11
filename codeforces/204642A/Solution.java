import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int num = Integer.parseInt(br.readLine());
        int next = num + 1;
        while (true) {
            if (containDistinctIntegers(next)) {
                pw.println(next);
                break;
            }
            next++;
        }

        pw.close();
    }

    static boolean containDistinctIntegers(int num) {
        Set<Integer> set = new HashSet<>();
        while (num > 0) {
            if (!set.add(num % 10)) {
                return false;
            }
            num /= 10;
        }

        return true;
    }
}
