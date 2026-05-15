# Java Notes

Language- and library-level notes for competitive programming in Java. These are about *how* to write code efficiently in Java, separate from algorithmic patterns (see [PATTERNS.md](PATTERNS.md)).

## Contents

- [When to use `StringBuilder`](#when-to-use-stringbuilder)
- [Picking the right integer type](#picking-the-right-integer-type)
- [Always-positive modulo](#always-positive-modulo)
- [Counting unique combinations with a `Set`](#counting-unique-combinations-with-a-set)

## When to use `StringBuilder`

Use `StringBuilder` whenever you need to **build or mutate a string** — appending, inserting, deleting, or replacing characters.

### Why it matters

`String` in Java is **immutable**. Every operation that looks like it mutates a string actually allocates a brand new `String` object and copies the data:

```java
String s = "";
for (int i = 0; i < n; i++) {
    s += someChar;   // O(n) copy each iteration → O(n²) total
}
```

`StringBuilder` keeps an internal mutable `char[]` and only resizes when needed (amortized O(1) per append):

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) {
    sb.append(someChar);   // amortized O(1) → O(n) total
}
String s = sb.toString();   // one final copy
```

### When to reach for it

- **Building output**: collecting many numbers/words before printing. Print once at the end via `pw.print(sb)` — much faster than many `println` calls.

  ```java
  StringBuilder sb = new StringBuilder();
  for (int q = 0; q < queries; q++) {
      sb.append(solve(q)).append('\n');
  }
  pw.print(sb);   // single flush instead of `queries` separate println calls
  ```

- **Mutating a string**: inserting, deleting, or replacing characters at specific positions (`sb.insert(i, c)`, `sb.deleteCharAt(i)`, `sb.setCharAt(i, c)`).
- **Reversing a string**: `new StringBuilder(s).reverse().toString()`.
- **Reading the string into a mutable form**: `StringBuilder sb = new StringBuilder(inputStr);` skips having to convert through `char[]` or `List<Character>`.

Related problem: [204642F](codeforces/204642F/notes.md) — needed to insert/remove chars while testing palindromes.

## Picking the right integer type

Match the type to the largest value that will ever appear — including intermediate results, not just the input.

### Java's integer types at a glance

| Type | Bits | Max value (approx) | Exact max |
|------|------|---------------------|-----------|
| `int` | 32 | ~2.1 × 10⁹ | 2,147,483,647 |
| `long` | 64 | ~9.2 × 10¹⁸ | 9,223,372,036,854,775,807 |
| `BigInteger` | unbounded | — | — |

### Rule of thumb from the input constraint

Read the constraint line in the problem and pick the smallest type that fits:

| Input constraint | Use |
|------------------|-----|
| `n ≤ 10⁹` | `int` |
| `10⁹ < n ≤ 10¹⁸` | `long` |
| `n > 10¹⁸` | `BigInteger` |

`int` covers up to roughly 2 × 10⁹, so any constraint like `n ≤ 10⁹` is safe. Anything in the `10¹⁰`–`10¹⁸` range needs `long`. Past that, you need `BigInteger` (slow — usually a sign the problem expects modular arithmetic, not raw big numbers).

### Watch out for derived values

The dangerous case isn't the input — it's what you compute *from* it. Even if each input fits in `int`, the result of a product or sum can blow past `int` range:

- **Products**: if `a, b ≤ 10⁵` (int-safe), then `a * b ≤ 10¹⁰` → **needs `long`**.
- **Sums of many values**: if `n ≤ 10⁵` items each up to `10⁹`, the sum can reach `10¹⁴` → **needs `long`**.
- **Squared distances**: if coordinates are up to `10⁴`, then `dx² + dy²` reaches `2 × 10⁸` (int-safe), but at `10⁵` it reaches `2 × 10¹⁰` → **needs `long`**.

Mental check before writing arithmetic: *"What's the largest value this expression can produce?"* If that number could exceed ~2 × 10⁹, either declare the variables as `long` from the start, or cast one operand to `long` before the operation:

```java
long product = (long) a * b;   // forces 64-bit multiplication even when a, b are int
```

Casting only the result — `(long)(a * b)` — does **not** work: the multiplication still runs in 32-bit and overflows before the widening.

### Parsing matches the type

- `Integer.parseInt(s)` — throws `NumberFormatException` if `s` represents a value beyond INT_MAX (Codeforces reports this as Runtime Error).
- `Long.parseLong(s)` — handles anything up to LONG_MAX.
- `new BigInteger(s)` — for arbitrary-precision input.

Related problem: [204642G](codeforces/204642G/notes.md) — input up to `10¹²` required switching from `int` to `long`.

## Always-positive modulo

### The problem

You have a circular structure of size `N` — a ring buffer, a circular array, or a combination-lock dial with positions `0, 1, … N-1` (`N` = the total number of positions). "Circular" means it wraps: the position one step before `0` is `N-1`, not `-1`. You want "the position `i` steps before `cur`", and the result must always come back as a valid position in `0 … N-1`.

### The approach

```java
int prev = (cur - i + N) % N;
```

Subtract to step back, add one `N` to stay non-negative, then `% N` to fold back into range.

The `+ N` is the part that matters. Java's `%` keeps the sign of the left operand, so `(cur - i) % N` alone returns a *negative* result whenever `cur - i` is negative — e.g. `cur = 1`, `i = 2`, `N = 50` gives `(1 - 2) % 50 = -1`, not the `49` you want. (This differs from mathematical/Python modulo, which always lands in `0 … N-1`.) Adding `N` before the mod absorbs that.

One `+ N` is enough here because the value never drops below `-N`: `cur` is a valid index (`0 … N-1`) and you only subtract a small step `i`, so `cur - i` stays well above `-N`, which means `cur - i + N` is already non-negative. The trailing `% N` then trims it back down when no wrap was needed.

Walk through it with `N = 50`:
- `cur = 1`, `i = 2`: `1 - 2 + 50` = `49`; `49 % 50` = `49`  ✓ (wrapped, as wanted)
- `cur = 10`, `i = 2`: `10 - 2 + 50` = `58`; `58 % 50` = `8`  ✓ (no wrap; the extra `N` is removed by the mod)

### Extending it: when the value can go far below `-N`

The single `+ N` works only because `cur - i` never drops below `-N`. If you compute something that *can* go far more negative (e.g. `-1000` with `N = 7`), one `+ N` isn't enough — `-1000 + 7` is still negative. Shrink it into range *first* with a mod, then add `N`:

```java
int wrapped = ((value % N) + N) % N;   // first `% N` pulls value into the range (-N, N)
```

Or use the library helper, which does all of the above for any input:

```java
int wrapped = Math.floorMod(value, N); // always lands in 0 .. N-1
```

### Which form to use

| How far below zero can the value go? | Use |
|---|---|
| Never negative | `value % N` |
| Only slightly (a valid index minus a small step) | `(value + N) % N` |
| Arbitrarily far negative | `((value % N) + N) % N` or `Math.floorMod(value, N)` |

Overflow note: in `(position - i + N) % N`, the intermediate `position - i + N` is an `int`. If `position` or `N` can reach ~10⁹ this addition overflows — do the arithmetic in `long` (see "Picking the right integer type"). For a small `N` like a lock dial, there's no risk.

Related problem: [204642H](codeforces/204642H/notes.md) — circular combination lock.

## Counting unique combinations with a `Set`

To count distinct tuples (pairs, triples, …), put a **canonical key** for each tuple into a `Set` and read `set.size()`.

### Use a `record` as the key

A `record` auto-generates value-based `equals`/`hashCode`, so `Set<Triple>` deduplicates by contents:

```java
record Triple(int a, int b, int c) {}

Set<Triple> seen = new HashSet<>();
seen.add(new Triple(a, b, c));
// ...
int distinct = seen.size();
```

If order within the tuple shouldn't matter (a *combination*, where `{3,1,2}` ≡ `{1,2,3}`), **sort the values before constructing the key** so equivalent tuples normalize to one form. If the positions are semantically distinct (e.g. three different dials), do **not** sort — `(0,1,2)` and `(2,1,0)` are genuinely different.

### `Set<int[]>` does not work

`int[]` inherits identity-based `equals`/`hashCode`, so two arrays with the same contents are treated as different elements:

```java
Set<int[]> s = new HashSet<>();
s.add(new int[]{1,2,3});
s.add(new int[]{1,2,3});
s.size();                 // 2, not 1 — silent wrong answer
```

Use a `record` (value-based), a packed `long` (`Set<Long>`, for small bounded values), or `List<Integer>` (its `equals`/`hashCode` are content-based) instead.

Related problem: [204642H](codeforces/204642H/notes.md) — union of valid lock combinations counted via `Set<Triple>`.
