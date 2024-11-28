/**
 * 
 */
package in.thirumal.t1basic;

import java.util.stream.IntStream;

/**
 * @author thirumal
 *
 */
public class RunnableThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = new Thread(()-> IntStream.range(0, 12).forEach(a-> System.out.println(a + " " + Thread.currentThread().getName())), "t");
		t.start();
		
		Thread r1 = new Thread(new RunnableImplement(), "r1");
		
		Thread r2 = new Thread(new RunnableImplement(), "r2");
		
		
		Thread r3 = new Thread (() -> {
			IntStream.range(0, 12).forEach(a-> System.out.println(a + " " + Thread.currentThread().getName()));
		}, "r3");
		
		r1.start();
		r2.start();
		r3.start();
	}

}

class RunnableImplement implements Runnable {
	
	@Override
	public void run() {
		IntStream.range(0, 12).forEach(a-> System.out.println(a + " " + Thread.currentThread().getName()));
	}
}