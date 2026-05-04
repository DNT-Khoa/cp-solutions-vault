import java.util.*;

public class Solution {

    static void read(Scanner sc, List<Character> list) {
        String line1 = sc.nextLine();
        String line2 = sc.nextLine();

        // store chars from line1
        for (int i = 0; i < 2; i++) {
            char c = line1.charAt(i);
            if (c == 'X')
                continue;
            list.add(c);
        }

        // store chars from line2
        for (int i = 1; i >= 0; i--) {
            char c = line2.charAt(i);
            if (c == 'X')
                continue;
            list.add(c);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Character> bessie = new LinkedList<>();
        List<Character> elsie = new LinkedList<>();

        // read config from bessie
        read(sc, bessie);
        // read from elsie
        read(sc, elsie);

        // keep rotating until first tile of bessie match with first tile of elsie
        while (!elsie.getFirst().equals(bessie.getFirst())) {
            elsie.addFirst(elsie.removeLast());
        }

        // compare and return result
        if (bessie.equals(elsie)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
