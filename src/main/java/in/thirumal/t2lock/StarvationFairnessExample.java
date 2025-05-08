/**
 * 
 */
package in.thirumal.t2lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ThirumalM
 */
public class StarvationFairnessExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Starvation example in `synchronized`
		System.out.println("---------Synchronized Lock Without Fairness policy with random order-----------------");
		StarvationExample starvationExample = new StarvationExample();
		Runnable starvationRunnable = starvationExample::criticalSection;
		List<Thread> threads = new ArrayList<>();
		for (int i = 0 ; i < 10; i++) {
			Thread t = new Thread(starvationRunnable, "Thread: " + i);
			if (i%2 == 0) {
				t.setPriority(Thread.MAX_PRIORITY);
			}
			threads.add(t);
			t.start();
		}
		
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		// Fairness with Reentrant Lock
		System.out.println("---------Reentrant Lock With Fairness policy with FIFI order-----------------");
		
		FairnessExample fairnessExample = new FairnessExample();
		for (int i = 0 ; i < 10; i++) {
			Thread t = new Thread(() -> {
				fairnessExample.criticalSection();
			}, "Thread : " + i);
			t.start();
		}

	}

}

class StarvationExample {
	
	public synchronized void criticalSection() { //No Fairness with synchronized:
		System.out.println(Thread.currentThread().getName() + " with priority " + Thread.currentThread().getPriority() + " is acquired lock");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() +  " with priority " + Thread.currentThread().getPriority() +" is releases lock");
	}
}

class FairnessExample {
	ReentrantLock lock = new ReentrantLock();
	public void criticalSection() {
		lock.lock();
		try {
	        System.out.println(Thread.currentThread().getName() + " acquired lock");
	        Thread.sleep(1000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    } finally {
	        lock.unlock();
	        System.out.println(Thread.currentThread().getName() + " released lock");
	    }
	}
}
