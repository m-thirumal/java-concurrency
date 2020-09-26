/**
 * 
 */
package in.thirumal.t3pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author Thirumal
 * 
 * Basic Thread Pool task using Executor service
 * 
 */
public class ExecuterBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		ThreadFactory threadFactory = new ThreadFactory() {
//
//			@Override
//			public Thread newThread(Runnable r) {
//				Thread thread = new Thread();
//				thread.setPriority(Thread.MAX_PRIORITY);
//				
//				return thread;
//			}
//			
//		};
//		
		
//		ExecutorService executorService = Executors.newFixedThreadPool(2, threadFactory);
		
		//ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(12);
		
		ScheduledFuture<?> remainder = scheduledExecutorService.scheduleAtFixedRate(()->{
			System.out.println("hello");
		}, 0, 1, TimeUnit.SECONDS);
		
		
		for (int i = 0; i < 5; i++) {
			executorService.submit(new Task(i));
		}
		System.out.println(remainder.isDone());
		executorService.shutdown();
		
		try {
			executorService.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}

class Task implements Runnable {

	int i;
	
	public Task(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ": " + i);
	}
	
}
