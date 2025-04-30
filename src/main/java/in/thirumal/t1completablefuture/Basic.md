# Completable Future

Introduces in Java 8, represents a future result of asynchronus computation, providing powerful & flexible way to write non-blocking, asynchronous code.

Unlike `future`, which is limited to blocking calls, `completablefuture` supports,

* Chain of tasks            - Attach a sequence of tasks to run after the completion of another.
* Combining multiple task   - Merge results from multiple asynchronous tasks
* Exception handling        - Gracefully manage errors without disrupting the entire flow.
* Asynchronus Callbacks     - Execute tasks without blocking the current thread.

!> CompletableFuture is single-use. Once a CompletableFuture is completed (either by normal completion, timeout, or exception), it cannot be reused.

Real-Time use cases:

![Real Time Use case](Real_time_usecase.webp)

## Chaining Dependent Tasks


* Each task depends on the result of the previous one
* The flow is non-blocking, improving system responsiveness.
* `thenApply()` is used to transform the result and pass it to the next stage.

[ChainingDependantTask.java](ChainingDependantTask.java ':include :type=code')

## Combine Result From Multiple Task


* `thenCombine()` merges the results of two independent tasks.

* Ideal for situations where tasks can run in parallel, but their results are needed together.

[CombineResultFromMultipleTask.java](CombineResultFromMultipleTask.java ':include :type=code')

## Handling Timeouts and Fallbacks (Microservice call)

* `completeOnTimeout()` provides a fallback response if the task exceeds the specified timeout.
* Prevents cascading failures in microservices architectures.
* Enhances system availability and user experience.

[ExceptionResilient.java](ExceptionResilient.java ':include :type=code')

## Asynchronous Exception Handling

* exceptionally() handles exceptions without crashing the program.
* Provides meaningful feedback to the user.
* Critical for fault-tolerant systems, especially in financial services.

[ExceptionHandling.java](ExceptionHandling.java ':include :type=code')

## Conclusion


`CompletableFuture` is a game-changer for writing non-blocking, asynchronous code in Java.

* `Parallel Processing:` Improves performance in API aggregations.
* `Task Chaining:` Simplifies complex workflows.
* `Resilience:` Handles failures gracefully with timeouts and exception handling.
* `Scalability:` Reduces thread blocking, enhancing resource utilization.

# RealTime UseCases

## Orchestrating Microservices Without Blocking (Event-Driven Architecture)

* Traditional REST-based microservices often rely on blocking calls, which waste system resource
* Using CompletableFuture, we can orchestrate multiple microservices asynchronously, improving latency and throughput.

[EDA.java](EDA.java ':include :type=code')

## Async Logging to Improve Performance (Batch Processing in Large Systems)

* Logging synchronously can slow down an application, especially under heavy load.
* Using CompletableFuture, we can log asynchronously in batches, improving overall system performance.

[AsyncLogger.java](AsyncLogger.java ':include :type=code')

#### Key Takeaways:

✅ Reduces blocking in high-performance applications.
✅ Batches logs efficiently, preventing I/O bottlenecks.
✅ Improves system responsiveness by logging in a separate thread pool.



## Timeout with Circuit Breaker for Resiliency (Preventing Failures from Propagating)

* Unresponsive services can slow down the entire system.
* A Circuit Breaker pattern prevents repeated calls to a failing service by returning a default response after a timeout.

[ExceptionResilient.java](ExceptionResilient.java ':include :type=code')

#### Key Takeaways:
✅ Prevents cascading failures in distributed systems.
✅ Enhances fault tolerance by providing fallback responses.
✅ Prepares for integration with Resilience4j Circuit Breaker.

## Streaming Large Data Sets Asynchronously (Big Data Processing)

* Big data applications process massive datasets, which can’t be loaded into memory all at once.

* Using CompletableFuture, we can stream data in batches asynchronously.

[AsyncBigDataProcessing.java](AsyncBigDataProcessing.java ':include :type=code')

#### Key Takeaways:

✅ Efficient data processing in real-time systems.
✅ Avoids memory overload by processing in batches.
✅ Scales well for high-volume, real-time analytics.

## Real-time AI Model Predictions (Non-blocking Inference Calls)

* AI/ML models take time to make predictions.
* Instead of blocking the main thread, we can run model inference asynchronously and return the result when ready.

[AsyncBigDataProcessing.java](AsyncBigDataProcessing.java ':include :type=code')

#### Key Takeaways:

✅ Enhances AI-powered applications with non-blocking inference calls.
✅ Prevents UI freezes in real-time applications.
✅ Scalable for high-load recommendation engines.