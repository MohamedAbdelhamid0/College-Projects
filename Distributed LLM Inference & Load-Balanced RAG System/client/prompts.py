
"""Prompt pool and selection helpers for load testing."""

from __future__ import annotations

import random

PROMPT_POOL = [
    "Explain the difference between round robin and least connections load balancing.",
    "Summarize the benefits of retrieval-augmented generation in one paragraph.",
    "What are two common failure modes in distributed systems?",
    "Describe how a health monitor can help a worker pool recover from failure.",
    "Give a concise explanation of why deterministic benchmarks are useful.",
    "How does a scheduler decide which worker should receive the next request?",
    "List three reasons to keep the prompt format consistent during load testing.",
    "Explain how request tracing helps debug a distributed inference pipeline.",
]


def select_prompt(user_id: int, mode: str = "fixed", seed: int = 42) -> str:
    """Return a prompt from the curated pool using the requested mode."""
    if not PROMPT_POOL:
        raise ValueError("Prompt pool is empty.")

    if mode == "fixed":
        return PROMPT_POOL[user_id % len(PROMPT_POOL)]

    if mode == "seeded":
        rng = random.Random(seed + user_id)
        return rng.choice(PROMPT_POOL)

    if mode == "random":
        return random.choice(PROMPT_POOL)

    raise ValueError(f"Unknown prompt mode: {mode}")