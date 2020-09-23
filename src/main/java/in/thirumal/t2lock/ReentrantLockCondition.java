/**
 * 
 */
package in.thirumal.t2lock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Thirumal
 *
 */
public class ReentrantLockCondition {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Runner1 runner = new Runner1();
		
		Thread thread1 = new Thread(runner::firstThreadMethod);
		
		Thread thread2 = new Thread(runner::secondThreadMethod);
		
		thread1.start();
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		runner.finished();
	}

}

class Runner1 {
	
	int count = 0;
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	private void increment() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}
	
	public void firstThreadMethod() {
		lock.lock();
		try {
			System.out.println("Waiting........");
			condition.await();
			System.out.println("Woken up.......");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			increment();
		} finally {
			lock.unlock();
		}
	}
	
	public void secondThreadMethod() {
		lock.lock();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Press some key......");
	
		new Scanner(System.in).nextLine();
	
		System.out.println("Got the key.........");
		
		condition.signal();
		
		try {
			increment();
		} finally {
			lock.unlock();
		}
	}
	
	public void finished( ) {
		System.out.println("Total count: " + count);
	}
	
}
