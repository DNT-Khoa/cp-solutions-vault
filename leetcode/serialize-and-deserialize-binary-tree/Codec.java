
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Time complexity:</b> O(?)
 *
 * <p><b>Space complexity:</b> O(?)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    private int preIdx = 0;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> encoded = new ArrayList<>();
        dfsEncode(root, encoded);
        return String.join(",", encoded);
    }

    void dfsEncode(TreeNode node, List<String> encoded) {
        if (node == null) {
            encoded.add("N");
            return;
        }

        encoded.add(String.valueOf(node.val));
        dfsEncode(node.left, encoded);
        dfsEncode(node.right, encoded);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] values = data.split(",");
        return dfsDecode(values);
    }

    TreeNode dfsDecode(String[] values) {
        if (values[preIdx].equals("N")) {
            preIdx++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(values[preIdx++]));
        node.left = dfsDecode(values);
        node.right = dfsDecode(values);

        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
