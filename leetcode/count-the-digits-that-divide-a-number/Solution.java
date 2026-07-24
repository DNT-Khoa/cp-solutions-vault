/**
 * <b>Time complexity:</b> O(log(n)) where n is the input number
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li>A number num has [log10(num)] + 1 digits</li>
 * </ul>
 */
class Solution {
    public int countDigits(int num) {
        int original = num;
        int count = 0;
        while (num > 0) {
            int digit = num % 10;
            if (original % digit == 0) count++;
            num /= 10;
        }
        return count;
    }
}
