
import java.util.HashMap;
import java.util.Map;

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

/**
 * <b>Time complexity:</b> O(n)
 *
 * <p><b>Space complexity:</b> O(n)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> originalToClone = new HashMap<>();
        cloneIfNotExist(head, originalToClone);

        Node node = head;
        while (node != null) {
            cloneIfNotExist(node, originalToClone);
            cloneIfNotExist(node.next, originalToClone);
            cloneIfNotExist(node.random, originalToClone);

            Node clonedNode = originalToClone.get(node);
            clonedNode.next = originalToClone.get(node.next);
            clonedNode.random = originalToClone.get(node.random);

            node = node.next;
        }

        return originalToClone.get(head);
    }

    void cloneIfNotExist(Node original, Map<Node, Node> originalToClone) {
        if (original == null) return;
        if (!originalToClone.containsKey(original)) {
            originalToClone.put(original, new Node(original.val));
        }
    }
}
