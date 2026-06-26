# 🏔️ cp-solutions-vault

> ⚔️ My personal stash of competitive programming solutions in **Java**, plus notes on the patterns and tricks behind them — kept for my own later reference.

---

## 📚 The Knowledge Base

The distilled knowledge lives under **[notes/](notes/)**, one file per topic, split into two areas:

| Where | What's inside |
|-------|---------------|
| 🧩 **[notes/algorithms/](notes/algorithms/)** | Algorithmic patterns & data-structure tricks — the recurring "aha" ideas (e.g. [Staircase Search](notes/algorithms/Staircase_Search.md), [Binary Search on the Answer](notes/algorithms/Binary_Search_Boundary.md), [Stack & Queue](notes/algorithms/Stack_Queue.md), [Heap & PriorityQueue](notes/algorithms/Heap_PriorityQueue.md)). |
| ☕ **[notes/java/](notes/java/)** | **Java-specific** idioms & gotchas for CP (e.g. [JAVA_NOTES.md](notes/java/JAVA_NOTES.md)). |

Each file has a 📑 Table of Contents up top for quick jumping. Every entry links back to the problem that motivated it. 🔗

---

## 🗂️ Repository Layout

```
🏔️ cp-solutions-vault/
├── 🗂️ notes/             ← topic-specific deep dives
│   ├── algorithms/       ← patterns & data-structure tricks (e.g. Staircase_Search.md, Stack_Queue.md)
│   └── java/             ← Java idioms & gotchas (e.g. JAVA_NOTES.md)
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

> 💡 Deep explanations live under **notes/**, not in `notes.md` — the per-problem note is usually just a link and stays lean.

---

## 🛠️ Tooling

Scaffolding, test-running, and solution management are powered by 🦅 **[KestrelCP](https://github.com/DNT-Khoa/kestrelcp)** — it spins up problem directories, fetches samples, and runs tests so the focus stays on *solving*. 🚀
