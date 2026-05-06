import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalCandle = sc.nextInt();
        int wentOutCandleGroup = sc.nextInt();

        int count = totalCandle;
        while (totalCandle > 0) {
            int remained = totalCandle <= wentOutCandleGroup ? 0 : totalCandle % wentOutCandleGroup;
            totalCandle /= wentOutCandleGroup;
            count += totalCandle;
            totalCandle += remained;
        } 

        System.out.println(count);
    }
}
