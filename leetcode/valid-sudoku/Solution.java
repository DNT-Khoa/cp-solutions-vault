
import java.util.HashSet;
import java.util.Set;

/**
 * <b>Time complexity:</b> O(n * m) where n and m are number of rows and columns of board
 *
 * <p><b>Space complexity:</b> O(max(n, m))
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public boolean isValidSudoku(char[][] board) {
        // check rule 1: each row must contains 1-9 without repetition
        Set<Character> set = new HashSet<>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != '.' && !set.add(board[row][col])) return false;
            }
            set = new HashSet<>();
        }

        // check rule 2: each col must contains 1-9 without repetition
        set = new HashSet<>();
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                if (board[row][col] != '.' && !set.add(board[row][col])) return false;
            }
            set = new HashSet<>();
        }

        // check rule 3: Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                set = new HashSet<>();
                for (int r = row * 3; r < row * 3 + 3; r++) {
                    for (int c = col * 3; c < col * 3 + 3; c++) {
                        if (board[r][c] != '.' && !set.add(board[r][c])) return false;
                    }
                }
            }
        }

        return true;
    }
}
