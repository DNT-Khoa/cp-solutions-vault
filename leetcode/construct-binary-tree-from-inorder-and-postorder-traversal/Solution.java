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

import java.util.HashMap;
import java.util.Map;

/**
 * <b>Time complexity:</b> O(n) where n is the number of nodes in tree
 *
 * <p><b>Space complexity:</b> O(n)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {

    private Map<Integer, Integer> inIdxMap;
    private int postIdx;
    private int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inIdxMap = new HashMap<>();
        this.postIdx = postorder.length - 1;
        this.postorder = postorder;

        for (int i = 0; i < inorder.length; i++) {
            inIdxMap.put(inorder[i], i);
        }

        return build(0, inorder.length - 1);
    }

    private TreeNode build(int inLeft, int inRight) {
        if (inLeft > inRight) return null;
        int rootVal = postorder[postIdx--];
        int rootIdx = inIdxMap.get(rootVal);

        TreeNode node = new TreeNode(rootVal);
        node.right = build(rootIdx + 1, inRight);
        node.left = build(inLeft, rootIdx - 1);

        return node;
    }
}
