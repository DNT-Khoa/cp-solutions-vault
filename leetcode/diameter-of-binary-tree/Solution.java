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
    int diameter = 0;

    // furthest you can go in the left + furthest you can go to the right
    public int diameterOfBinaryTree(TreeNode root) {
        explore(root);
        return this.diameter;
    }

    public int explore(TreeNode node) {
        
        int maxLeft = 0, maxRight = 0;
        
        if (node.left != null) {
            maxLeft = explore(node.left) + 1;
        }

        if (node.right != null) {
            maxRight = explore(node.right) + 1;
        }

        this.diameter = Math.max(diameter, maxLeft + maxRight);

        return Math.max(maxLeft, maxRight);
    }

    
}
