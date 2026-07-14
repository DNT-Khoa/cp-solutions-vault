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
 * <b>Time complexity:</b> O(h + k) where h is the height of the tree
 *
 * <p><b>Space complexity:</b> O(h) 
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li>For the time complexity, after traveling h to the furthest node on the left, we are currently at the smallest, to find the kth smallest, 
 *  you go k another time until you reach it
 * </li>
 * </ul>
 */
class Solution {
    private Integer res = null;
    private int counter = 0;

    public int kthSmallest(TreeNode root, int k) {
        inOrderTraversal(root, k);

        return res;
    }

    void inOrderTraversal(TreeNode node, int k) {
        if (node == null || res != null) return;
        
        inOrderTraversal(node.left, k);

        counter++;
        if (counter == k) res = node.val;

        inOrderTraversal(node.right, k);
    }
}
