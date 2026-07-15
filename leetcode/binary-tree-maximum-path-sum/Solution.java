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
 * <b>Time complexity:</b> O(n) where n is the number of nodes in tree
 *
 * <p><b>Space complexity:</b> O(h) where h is the height of root
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    private int res;

    public int maxPathSum(TreeNode root) {
        res = root.val;
        explore(root);
        return res;
    }

    int explore(TreeNode node) {
        if (node == null) return 0;

        int leftMax = explore(node.left);
        int rightMax = explore(node.right);

        int sumLeftPath = node.val + leftMax;
        int sumRightPath = node.val + rightMax;
        int sumOfAllSubPath = node.val + leftMax + rightMax;
        res = Math.max(res, Math.max(node.val, Math.max(sumOfAllSubPath, Math.max(sumLeftPath, sumRightPath))));

        return Math.max(Math.max(sumLeftPath, sumRightPath), node.val);
    }
}
