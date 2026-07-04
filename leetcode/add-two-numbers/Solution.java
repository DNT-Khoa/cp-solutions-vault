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
 * <b>Time complexity:</b> O(n + m) where n and m are length of l1 and l2
 *
 * <p><b>Space complexity:</b> O(n)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode dummy = new ListNode();
        ListNode head = new ListNode();
        dummy.next = head;

        int carriedOver = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carriedOver;

            if (sum >= 10) {
                sum -= 10;
                carriedOver = 1;
            } else {
                carriedOver = 0;
            }

            head.next = new ListNode(sum);
            head = head.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int sum = l1.val + carriedOver;
            
            if (sum >= 10) {
                sum -= 10;
                carriedOver = 1;
            } else {
                carriedOver = 0;
            }
            head.next = new ListNode(sum);
            head = head.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int sum = l2.val + carriedOver;
            
            if (sum >= 10) {
                sum -= 10;
                carriedOver = 1;
            } else {
                carriedOver = 0;
            }
            head.next = new ListNode(sum);
            head = head.next;
            l2 = l2.next;
        }

        if (carriedOver == 1) {
            head.next = new ListNode(1);
        }

        return dummy.next.next;
    }
}
