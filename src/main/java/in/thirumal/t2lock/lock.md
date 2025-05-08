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
* `StampedLock`:  Introduces optimistic locking, allowing threads to perform reads without acquiring a lock initially and only acquiring the lock if the data is modified. This can provide better performance in cases where writes are infrequent.


### Reentrancy
A thread holding a ReentrantLock can re-acquire it without blocking.

The ability of a thread to re-acquire a ReentrantLock without blocking is crucial in certain scenarios where recursive or nested locking is required. This is called re-entrancy.

```Java
class Account {
    private final ReentrantLock lock = new ReentrantLock();
    private double balance;

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            logTransaction(amount);
        } finally {
            lock.unlock();
        }
    }

    private void logTransaction(double amount) {
        lock.lock();  // Reentrant: same thread can re-acquire the lock
        try {
            System.out.println("Deposited: " + amount);
        } finally {
            lock.unlock();
        }
    }
}

```

If, it's not a reentrant lock, it would lead `deadlock (i.e self-deadlock)`.

A non-reentrant lock doesn't allow the same thread to acquire the lock again if it already holds it. So, if a thread tries to lock() again before unlock()ing the previous one, it will block indefinitely, waiting for itself to release the lock — which is impossible unless it continues executing.

This leads to a self-deadlock.

### Deadlock Prevention and Avoidance



#### ReadWriteLock

[ReentrantReadWriteLockBasic.java](ReentrantReadWriteLockBasic.java ':include :type=code')

####  Condition Variables with ReentrantLock

ReentrantLock provides Condition objects that allow more advanced thread synchronization compared to traditional  `wait()` and `notify()`. These condition variables can help implement complex thread coordination, such as producer-consumer problems.

```JAVA
ReentrantLock lock = new ReentrantLock();
Condition condition = lock.newCondition();

// Producer thread
new Thread(() -> {
    lock.lock();
    try {
        // Do work
        condition.signal();  // Notify consumer
    } finally {
        lock.unlock();
    }
}).start();

// Consumer thread
new Thread(() -> {
    lock.lock();
    try {
        condition.await();  // Wait for signal
        // Do work
    } finally {
        lock.unlock();
    }
}).start();
```
This allows the consumer thread to wait until the producer thread signals it, ensuring proper coordination between threads.


