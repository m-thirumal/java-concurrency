/**
 * 
 */
package in.thirumal.t3pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		for (int i = 0; i < 5; i++) {
			executorService.submit(new Task(i));
		}
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
