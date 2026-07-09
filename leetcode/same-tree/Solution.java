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
 * <b>Time complexity:</b> O(min(n, m)) where n and m are number of calls made for p and q respectively
 *
 * <p><b>Space complexity:</b> O(min(h_n, h_m)) where h_n and h_m stack depth of p and q respectively
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return (p == null && q == null) 
        || (p != null && q != null && p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
}
