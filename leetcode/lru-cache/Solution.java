
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Time complexity:</b> O(1)
 *
 * <p><b>Space complexity:</b> O()
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class LRUCache {

    class Node {
        int key;
        int val;
        Node next;
        Node prev;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Map<Integer, Node> map;
    private int capacity;
    private Node dummyStart;
    private Node dummyEnd;
    private int llSize;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        dummyStart = new Node(-1, -1);
        dummyEnd = new Node(-1, -1);
        dummyStart.next = dummyEnd;
        dummyEnd.prev = dummyStart;
    }
    
    // Time complexity: O(1)
    public int get(int key) {
        Node result = map.get(key);
        if (result == null) return -1;

        removeNodeFromLL(result);
        addNodeToLL(result);
        return result.val;
    }
    
    // Time complexity: O(1)
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).val = value;
            get(key);
        } else {
            if (llSize == capacity) {
                Node toBeEvicted = dummyStart.next;
                removeNodeFromLL(toBeEvicted);
                map.remove(toBeEvicted.key);
            }

            Node newNode = new Node(key, value);
            addNodeToLL(newNode);
            map.put(key, newNode);
        }
    }

    // Time complexity: O(1)
    public void addNodeToLL(Node newNode) {
        Node currLast = dummyEnd.prev;
        currLast.next = newNode;
        dummyEnd.prev = newNode;
        newNode.prev = currLast;
        newNode.next = dummyEnd;
        llSize++;
    }

    // Time complexity: O(1)
    public void removeNodeFromLL(Node toBeRemoved) {
        Node prev = toBeRemoved.prev;
        Node next = toBeRemoved.next;
        prev.next = next;
        next.prev = prev;
        llSize--;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
