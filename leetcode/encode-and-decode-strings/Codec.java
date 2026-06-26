
import java.util.ArrayList;
import java.util.List;

class Codec {

    // Time complexity: O(n) where n is the total number of characters out of all strings
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encodedStrs = new StringBuilder();
        for (String s : strs) {
            StringBuilder encodedS = new StringBuilder();
            encodedS.append(s.length());
            encodedS.append("#");
            encodedS.append(s);

            encodedStrs.append(encodedS);
        }

        return encodedStrs.toString();
    }

    // Time complexity: O(n)
    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int idx = 0; 
        while (idx < s.length()) {
            int j = idx;
            while (s.charAt(idx) != '#') {
                idx++;
            }

            int stringLength = Integer.parseInt(s.substring(j, idx));
            idx++;
            res.add(s.substring(idx, idx + stringLength));
            idx += stringLength;
        }

        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));