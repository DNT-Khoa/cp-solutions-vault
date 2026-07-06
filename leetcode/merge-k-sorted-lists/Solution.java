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

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Time complexity:</b> O(nlog(k)) where k is the number of link lists and n is the total number of nodes of these linked lists
 *
 * <p><b>Space complexity:</b> O(k)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        while (lists.length > 1) {
            List<ListNode> temp = new ArrayList<>();
            for (int i = 0; i < lists.length; i += 2) {
                ListNode l1 = lists[i];
                ListNode l2 = i + 1 >= lists.length ? null : lists[i + 1];
                ListNode mergedList = mergeTwoSortedList(l1, l2);
                temp.add(mergedList);
            }
            lists = temp.toArray(new ListNode[0]);
        }
        
        return lists[0];
    }

    ListNode mergeTwoSortedList(ListNode listA, ListNode listB) {
        ListNode dummy = new ListNode();
        ListNode head = dummy;

        while (listA != null && listB != null) {
            if (listA.val < listB.val) {
                head.next = listA;
                listA = listA.next;
            } else {
                head.next = listB;
                listB = listB.next;
            }
            head = head.next;
        }

        if (listA != null) head.next = listA;
        if (listB != null) head.next = listB;

        return dummy.next;
    }
}
