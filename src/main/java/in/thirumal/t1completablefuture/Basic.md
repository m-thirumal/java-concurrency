# Completable Future

Introduces in Java 8, represents a future result of asynchronus computation, providing powerful & flexible way to write non-blocking, asynchronous code.

Unlike `future`, which is limited to blocking calls, `completablefuture` supports,

* Chain of tasks            - Attach a sequence of tasks to run after the completion of another.
* Combining multiple task   - Merge results from multiple asynchronous tasks
* Exception handling        - Gracefully manage errors without disrupting the entire flow.
* Asynchronus Callbacks     - Execute tasks without blocking the current thread.

## Chaining Dependent Tasks

