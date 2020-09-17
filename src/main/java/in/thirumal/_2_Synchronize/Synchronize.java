/**
 * 
 */
package in.thirumal._2_Synchronize;

/**
 * @author thirumal
 *
 */
public class Synchronize {

	private int count = 0;
	
	//synchronized (Monitor - only one thread can own the object at a given time)
	//Guard the state from race condition
	private synchronized void increment() {
		count++;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Synchronize s = new Synchronize();
		s.synchronizeExample();
	}
	
	private void synchronizeExample() {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 100000; i++) {
				//count++;  //This will cause race condition
				increment(); //Count++ will give wrong result, because it's not synchronized
			}
		});
		
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 100000; i++) {
				//count++;  //This will cause race condition 
				increment();
			}
		});
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println(count);
	}

}
