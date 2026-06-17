
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Time complexity:</b>
 * <ul>
 *   <li><code>set</code>: O(1) amortized — HashMap lookup + ArrayList append at end.</li>
 *   <li><code>get</code>: O(log M), where M = number of entries for the queried key.</li>
 * </ul>
 *
 * <p><b>Space complexity:</b> O(N), where N = total number of <code>set</code> calls.
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li>Per-key list is append-only and timestamps are strictly increasing, so each list
 *       stays sorted by timestamp — binary search applies directly.</li>
 *   <li>Binary search finds the last entry with <code>timestamp &lt;= target</code>
 *       ("find last true" variant): <code>left = -1</code> tracks the last matching index,
 *       <code>right = size</code> is the known-false sentinel past the end.</li>
 *   <li>log M (not log N): each <code>get</code> only scans one key's list, so per-key
 *       locality matters — 1M sets across 1K keys means ~10 comparisons, not ~20.</li>
 * </ul>
 */
class TimeMap {

    record TimestampValue(int timestamp, String value) {}

    private final Map<String, List<TimestampValue>> map;
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) map.put(key, new ArrayList<>());
        map.get(key).add(new TimestampValue(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }

        // do binary search
        var values = map.get(key);
        int left = -1, right = values.size();
        while (right - left > 1) {
            int mid = left + (right - left) / 2;

            if (values.get(mid).timestamp <= timestamp) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (left == -1) return "";
        return values.get(left).value;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
