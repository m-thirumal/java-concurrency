# java-concurrency

### What is Process?

* A process is a `unit of work` in the Operating System
* A process is a instance of program loaded in to memory

### What is Preemptive & Non-Preemptive?

**Preemptive** is non-blocking task. 

**Non-Preemptive**  is blocking task. 

# Concurrency

- Performing multiple task within same time frame but not necessarily executing at the exact same moment.

Concurrency in a CPU is achieved through  `context switching`.

#### How it works

* Context Saving: When CPU switches from one task to another, it saves the current task's state in the memory(Eg: Program counter, registry..)
* Context Loading: The CPU then loads the context of the next task and continues executing it.
* Rapid Switching: The CPU repeats this process, switching between tasks so quickly that it seems like they are running simultaneously.

# Parallelism

- Executing multiple task at exact time by utilizing the multiple cores or processors.
- To achieve parallelism, an application divides its tasks into smaller, independent subtasks. These subtasks are distributed across multiple `CPUs, CPU cores, GPU cores, or similar processing units`, allowing them to be processed in parallel.
- [Basic Understanding](src/main/java/in/thirumal/parallelism/Definition.md)

```java
IntStream.range(0, 100).parallel().forEach(i -> {
        System.out.println(Thread.currentThread().getName() + " processing number: " + i);
       });
```

Output:
```bash
ForkJoinPool.commonPool-worker-6 processing number: 40
ForkJoinPool.commonPool-worker-6 processing number: 41
ForkJoinPool.commonPool-worker-6 processing number: 42
ForkJoinPool.commonPool-worker-2 processing number: 90
ForkJoinPool.commonPool-worker-4 processing number: 15
main processing number: 65
ForkJoinPool.commonPool-worker-3 processing number: 56
ForkJoinPool.commonPool-worker-5 processing number: 43
ForkJoinPool.commonPool-worker-6 processing number: 37
ForkJoinPool.commonPool-worker-2 processing number: 91
ForkJoinPool.commonPool-worker-1 processing number: 31
ForkJoinPool.commonPool-worker-4 processing number: 16
ForkJoinPool.commonPool-worker-7 processing number: 6
......
```

# Synchronization

Synchronization is the coordination or control of threads to ensure the `consitency` when acessing shared resources.

* `Why it’s needed:`
In concurrent/parallel task, shared resources(like variable/data structures) might be accessed or modified by multiple threads. Without `Synchroniztion`, race conditions can occur, leading to the incorrect result.


[Synchronize.java](src/main/java/in/thirumal/Synchronize/Synchronize.java ':include :type=code')


### What is Thread?






* [Thread Life Cycle](ThreadLifecycle.md)
1.  [Basic](src/main/java/in/thirumal/t1basic)
2.  [Synchronize](src/main/java/in/thirumal/t2Synchronize)
3.  [Lock](src/main/java/in/thirumal/t2lock)
4.  [Pool-Executor](src/main/java/in/thirumal/t2poool)
5.  [ThreadLocal](src/main/java/in/thirumal/t1threadlocal)
6.  [DeadLock](src/main/java/in/thirumal/t5deadlock)
7.  [Seamaphore](src/main/java/in/thirumal/t6semaphore)
8.  [Callable & Future](src/main/java/in/thirumal/t1callablefuture)
9.  [Fork Join](src/main/java/in/thirumal/forkjoin)
10. [Complete Future](src/main/java/in/thirumal/t1completablefuture/Basic.md)
11. [Thread safe Data Structure](src/main/java/in/thirumal/t1threadsafedatastructure)
12. [Reactive](src/main/java/in/thirumal/t1reactive)
