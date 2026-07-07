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
 * <b>Time complexity:</b> O(n)
 *
 * <p><b>Space complexity:</b> O(h) where h is the height of the tree
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode left = root.left, right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }
}
