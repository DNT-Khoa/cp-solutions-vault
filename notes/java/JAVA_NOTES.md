# Java Notes

Language- and library-level notes for competitive programming in Java. These are about *how* to write code efficiently in Java, separate from algorithmic patterns (see [PATTERNS.md](PATTERNS.md)).

## Contents

- [When to use `StringBuilder`](#when-to-use-stringbuilder)
- [Picking the right integer type](#picking-the-right-integer-type)
- [Always-positive modulo](#always-positive-modulo)
- [Counting unique combinations with a `Set`](#counting-unique-combinations-with-a-set)
- [Stream output instead of accumulating it](#stream-output-instead-of-accumulating-it)
- [`setLength` vs `deleteCharAt` for backtracking undo](#setlength-vs-deletecharat-for-backtracking-undo)
- [Printing doubles: avoid scientific notation](#printing-doubles-avoid-scientific-notation)
- [`boolean[]` vs `HashSet<Integer>` for dense-index membership](#boolean-vs-hashsetinteger-for-dense-index-membership)

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

- **Assembling one line / token sequence**: building a single output line from many pieces (numbers + delimiters) before printing it — the O(n²) reason above applies to that line. For *whole-program* output, don't accumulate; stream it (see [Stream output instead of accumulating it](#stream-output-instead-of-accumulating-it)). Accumulate the full output only to reorder/post-process it, or when the sink is unbuffered.
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

## Stream output instead of accumulating it

`new PrintWriter(System.out)` **already wraps the stream in a `BufferedWriter`** (8 KB buffer) and does not autoflush. So writing each line the moment you produce it — `pw.println(line)` in a loop or at a recursion leaf — is *already* batched I/O: the buffer fills and flushes in big chunks, and one `pw.close()` at the end does the final flush.

Collecting the whole output into a `StringBuilder` or `List<String>` first does **not** make it faster — the batching already happened. It only adds O(total output) memory on top of the buffer that's already there, plus a giant temporary `String` (`pw.print(sb)` does `String.valueOf(sb)` before writing).

```java
// don't: accumulate the whole output just to print it
StringBuilder sb = new StringBuilder();
for (int q = 0; q < queries; q++) {
    sb.append(solve(q)).append('\n');
}
pw.print(sb);   // O(total) memory + one huge temp String, no speed gain

// do: emit each line as you produce it — same flushes, O(1) extra memory
for (int q = 0; q < queries; q++) {
    pw.println(solve(q));
}
// (recursion leaf is the same idea: pw.println(path); return;)
```

Concrete cost: "print all `N!` permutations" with an accumulator holds every line at once and runs out of memory (`OutOfMemoryError`, "OOM" for short) at `N = 10` (~3.6M lines) even under a 64 MB heap; the streaming version above stays at O(N) and runs fine. Reach for a full-output accumulator only when you must **reorder or post-process** everything before printing — never just to "print fast". If you emit in generation order, stream it.

Related problem: [205012A](codeforces/205012A/notes.md) — the `StringBuilder` and `List<String>` versions OOM'd at `N = 10`; streaming straight to `pw` did not.

## `setLength` vs `deleteCharAt` for backtracking undo

When undoing a `StringBuilder` append during backtracking, snapshot the length *before* appending and restore it after:

```java
int mark = sb.length();
sb.append(x);          // x may render as 1 char or many
recurse();
sb.setLength(mark);    // drops exactly what was added, whatever its length
```

`sb.deleteCharAt(sb.length() - 1)` removes exactly **one** character. That only mirrors the append while every appended value is a single character. Append a two-digit number (10, 11, …), or a value plus a separator, and the undo removes too little — the buffer never unwinds, the path silently corrupts, and in the worst case it grows until OOM. `setLength(mark)` rewinds to a known-good point no matter how many characters the append produced, so it's the default undo for the mutate+undo style of the [backtracking pattern](PATTERNS.md#backtracking-one-template-the-choice-set-is-the-pattern).

Related problem: [205012A](codeforces/205012A/notes.md) — `deleteCharAt` passed for `N ≤ 9` but corrupted the path at `N ≥ 10` where values are two digits.

## Printing doubles: avoid scientific notation

When the answer is a `double`, print it with `printf`, **not** `println`:

```java
pw.printf("%.6f%n", ans);   // 1000000000.000000
// not: pw.println(ans);    // 1.0E9  ← checker can't read this
```

### Why `println` fails

`System.out.println(double)` (and `String.valueOf(double)`) switch to **scientific notation** for values `≥ 10⁷` or `< 10⁻³`. So an answer of `1000000000.0` prints as `1.0E9`. Competitive judges read the answer with a strict real-number parser (Codeforces uses **testlib**, whose `readDouble` rejects the `E`), so a perfectly correct value gets marked wrong purely on format.

The bug hides on small samples — `200.5` prints fine either way — and only surfaces when a test has a large answer.

### What the format string means

`"%.6f%n"` → `.6` = six digits after the decimal point, `f` = fixed-point (the part that kills scientific notation), `%n` = newline (`printf` does *not* add one like `println` does).

### Picking the precision

Match or exceed the problem's stated tolerance. `%.6f` rounds to 6 decimals, giving absolute error `≤ 5 × 10⁻⁷` — safe for the common "error `≤ 10⁻⁶`" requirement. Printing a couple extra digits (`%.9f`) costs nothing (the checker reads the value, not the digit count) and removes any doubt.

Related problem: [283932B](codeforces/283932B/notes.md) — rope-cutting answer up to `10⁹` printed as `1.0E9` with `println`; `printf("%.6f%n", …)` fixed it. Pairs with the [binary search on doubles](PATTERNS.md#binary-search-on-doubles-fixed-loop-not-while) pattern.

## `boolean[]` vs `HashSet<Integer>` for dense-index membership

When the things you're tracking are indices into a known range `0 … n-1` (or any dense, bounded set of `int`s), use a `boolean[]` for membership — **not** a `HashSet<Integer>`. Both are O(1) per lookup and O(n) space *on paper*, but the constant factors differ by ~5× in time and ~20× in memory.

```java
boolean[] excluded = new boolean[n];   // index it directly
excluded[idx] = true;
if (excluded[i]) { ... }
```

```java
Set<Integer> excluded = new HashSet<>();  // boxes every int
excluded.add(idx);
if (excluded.contains(i)) { ... }
```

### Why the same Big-O hides such different numbers

Big-O tells you how cost *grows* with input; the constant factor is the cost *per step*. The swap above doesn't change the growth curve — it slashes the cost of each step:

- **No autoboxing.** `HashSet<Integer>` can't hold primitive `int`, so every `add`/`contains` wraps it in an `Integer` object (a heap allocation for values > 127). `boolean[]` stores raw bits inline.
- **Direct index vs. hashing.** `excluded[i]` is one address computation + one read. `contains(i)` hashes the `Integer`, finds a bucket, then walks bucket entries comparing with `.equals`.
- **Cache locality.** A `boolean[]` is one contiguous block (1 byte/slot) — cache-friendly to scan. A `HashSet`'s `Integer` objects and `Node` wrappers (~40-50 bytes/entry) are scattered across the heap, so each lookup tends to miss cache.

Mental model: an array says *"I know exactly where `i` lives — go there."* A hash set says *"I don't know where `i` is — let me compute a guess and search."* When your keys are already `0 … n-1`, the array's assumption is free.

### Concrete numbers

Same problem, same O(n·log n) algorithm, only the membership structure changed:

| Structure | Time | Memory |
|---|---|---|
| `HashSet<Integer>` | 1703 ms | 18200 KB |
| `boolean[]` | 312 ms | 900 KB |

On Codeforces this can be the whole difference between AC and TLE — two correct same-complexity solutions separated purely by constants.

One gotcha when you allocate the `boolean[]` inside a function called many times (e.g. each binary-search probe): a fresh `new boolean[n]` per call is fine for correctness but adds an O(n) allocation each time. If it's hot, allocate once outside and reset only the slots you set.

Related problem: [283932F](codeforces/283932F/notes.md) — `canDelete` membership test; `HashSet<Integer>` → `boolean[]` took it from 1703 ms to 312 ms. Pairs with the [binary search on the answer](PATTERNS.md) pattern.
