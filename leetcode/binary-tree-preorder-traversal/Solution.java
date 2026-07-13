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

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Time complexity:</b> O(n) where n is the number of nodes in root
 *
 * <p><b>Space complexity:</b> O(h) where h is the height of root
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traverse(root, res);
        return res;
    }

    void traverse(TreeNode node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);
        traverse(node.left, res);
        traverse(node.right, res);
    }
}
