# Lock

## Class Level Lock

* A `class-level` lock is associated with class object representing a loaded class.

* It's used to synchronize static method or a block of code inside the static method.

* Since, the lock is tied to `static` (i.e class object)

```java
synchronized (LockExample.class) {
    // Static synchronized method implementation
}
```

## Object Level Lock

* An `Object-level` lock is associated with an instance of the class

* It's used to synchronize the method or block of the code inside the method.

* Each object has it's own lock.

```java
synchronized (LockExample.class) {
    // Static synchronized method implementation
}

```

## ReentrantLock 

A ReentrantLock is a synchronization mechanism that allows threads to have more control over locks. Unlike the synchronized keyword, ReentrantLock provides a variety of features, such as:

* `Explicit Locking`: You can explicitly acquire and release the lock.
* `Fairness Policy`: Allows threads to acquire locks in the order they request them.
* `Interruptible Locking`: Threads can interrupt waiting threads.
* `TryLock`: Attempts to acquire a lock without blocking indefinitely.
* `Reentrant Behavior`: A thread that holds the lock can reacquire it without deadlocking itself.
* `ReadWriteLock`:  Provides separate locks for reading and writing, allowing multiple threads to read concurrently while ensuring that only one thread can write at a time. This is useful in scenarios where read operations dominate.

[ReentrantReadWriteLockBasic.java](ReentrantReadWriteLockBasic.java ':include :type=code')