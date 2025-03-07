# Parallelism

Parallelism means multiple tasks are executed simultaneously.

To achieve parallelism, an application divides its tasks into smaller, independent subtasks. These subtasks are distributed across `multiple CPUs, CPU cores, GPU cores, or similar processing units`, allowing them to be processed in parallel.

## How does Parallelism Works?

Modern CPUs consist of multiple cores. Each core can independently execute a task. Parallelism divides a problem into smaller parts and assigns each part to a separate core for simultaneous processing.

## How to achive?

* Task Division: The problem is broken into smaller independent sub-tasks.
* Task Assignment: Sub-tasks are distributed across multiple CPU cores.
* Execution: Each core processes its assigned task simultaneously.
* Result Aggregation: Results from all cores are combined to form the final output.