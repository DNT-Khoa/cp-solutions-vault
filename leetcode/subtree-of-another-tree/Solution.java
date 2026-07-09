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
 * <b>Time complexity:</b> O(n.m) where n is the number of nodes in root and m is number of nodes in subRoot
 *
 * <p><b>Space complexity:</b> O(h_root + h_subRoot) where h_root is the height of root and h_subRoot is the height of subRoot
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;

        return sameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean sameTree(TreeNode node1, TreeNode node2) {
        return (node1 == null && node2 == null) 
        || (node1 != null && node2 != null && node1.val == node2.val && sameTree(node1.left, node2.left) && sameTree(node1.right, node2.right));
    }
}
