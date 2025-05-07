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