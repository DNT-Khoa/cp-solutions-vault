# 205012B

[Problem](https://codeforces.com/group/5tN48zOVvQ/contest/205012/problem/B)

Count pairs (Exonian lineup, Smurf lineup) with equal total strength, both non-empty. `n+m ≤ 17` → brute-force all `2^(n+m)` lineups via one include/exclude DFS over the combined player line, carrying one strength accumulator per team.

## Patterns / notes used
- [Backtracking: one template, the choice set is the pattern](../../PATTERNS.md#backtracking-one-template-the-choice-set-is-the-pattern)
