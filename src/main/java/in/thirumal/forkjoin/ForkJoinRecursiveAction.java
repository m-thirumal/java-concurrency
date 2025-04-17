/**
 * 
 */
package in.thirumal.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 
 */
public class ForkJoinRecursiveAction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
		forkJoinPool.invoke(new RecursiveActionExample(2221));
		System.out.println("Parallelism: " + forkJoinPool.getParallelism());
		System.out.println("PoolSize: " + forkJoinPool.getPoolSize());
		System.out.println("Queued Submission Count : " + forkJoinPool.getQueuedSubmissionCount());
		System.out.println("Running thread count : " + forkJoinPool.getActiveThreadCount());
		System.out.println("Is shutdown : " + forkJoinPool.isShutdown());
		System.out.println("Is Terminiating : " + forkJoinPool.isTerminating());
		try {
			forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		forkJoinPool.shutdownNow();
		System.out.println("Is Terminiated : " + forkJoinPool.isTerminated());
	}

}

class RecursiveActionExample extends RecursiveAction {

	int threshold = 0;
	
	public RecursiveActionExample (int threshold) {
		this.threshold = threshold;
	}
	
	@Override
	protected void compute() {
		if (this.threshold > 10) {
			System.out.println("Max Threshold is " + threshold + " on thread " + Thread.currentThread().getName());
			int subTask1threshold = threshold  / 2;
			int subTask2threshold = this.threshold - subTask1threshold;
			RecursiveActionExample subTask1 = new RecursiveActionExample(subTask1threshold);
			RecursiveActionExample subTask2 = new RecursiveActionExample(subTask2threshold);
			subTask1.fork();
			subTask2.fork();
		} else {
			System.out.println("Threshold is " + threshold + " on thread " + Thread.currentThread().getName());
		}
	}
	
}
