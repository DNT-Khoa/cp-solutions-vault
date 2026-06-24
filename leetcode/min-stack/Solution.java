
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <b>Time complexity:</b> O(1) for all operations
 *
 * <p><b>Space complexity:</b> O(N)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class MinStack {

    record Item(int num, int runningMin) {}
    private Deque<Item> stack;

    public MinStack() {
        this.stack = new ArrayDeque<>();
    }
    
    public void push(int value) {
        if (stack.isEmpty()) {
            stack.offerFirst(new Item(value, value));
        } else {
            stack.offerFirst(new Item(value, Math.min(value, stack.peek().runningMin)));
        }
    }
    
    public void pop() {
        stack.pollFirst();
    }
    
    public int top() {
        return stack.peek().num;
    }
    
    public int getMin() {
        return stack.peek().runningMin;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
