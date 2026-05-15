# 🏔️ cp-solutions-vault

> ⚔️ My personal stash of competitive programming solutions in **Java**, plus notes on the patterns and tricks behind them — kept for my own later reference.

---

## 📚 The Knowledge Base

The two most important files in this repo — read these, not just the solutions:

| File | What's inside |
|------|---------------|
| 🧩 **[PATTERNS.md](PATTERNS.md)** | Reusable **algorithmic** patterns — the "aha" ideas that recur across problems (complementary counting, histogram-shift = sorting, …). |
| ☕ **[JAVA_NOTES.md](JAVA_NOTES.md)** | **Java-specific** idioms & gotchas for CP — `StringBuilder`, int vs. long, always-positive modulo, `Set`-based dedup, … |

Each has a 📑 Table of Contents up top for quick jumping. Every entry links back to the problem that motivated it. 🔗

---

## 🗂️ Repository Layout

```
🏔️ cp-solutions-vault/
├── 🧩 PATTERNS.md        ← algorithmic patterns catalog
├── ☕ JAVA_NOTES.md      ← Java idioms & gotchas catalog
├── 🟦 codeforces/        ← Codeforces solutions
├── 🟧 kattis/            ← Kattis solutions
├── 🟨 leetcode/          ← LeetCode solutions
└── 🧪 playground/        ← scratch space for testing ideas
```

---

## 🧱 Anatomy of a Problem

Every problem lives in its own directory (e.g. `codeforces/204642H/`):

| File | Role |
|------|------|
| ☕ `Solution.java` | The solution |
| 📥 `*.in` | Sample input files |
| 📤 `*.out` | Expected output files |
| 📝 `notes.md` | Problem link + any problem-specific notes |

> 💡 Deep explanations live in the **root catalogs**, not in `notes.md` — the per-problem note is usually just a link and stays lean.

---

## 🛠️ Tooling

Scaffolding, test-running, and solution management are powered by 🦅 **[KestrelCP](https://github.com/DNT-Khoa/kestrelcp)** — it spins up problem directories, fetches samples, and runs tests so the focus stays on *solving*. 🚀
