# Java Technical Notes

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
