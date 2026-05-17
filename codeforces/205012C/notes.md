# 205012C

[Problem](https://codeforces.com/group/5tN48zOVvQ/contest/205012/problem/C)

Split 12 cows into 4 teams of 3; minimize (max team sum − min team sum). DFS where each cow (fixed order) picks a team; a `teamCount == 3` cap forces teams of 3 and prunes `4¹² → 12!/(3!)⁴ ≈ 3.7e5`.

## Patterns / notes used
- [Backtracking: one template, the choice set is the pattern](../../PATTERNS.md#backtracking-one-template-the-choice-set-is-the-pattern) (partition / pass-down instance)
