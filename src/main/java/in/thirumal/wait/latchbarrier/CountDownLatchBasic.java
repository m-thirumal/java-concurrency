/**
 * 
 */
package in.thirumal.wait.latchbarrier;

import java.util.concurrent.CountDownLatch;

/**
 * One or more threads wait until others finish something.
 * Reusable - No
 * 
 */
public class CountDownLatchBasic {
	
	public static void main(String[] arg) {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		Thread thread1 = new Thread(() -> {
			System.out.println("Thread 1 started waiting for other threads to complete");
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thread 1 resumed...&  Finished after latch count reached " + countDownLatch.getCount());
		});
		thread1.start();
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Thread(() -> {
				System.out.println("Sub Thread finished task & current latch count " + countDownLatch.getCount());
				countDownLatch.countDown();
			}).start();
		}
		
	}
}
