/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 * <b>Time complexity:</b> O(n)
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public void reorderList(ListNode head) {
        ListNode left = head, right = head;

        // find the middle point of linked list
        while (right.next != null && right.next.next != null) {
            left = left.next;
            right = right.next.next;
        }

        // rotate the second half of linked list
        ListNode mid = left.next;
        left.next = null; // unlink the two sub lists
        ListNode prev = null;
        while (mid != null) {
            ListNode next = mid.next;
            mid.next = prev;
            prev = mid;
            mid = next;
        }

        // now link nodes as requested
        left = head;
        right = prev;
        int turn = 0;

        while (left != null && right != null) {
            if (turn == 0) {
                ListNode next = left.next;
                left.next = right;
                left = next;
            } else {
                ListNode next = right.next;
                right.next = left;
                right = next;
            }
            turn = (turn + 1) % 2;
        }
    }
}
