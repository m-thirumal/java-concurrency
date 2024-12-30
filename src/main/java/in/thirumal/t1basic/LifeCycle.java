/**
 * 
 */
package in.thirumal.t1basic;

/**
 * 
 */
public class LifeCycle implements Runnable {
	
	public static Thread thread1;
    public static LifeCycle lifeCycle;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		lifeCycle = new LifeCycle();
		thread1 = new Thread(lifeCycle);
		// thread1 created and is currently in the NEW
        // state.
        System.out.println("State of thread=>1 after creating it - " + thread1.getState());
        thread1.start();

        // thread1 moved to Runnable state
        System.out.println("State of thread=>1 after calling .start() method on it - " + thread1.getState());
	}

	@Override
	public void run() {
		MyThread myThread = new MyThread();
		Thread thread2 = new Thread(myThread);
		 // thread2 created and is currently in the NEW
        // state.
        System.out.println("State of thread=>2 after creating it - " + thread2.getState());
      
        thread2.start();
        
        // thread2 moved to Runnable state
        System.out.println("State of thread=>2 after calling .start() method on it - " + thread2.getState());

        // moving thread2 to timed waiting state
        try {
            // moving thread2 to timed waiting state
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
      
        System.out.println("State of thread=>2 after calling .sleep() method on it - " + thread2.getState());

        try {
            // waiting for thread2 to die
            thread2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
      
        System.out.println("State of thread=>2 when it has finished it's execution - " + thread2.getState());
	}

}



	

class MyThread implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("State of thread=>1 while it called join() method on thread=>2() - " + LifeCycle.thread1.getState());

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}