/**
 * 
 */
package in.thirumal.t5deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Thirumal
 *
 */
public class DeadLockBasic {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Lock lock1 = new ReentrantLock();
		Lock lock2 = new ReentrantLock();
		
		Thread thread1 = new Thread(() -> {
			lock1.lock();
			lock2.lock();
			try {
				System.out.println(Thread.currentThread().getName());
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		});
		
		Thread thread2 = new Thread(() -> {
			lock2.lock(); //DeadLock occurs.......HERE       //SOLUTION:- 1. Lock it in same order
			lock1.lock();
			
			try {
				System.out.println(Thread.currentThread().getName());
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		});
		
		thread1.start();
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished");
	}

}
