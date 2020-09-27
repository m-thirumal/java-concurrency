/**
 * 
 */
package in.thirumal.t2lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Thirumal
 *
 */
public class ReentrantLockBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Runner runner = new Runner();
		
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

class Runner {
	
	int count = 0;
	
	private Lock lock = new ReentrantLock();
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private Lock writeLock = readWriteLock.writeLock(); //Only one thread is allowed
	private Lock readLock = readWriteLock.readLock(); //More than one threads are allowed
	
	private void increment() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}
	
	public void firstThreadMethod() {
		lock.lock();
		try {
			increment();
		} finally {
			lock.unlock();
		}
	}
	
	public void secondThreadMethod() {
		lock.lock();
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
