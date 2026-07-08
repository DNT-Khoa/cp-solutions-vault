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
    private boolean balanced = true;

    public boolean isBalanced(TreeNode root) {
        height(root);
        return this.balanced;
    }

    int height(TreeNode node) {
        if (node == null) return 0;
        
        int leftHeight = 0, rightHeight = 0;
        if (node.left != null) {
            leftHeight = height(node.left) + 1;
        }
        if (node.right != null) {
            rightHeight = height(node.right) + 1;
        }

        balanced &= (Math.abs(leftHeight - rightHeight) <= 1);
        return Math.max(leftHeight, rightHeight);
    }
}
