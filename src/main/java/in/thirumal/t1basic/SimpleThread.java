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
		e.start();
		e1.start();

	}

}

class ExtendThread extends Thread {
	@Override
	public void run() {
		IntStream.range(0, 500).forEach( a-> System.out.println(a + " " + Thread.currentThread().getName()));
	}
}
