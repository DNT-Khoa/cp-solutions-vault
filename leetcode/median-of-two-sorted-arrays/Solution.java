/**
 * <b>Time complexity:</b> O(log(min(m, n)))
 *
 * <p><b>Space complexity:</b> O(1)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li>Binary search on the smaller array to pick a partition.</li>
 *   <li>The partition on the other array is forced by the size constraint.</li>
 *   <li>Check that every left element ≤ every right element across the two arrays.</li>
 * </ul>
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] A = nums1;
        int[] B = nums2;
        int total = nums1.length + nums2.length;
        int half = total / 2;

        // make sure length of A is less than length of B
        if (A.length > B.length) {
            var temp = A;
            A = B;
            B = temp;
        }

        // run binary search on A
        int left = -1, right = A.length - 1;
        while (true) {
            int i = left + (right - left) / 2;
            int j = half - (i + 1) - 1;

            int Aleft = i >= 0 ? A[i] : Integer.MIN_VALUE;
            int Aright = i + 1 < A.length ? A[i + 1] : Integer.MAX_VALUE; 
            int Bleft = j >= 0 ? B[j] : Integer.MIN_VALUE;
            int Bright = j + 1 < B.length ? B[j + 1] : Integer.MAX_VALUE;

            if (Aleft <= Bright && Bleft <= Aright) {
                // odd
                if (total % 2 == 1) {
                    return Math.min(Aright, Bright);
                }
                // even
                return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
            } else if (Aleft > Bright) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
    }
}
