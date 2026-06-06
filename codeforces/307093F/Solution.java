import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * <b>Time complexity:</b> O(N)
 *
 * <p><b>Space complexity:</b> O(N)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // read inputs
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] nums = new long[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        // solve
        long numOfGoodSegments = 0;
        // use two stack to represent a queue 
        Deque<Item> inStack = new ArrayDeque<>();
        Deque<Item> outStack = new ArrayDeque<>();

        int left = 0;
        for (int right = 0; right < N; right++) {

            inStack.offerFirst(inStack.isEmpty()
                ? new Item(nums[right], nums[right], nums[right]) 
                : new Item(nums[right], Math.min(nums[right], inStack.peek().runningMin), Math.max(nums[right], inStack.peek().runningMax)));

            while (getMax(inStack, outStack) - getMin(inStack, outStack) > K) {
                popLeft(outStack, inStack);
                left++;
            }

            numOfGoodSegments += (long)right - left + 1;
        }

        pw.println(numOfGoodSegments);
        pw.close();
    }

    static void popLeft(Deque<Item> outStack, Deque<Item> inStack) {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                long num = inStack.pop().num;
                outStack.offerFirst(outStack.isEmpty() 
                    ? new Item(num, num, num)
                    : new Item(num, Math.min(num, outStack.peek().runningMin), Math.max(num, outStack.peek().runningMax))
                );   
            }
        }

        outStack.pollFirst();
    }

    static long getMin(Deque<Item> inStack, Deque<Item> outStack) {

        if (inStack.isEmpty()) return outStack.peek().runningMin;
        if (outStack.isEmpty()) return inStack.peek().runningMin;
        return Math.min(inStack.peek().runningMin, outStack.peek().runningMin);
    }

    static long getMax(Deque<Item> inStack, Deque<Item> outStack) {

        if (inStack.isEmpty()) return outStack.peek().runningMax;
        if (outStack.isEmpty()) return inStack.peek().runningMax;
        return Math.max(inStack.peek().runningMax, outStack.peek().runningMax);

    }

    record Item(long num, long runningMin, long runningMax) {}
}
