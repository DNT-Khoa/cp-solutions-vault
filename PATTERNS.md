# Competitive Programming Patterns

## Contents

- [Histogram Shift = Sorting](#histogram-shift--sorting)
- [Complementary Counting + Row/Col Independence](#complementary-counting--rowcol-independence)

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
