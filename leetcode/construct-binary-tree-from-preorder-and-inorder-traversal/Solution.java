
import java.util.HashMap;
import java.util.Map;

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
 * <b>Time complexity:</b> O(n) where n is the number of node in tree
 *
 * <p><b>Space complexity:</b> O(n)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>a
 * </ul>
 */
class Solution {
    private Map<Integer, Integer> inIdxMap;
    private int[] preorder;
    private int preIdx;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inIdxMap = new HashMap<>();
        preIdx = 0;
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            inIdxMap.put(inorder[i], i);
        }

        return build(0, inorder.length - 1);
    }

    private TreeNode build(int inLeft, int inRight) {
        if (inLeft > inRight) return null;

        int root = preorder[preIdx++];
        int rootIdx = inIdxMap.get(root);
        TreeNode rootNode = new TreeNode(root);
        
        rootNode.left = build(inLeft, rootIdx - 1);
        rootNode.right = build(rootIdx + 1, inRight);

        return rootNode;
    }
}
