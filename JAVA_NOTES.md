# Java Notes

Language- and library-level notes for competitive programming in Java. These are about *how* to write code efficiently in Java, separate from algorithmic patterns (see [PATTERNS.md](PATTERNS.md)).

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
