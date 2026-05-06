import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);   
        
        // read input
        int N = Integer.parseInt(br.readLine());
        List<Integer> moneyList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            moneyList.add(Integer.valueOf(st.nextToken()));
        }

        // Use two pointers to calculate longest window size
        int left = 0, maxSubsegment = 0;
        for (int right = 0; right < N; right++) {
            int rightVal = moneyList.get(right);
            if (right > 0 && rightVal < moneyList.get(right - 1)) {
                left = right;
            }

            maxSubsegment = Math.max(maxSubsegment, right - left + 1);
        }
        
        pw.println(maxSubsegment);
        pw.close();
    }
}
