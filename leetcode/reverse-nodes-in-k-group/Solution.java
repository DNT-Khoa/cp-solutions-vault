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
 * <p><b>Space complexity:</b> O(n/k)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;

        // check upfront if we can reach the next k nodes
        int localCount = 0;
        ListNode temp = head;
        while (localCount < k && temp != null) {
            localCount++;
            temp = temp.next;
        }

        if (localCount == k) {
            ListNode prev = null;
            ListNode firstPrev = null;
            ListNode oldNext = null;

            for (int i = 0; i < k; i++) {
                oldNext = head.next;
                head.next = prev;
                if (prev == null) {
                    firstPrev = head;
                }
                prev = head;

                if (i == k - 1) break;
                head = oldNext;
            }
            
            firstPrev.next = reverseKGroup(oldNext, k);
        }

        return head;
    }

}
