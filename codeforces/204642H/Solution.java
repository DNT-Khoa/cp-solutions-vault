import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    static record Triple(int a, int b, int c) {}
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // read inputs
        int N = Integer.parseInt(br.readLine());
        int[] farmerCombination = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] locksmithCombination = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // Check from farmer and locksmith combinations and keep track of unique combination
        Set<Triple> uniqueCombinations = new HashSet<>();

        for (int tickA : validTicks(farmerCombination[0], N)) {
            for (int tickB : validTicks(farmerCombination[1], N)) {
                for (int tickC : validTicks(farmerCombination[2], N)) {
                    uniqueCombinations.add(new Triple(tickA, tickB, tickC));
                }
            }
        }

        for (int tickA : validTicks(locksmithCombination[0], N)) {
            for (int tickB : validTicks(locksmithCombination[1], N)) {
                for (int tickC : validTicks(locksmithCombination[2], N)) {
                    uniqueCombinations.add(new Triple(tickA, tickB, tickC));
                }
            }
        }

        // count unique combinations and print
        pw.println(uniqueCombinations.size());
        pw.close();
    }

    static List<Integer> validTicks(int currentTick, int N) {
        List<Integer> valid = new ArrayList<>();
        for (int d = -2; d <= 2; d++) {
            valid.add((currentTick + d + N) % N);
        }

        return valid;
    }
}
