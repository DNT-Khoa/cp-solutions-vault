import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // read inputs
        String fullString = br.readLine();
        StringBuilder charsSB = new StringBuilder(fullString);

        // brute force
        String palindrome = null;
        outer:
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i <= fullString.length(); i++) {
                charsSB.insert(i, c);
                if (isPalindrome(charsSB)) {
                    palindrome = charsSB.toString();
                    break outer;
                }
                charsSB.deleteCharAt(i);
            }
        }

        if (palindrome != null) {
            pw.println(charsSB);
        } else {
            pw.println("NA");
        }
        pw.close();
    }

    static boolean isPalindrome(StringBuilder sb) {
        int left = 0; 
        int right = sb.length() - 1;
        while (left < right) {
            if (sb.charAt(left) != sb.charAt(right)) return false;
            left++;
            right--;
        }

        return true;
    }
}
