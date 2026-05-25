# Competitive Programming Patterns

## Contents

- [Histogram Shift = Sorting](#histogram-shift--sorting)
- [Complementary Counting + Row/Col Independence](#complementary-counting--rowcol-independence)
- [Backtracking: one template, the choice set is the pattern](#backtracking-one-template-the-choice-set-is-the-pattern)
- [Binary Search: which bound holds the answer](#binary-search-which-bound-holds-the-answer)
- [Binary Search on Doubles: fixed loop, not while](#binary-search-on-doubles-fixed-loop-not-while)

## Histogram Shift = Sorting
When you shift/rotate/apply gravity to a histogram (columns of boxes), the result is equivalent to sorting the column heights.
- Example: shifting all boxes right → sort heights ascending
- Applies to: gravity simulations, falling blocks, water pouring, skyline rearrangements
- Related problem: [204614F](codeforces/204614F/notes.md)

## Complementary Counting + Row/Col Independence
When a grid condition is "row is good **OR** col is good", `OR` is hard to count directly (double-counting). Flip it via DeMorgan:

> "row good OR col good"  ⇔  NOT ("row bad AND col bad")

`AND` between two independent conditions = multiplication, so:

```
not-eaten count = (# bad rows) × (# bad cols)
eaten count     = R * C − (# bad rows) × (# bad cols)
```

### Why it works geometrically
Bad rows are horizontal stripes; bad cols are vertical stripes. The cells satisfying "bad row AND bad col" sit exactly at the **intersections** of these stripes, forming a perfect (bad rows) × (bad cols) rectangle pattern — even when the bad rows/cols are scattered.

### How to spot it
Ask: **"Is the condition on each cell built from a property of its row + a property of its column, independently?"**
If yes, the answer factors into `(rows-thing) × (cols-thing)`.

### Other problems where the same flip works
- "Count cells where row sum > 0 OR col sum > 0" → flip to "row sum ≤ 0 AND col sum ≤ 0" → multiply.
- "Count pairs `(i,j)` where `a[i]` is even OR `b[j]` is even" → flip to "`a[i]` odd AND `b[j]` odd" → (# odd `a[i]`) × (# odd `b[j]`).
- "Place a non-attacking rook" → safe squares = (empty rows) × (empty cols).

### Two-line summary
1. **OR is hard, AND is easy** — flip via DeMorgan when stuck on OR.
2. **AND of independent row/col conditions = multiplication** — the answer factors into a product.

- Related problem: [204642C](codeforces/204642C/notes.md)

## Backtracking: one template, the choice set is the pattern

Every backtracking enumeration — subset, permutation, combination, k-digit string, N-queens — is the *same* function. One line changes.

```java
void dfs(int k, State state) {
    if (k == numDecisions) {                  // leaf = one complete object
        if (accept(state)) record(state);
        return;
    }
    for (Choice c : candidates(k, state)) {   // ← the only pattern-specific line
        dfs(k + 1, apply(state, c));
    }
}
```

The recursion, base case, and "advance to `k+1`" are boilerplate. What each slot means:

- `k` — which decision you're on (also the recursion depth), `0 … numDecisions-1`.
- `numDecisions` — how many decisions make one complete object (`n` items, `n` slots, …).
- `state` — the partial object so far (a running sum, the path, a `used[]` set).
- `candidates(k, state)` — the legal choices for decision `k` given `state`. **This line alone is the pattern.**
- `apply(state, c)` — `state` after taking choice `c` (pass a new value down, or mutate it).
- `accept` / `record` — at a leaf: is the finished object valid, and what to do with it (print / count / store).

**A pattern's whole identity is `candidates(k, state)`** — *what are the legal choices for the next decision, given what's built so far?* The table is examples, not a taxonomy; the real tool is that question:

| pattern | `candidates(k, state)` |
|---|---|
| subset / include-exclude | `{exclude, include}` item `k` — always 2 |
| partition into k groups | which of the k groups item `k` joins; skip full ones |
| permutation | values not yet used — n, n−1, … |
| combination | values with index **>** the last picked |
| k-digit base-b string | `0 … b−1` |
| N-queens | columns no current queen attacks |

### Worked: 205012B (subset / include-exclude, counting)
Fill the template in: `numDecisions = n+m` players; `state` = the two team sums; `candidates(k)` = {exclude, include player `k`}; `apply` = add the skill to that player's team (pass-down); `accept` = both teams non-empty and equal; to *count*, return `0/1` at the leaf and **sum the branches** (every leaf is a distinct lineup, so they add — don't `max`).

```java
long dfs(int k, long eSum, long sSum) {                   // state = (eSum, sSum)
    if (k == n + m)                                       // k == numDecisions → leaf
        return (eSum > 0 && sSum > 0 && eSum == sSum) ? 1 : 0;   // accept ? 1 : 0
    long inc = (k < n) ? dfs(k + 1, eSum + skill[k], sSum)       // include → apply
                       : dfs(k + 1, eSum, sSum + skill[k]);      //  (pass down)
    long exc = dfs(k + 1, eSum, sSum);                           // exclude
    return inc + exc;                                            // count = sum branches
}
```

Same skeleton as the generic one above — only `candidates` (include/exclude) and `apply` (which sum grows) are filled in.

### Orthogonal axis: how state is carried
Independent of *which* pattern, `apply(state, c)` has two styles — same tree either way:

- **Pass down (functional):** build the new value, hand it to the child, no undo. `205012B` above: `dfs(k+1, eSum + skill[k], sSum)`.
- **Mutate + undo:** change shared state, recurse, then restore it — *choose → recurse → un-choose*, where the undo lines mirror the choose lines exactly. Used when state is awkward to copy (e.g. a `used[]` array, as in permutations).

Pick by what's cheap to copy: a scalar (`sum`) → pass down; a `used[]` array → mutate + undo. This is a *state-handling* choice, **not** what makes a pattern a subset vs a permutation.

### Items `[A, B]` — concrete
Subset DFS → 4 leaves: `{}`, `{B}`, `{A}`, `{A,B}` (each item in/out). Permutation DFS → 2 leaves: `[A,B]`, `[B,A]` (`candidates` shrinks 2 → 1). Same tree depth; the `candidates` line is the only difference.

### Fixed order vs permuting (why no `used[]`)

**Rule:** if order *within* a group doesn't matter, walk items in a fixed order and, for each item, choose **which group it joins** — not **which item comes next**.

**Why — tiny example.** Split 4 items `A B C D` into 2 teams of 2.

- *Permuting items into slots* (slots 1–2 = team 1, slots 3–4 = team 2): team `{A,B}` is built by both `AB|..` and `BA|..`. The 2 items inside a team reorder `2!` ways, in each of 2 teams, so every real split is generated `2!·2! = 4` times. That's `4! = 24` orderings but only `24 / 4 = 6` real splits.
- *Walking in fixed order* (`A`, then `B`, …) and only choosing each item's **team**: a team's members always appear in input order, so a split is generated exactly once. No `used[]` needed — `used[]` exists only for permutations, where order genuinely matters.

**Payoff (205012C).** 12 cows → 4 teams of 3. "Cow `k` picks a team, skip a team already holding 3" visits `12! / (3!)⁴ ≈ 3.7×10⁵` leaves. Permuting cows instead visits `12! ≈ 4.8×10⁸` — the same splits, each duplicated `(3!)⁴` times.

### Related problems

- [205012B](codeforces/205012B/notes.md) — count equal-strength lineups. *Subset; pass-down state; worked above.*
- [205012A](codeforces/205012A/notes.md) — all permutations of `1..N`. *Permutation; mutate+undo; ascending candidates ⇒ lexicographic output.*
- [205012C](codeforces/205012C/notes.md) — split 12 cows into 4 teams of 3. *Partition; `teamCount` cap prunes `4¹² → 12!/(3!)⁴`.*

## Binary Search: which bound holds the answer

A binary search keeps two bounds, `left` and `right`, that close in on the answer; when the loop ends they're adjacent. The target sits **between** them, so one of the two *is* the answer. The bound you **return** lands on the target; the other stops strictly past it. Set *both* deliberately.

`f` is a monotone yes/no test, so the line splits into a *fail* region and a *pass* region; the answer is the boundary, and the two final bounds straddle it. **Return whichever the question asks for** — the smallest value that passes, or the largest. The other bound is the **outer sentinel**, and it is *not* arbitrary: it must (a) genuinely evaluate to its side — the test really fails there — and (b) sit strictly beyond the target, so the answer always stays bracketed.

*Which* side passes is your choice — negating the predicate flips it — so match the update to it:

| you return | loop update | the other (outer) bound must |
|---|---|---|
| `right` — smallest passing value | `if (f(mid)) right = mid; else left = mid;` | be `left`: fails the test **and** strictly *below* the smallest possible answer |
| `left` — largest passing value | `if (f(mid)) left = mid; else right = mid;` | be `right`: fails the test **and** strictly *above* the largest possible answer |

Don't reuse `-1` / `N+1` by reflex — the right sentinel depends on `f`:

## Binary Search on Doubles: fixed loop, not while

When the answer is a **real number** (a length, a rate, a ratio) instead of an integer, binary-search the answer — but the loop changes shape.

**Why the integer `while` breaks.** A `double` stores only ~16 significant digits — a fixed length. Every time you split the range, `l` and `r` get closer together, so `mid` differs from them only further out in the decimals — it needs *more* digits to write down. Eventually it needs more than 16: the extra digits get rounded off, and `(l + r) / 2` rounds back to exactly `l` or `r`. Now `mid` equals an endpoint, so `l = mid` (or `r = mid`) doesn't change anything — and a `while (r - l > eps)` loop (`eps` = a precision you'd have to pick, e.g. `1e-6`) **spins forever**. This bites when the answer is large (more digits used up before the point, fewer left for splitting) and the precision is tight.

**The fix: loop a fixed number of times.** Each step halves the range, so after a fixed count the range is a single point — no termination condition to get stuck on, and **no `eps` to choose** (you'd rarely know the right one anyway).

```java
double l = 0, r = maxAns;          // maxAns = largest the answer could be
for (int i = 0; i < 100; i++) {    // 100 halvings — can't hang, always enough
    double mid = (l + r) / 2;
    if (f(mid)) l = mid;           // mid works → answer is in [mid, r]
    else        r = mid;           // mid fails → answer is in [l, mid]
}
// l ≈ r ≈ the answer
```

**Why 100.** Each iteration halves the interval: `8 → 4 → 2 → 1 → 0.5 → ...`. After `n` steps the range is `maxAns / 2ⁿ`. You want that below your precision `eps`, i.e. `maxAns / 2ⁿ ≤ eps`. Solving for `n` gives `n ≥ log2(maxAns / eps)` — that's where the formula comes from. With `maxAns ≈ 1e9` and `eps ≈ 1e-9` that's only ~60, so 100 halvings clears any sane case: "just write 100 and don't think."

> **`e` notation.** `1e9` means `1 × 10⁹` (move the decimal 9 places right) = `1000_000_000`. A negative exponent moves left: `1e-9` = `0.000000001`, `1e-6` = `0.000001`. It's just shorthand for big/small numbers, and is valid Java for `double` literals (`double eps = 1e-9;`).

**Choosing `l` and `r`.** The loop needs `f(l)` true and `f(r)` false. Pick bounds you know land on those sides — don't blindly copy `l = -1, r = N+1` from integer search. For a length, the domain is `(0, maxAns]`, so use `l = 0` (true) and `r = maxAns + 1` (false). `l = -1` is a trap: `f(-1)` is false (negative length), so when the answer is tiny, `mid` gets dragged negative, `l` never moves, and you `return -1`.

Related problem: [283932B](codeforces/283932B/notes.md) — rope cutting; `l = -1` failed the small-answer test.
