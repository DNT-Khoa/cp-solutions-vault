class Solution {
    public int search(int[] nums, int target) {
        int N = nums.length;
        int left = -1, right = N;
        int rightMost = nums[N - 1];

        // binary search to find the index of the smallest num
        while (right - left > 1) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= rightMost) {
                right = mid;
            } else {
                left = mid;
            }
        }

        // now the postion of the smallest index will be at right
        // if at this point we find the target, return it
        // otherwise run another binary search
        if (nums[right] == target) return right;

        if (target > rightMost) {
            left = -1;
        } else {
            left = right - 1;
            right = N;
        }

        while (right - left > 1) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (right > -1 && right < N && nums[right] == target) return right;
        return -1;
    }
}
