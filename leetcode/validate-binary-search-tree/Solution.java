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
 * <p><b>Space complexity:</b> O(h) where h is the height of root
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    private boolean isValid = true;

    public boolean isValidBST(TreeNode root) {
        explore(root, null, null);
        return isValid;    
    }

    void explore(TreeNode node, Integer leftMin, Integer rightMax) {
        if (!isValid) return;

        if (node.left != null) {
            isValid &= node.left.val < node.val;
            if (rightMax != null) isValid &= node.left.val > rightMax;

            explore(node.left, leftMin == null || node.val < leftMin ? node.val : leftMin, rightMax);
        }

        if (node.right != null) {
            isValid &= node.right.val > node.val;
            if (leftMin != null) isValid &= node.right.val < leftMin; 

            explore(node.right, leftMin, rightMax == null || node.val > rightMax ? node.val : rightMax);
        }
    }
}
