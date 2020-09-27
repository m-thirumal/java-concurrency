/**
 * 
 */
package in.thirumal.t1.threadsafedatastructure;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Thirumal
 *
 */
public class AtomicTypeBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executorService  = Executors.newCachedThreadPool();
		
		Counter counter = new Counter();
		for (int i = 0; i < 100000; i++) {
			executorService.submit(() -> {
				counter.increment();
			});
		}
		
		executorService.shutdown();
		
		try {
			executorService.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Result: " + counter.getValue());
	}

}

class Counter {
	private AtomicInteger atomicInteger = new AtomicInteger();
	
	public void increment() {
		atomicInteger.incrementAndGet();
	}
	
	private void decrement() {
		atomicInteger.decrementAndGet();
	}
	
	public int getValue() {
		return atomicInteger.get();
	}
}
