	/**
 * 
 */
package in.thirumal.t3pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Thirumal
 *
 */
public class CountDownLatchBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(3);

		ExecutorService executorService = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 13; i++) {
			executorService.execute(new Task1(countDownLatch));
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed....");
	}

} 

class Task1 implements Runnable {

	private CountDownLatch latch;
	
	
	public Task1(CountDownLatch latch) {
		this.latch = latch;
	}


	@Override
	public void run() {
		System.out.println("Started...");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		latch.countDown();
	}
	
}