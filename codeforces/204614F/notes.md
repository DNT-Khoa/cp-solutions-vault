# 204614F

[Problem](https://codeforces.com/group/5tN48zOVvQ/contest/204614/problem/F)

## Key Insight
Shifting all boxes to the right (gravity) is equivalent to sorting the column heights in ascending order.

## Why Sorting Works
- Each row `k` has boxes in every column with height ≥ k+1
- Shifting right doesn't change the count per row, just packs them right
- The result is that shorter columns end up on the left, taller on the right — i.e., sorted

## Optimal Solution
Just sort the input array. O(N log N) time, O(1) extra space.

## Grid Simulation (Initial Approach)
- Build an M×N grid (M = max height), shift each row's 1s to the right, read column heights
- Works correctly but O(M × N) time and memory — too slow if max height is large

## Lessons
- When shifting/rotating a histogram, think **sorting**
- Ask "what does the end state look like?" instead of simulating step by step
- Counting + placing is simpler than two-pointer shifting
