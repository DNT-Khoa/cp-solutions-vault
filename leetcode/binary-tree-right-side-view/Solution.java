
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/**
 * <b>Time complexity:</b> O(n) where n is the number of nodes in root
 *
 * <p><b>Space complexity:</b> O(n)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.add(root);
        while (!dq.isEmpty()) {
            int levelSize = dq.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = dq.poll();
                if (i == 0) res.add(node.val);

                if (node.right != null) dq.offer(node.right);
                if (node.left != null) dq.offer(node.left);
            }
        }

        return res;
    }
}
