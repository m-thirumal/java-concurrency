# Thread LifeCycle

![Thread Life Cycle](./Thread%20Life%20cycle.drawio.svg)


### NEW

Thread is created & has not yet started
```java
Thread thread = new Thread (() -> System.out.println("hello from runnable state"));
```
### RUNNING

* A thread that is ready to run is moved to a runnable state. 

```java
thread.start();
```
* In this state, a thread might actually be running or it might be ready to run at any instant of time. It is the responsibility of the thread scheduler to give the thread, time to run. 

* A multi-threaded program allocates a fixed amount of time to each individual thread. 

* Each and every thread runs for a short while and then pauses and relinquishes the CPU to another thread so that other threads can get a chance to run. 

* When this happens, all such threads that are ready to run, waiting for the CPU and the currently running thread lie in a runnable state.

### BLOCKED

The thread will be in blocked state when it is trying to acquire a lock but currently the lock is acquired by the other thread. The thread will move from the blocked state to runnable state when it acquires the lock.

```java

```

### WAITING

The thread will be in waiting state when it calls `wait()` method or `join()` method. It will move to the runnable state when other thread will notify or that thread will be terminated.

```java
thread.wait(); // -- With no wait timeout--- // Move to runnable state after notify
thread.join(); //-- With no wait timeout---Move to terminate state
LockSupport.park
```

### TIME WAITING

A thread lies in a timed waiting state when it calls a method with a `time-out` parameter. A thread lies in this state until the timeout is completed or until a notification is received. For example, when a thread calls sleep or a conditional wait, it is moved to a timed waiting state.

```java
Thread.sleep
Object.wait // with timeout
Thread.join //with timeout
LockSupport.parkNanos
LockSupport.parkUntil
```

### TERMINATED

A thread terminates because of either of the following reasons: 

* Because it exits normally. This happens when the code of the thread has been entirely executed by the program.

* Because there occurred some unusual erroneous event, like a segmentation fault or an unhandled exception.