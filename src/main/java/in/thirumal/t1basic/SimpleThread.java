/**
 * 
 */
package in.thirumal.t1basic;

import java.util.stream.IntStream;

/**
 * @author thirumal
 *
 */
public class SimpleThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExtendThread e = new ExtendThread();
		ExtendThread e1 = new ExtendThread();
		System.out.println("Thread state Before Start => " + e1.getState());
		e.start();
		e1.start();
		try {
			e.join();
			e1.join();
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("Thread state after join => " + e1.getState());
	}

}

class ExtendThread extends Thread {
	@Override
	public void run() {
		IntStream.range(0, 500).forEach( a-> System.out.println(a + " " + Thread.currentThread().getName()));
		System.out.println("Thread state inside Run => " + this.getState());
	}
}
