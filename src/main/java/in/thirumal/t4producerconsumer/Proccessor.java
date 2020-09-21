/**
 * 
 */
package in.thirumal.t4producerconsumer;

import java.util.LinkedList;

/**
 * @author Thirumal
 *
 */
public class Proccessor {
	
	private LinkedList<Integer> list = new LinkedList<>();
	
	private int limit  = 10;
	
	private Object lock = new Object();

	public void produce() throws InterruptedException {
		
		int value = 0;
		
		while(true) {
			synchronized(lock) {
				while (list.size() == limit) {
					lock.wait();
				}
				list.add(value++);
				lock.notify();
			}
		}
		
	}
	
	public void consume() throws InterruptedException {
		while(true) {
			synchronized(lock) {
				
				while (list.size() == 0) {
					lock.wait();
				}
				System.out.print("List size is : " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is " + value);
				lock.notify();
			}
			Thread.sleep(500);
		}
	}
}
