# Competitive Programming Patterns

## Contents

- [Histogram Shift = Sorting](#histogram-shift--sorting)
- [Complementary Counting + Row/Col Independence](#complementary-counting--rowcol-independence)
- [Backtracking: choose / recurse / un-choose](#backtracking-choose--recurse--un-choose)

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

## Backtracking: choose / recurse / un-choose

To enumerate every arrangement (permutations, subsets, combinations, placements), build one partial solution and walk a decision tree. At each node, for every candidate: **choose** it (mark used + add to the path), **recurse**, then **un-choose** it (undo both). Choose and un-choose must mirror each other exactly — that symmetry is the entire pattern.

```java
for (int num = 1; num <= N; num++) {
    if (!used[num]) {
        int mark = path.length();
        used[num] = true;          // choose
        path.append(num);
        recurse(depth + 1);
        path.setLength(mark);      // un-choose — exact mirror of the two choose lines
        used[num] = false;
    }
}
```

Iterating candidates in ascending order makes the output come out in lexicographic order for free.

Track progress with a separate `depth` counter and end on `depth == N` — **not** `path.length()`. Length counts *characters*, which only equals *numbers placed* while every value is one digit; the moment a value reaches 10 the two diverge and the base case never fires. Keeping "how far am I" separate from "what does it render as" is what makes the pattern survive multi-digit values. (See [`setLength` vs `deleteCharAt` for backtracking undo](JAVA_NOTES.md#setlength-vs-deletecharat-for-backtracking-undo) for why the undo uses `setLength`.)

### N = 3 trace
Candidates 1, 2, 3. Fix 1, then 2, then 3 → emit `123`; un-choose 3 and 2; fix 3 then 2 → `132`; back up to the root, fix 2 first → `213`, `231`; then 3 first → `312`, `321`. Output in lexicographic order: `123, 132, 213, 231, 312, 321`.

Related problem: [205012A](codeforces/205012A/notes.md) — generate all permutations of `1..N`.
