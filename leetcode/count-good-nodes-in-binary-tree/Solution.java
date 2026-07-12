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
    private int numOfGoodNodes = 0;

    public int goodNodes(TreeNode root) {
        explore(root, root.val);
        return numOfGoodNodes;     
    }

    void explore(TreeNode node, int maxSoFar) {
        int updatedMax = maxSoFar;
        if (node.val >= maxSoFar) {
            numOfGoodNodes++;
            updatedMax = node.val;
        }
        
        if (node.left != null) {
            explore(node.left, updatedMax);
        }

        if (node.right != null) {
            explore(node.right, updatedMax);
        }
    }
}
