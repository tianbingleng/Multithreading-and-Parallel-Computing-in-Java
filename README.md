# Multithreading & Parallel-Computing in Java

**Current Version 1.0**

Version History:

Version 1.0 Updated on 12/03/2017 by Tianbing Leng. (Remark: Final version.)

Version 0.1 Updated on 11/28/2017 by Tianbing Leng. (Remark: Commit /Basic package.)


## I. Project Artifacts Organization
The project contains below parts:
+ /Basics
+ /Collections
+ /DiningPhilosopher
+ /StudentLibrary
+ /ParallelAlgorithm
+ /ForkJoinFramework


### /Basics
It includes basic concept of the multithreading and concurrency. Below concepts are covered:
+ Thread
+ Wait & Notify 
+ Lock & Synchronisation
+ Producer & Consumer
+ Semaphore
+ Executor
+ Callable

### /Collections
It includes common Concurrent collections tool usage. Below concepts are covered:
+ Latch
+ Cyclic Barrier
+ Blocking Queue
+ Delay Queue
+ Priority Queue
+ Concurrent Maps
+ Exchanger

### /DiningPhilosopher
It demonstrates the famous Dining Philosopher Problem.

### /StudentLibrary
It demonstrates a real-world concurrency application. (e.g. students read books in the library concurrently.)

### /ParallelAlgorithm
Using parallel algorithm (create threads on multi-cores) to implement below scenarios and compare time with sequential algorithm.
+ MergeSort Comparison (Parallel & Sequential Algorithm)
+ Sum Comparison (Parallel & Sequential Algorithm)

### /ForkJoinFramework
Using ForkJoinFramework to implement below scenarios and compare time with sequential algorithm. Also covered two ForkJoinFramework class in below.

+ RecursiveAction
+ RecursiveTask
+ MergeSort Comparison (Parallel using ForkJoinFramework & Sequential Algorithm)
+ FindMax Comparison (Parallel using ForkJoinFramework & Sequential Algorithm)


