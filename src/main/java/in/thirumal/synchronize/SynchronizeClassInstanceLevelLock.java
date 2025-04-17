/**
 * 
 */
package in.thirumal.synchronize;

/**
 * 
 */
public class SynchronizeClassInstanceLevelLock {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		A a = new A();
		A b = new A();
		Thread thread1  = new Thread (a::instanceLevelLock);
		Thread thread2  = new Thread (b::instanceLevelLock);
		thread1.start();
		thread2.start();
		//
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("==========");
		Thread thread3  = new Thread (() -> a.methodLevelLock());
		Thread thread4  = new Thread (() -> b.methodLevelLock());
		thread3.start();
		thread4.start();
	}

}

class A {
	synchronized void instanceLevelLock() { //Instance Method
		System.out.println(Thread.currentThread().getName() + " entered instanceLevelLock()");
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        System.out.println(Thread.currentThread().getName() + " exiting instanceLevelLock()");
	}
	
	static synchronized void methodLevelLock() {//Class Method
		 System.out.println(Thread.currentThread().getName() + " entered methodLevelLock()");
	        try { Thread.sleep(1000); } catch (InterruptedException e) {}
	        System.out.println(Thread.currentThread().getName() + " exiting methodLevelLock()");
	}
}
