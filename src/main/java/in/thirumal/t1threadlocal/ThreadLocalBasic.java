/**
 * 
 */
package in.thirumal.t1threadlocal;

/**
 * @author Thirumal
 *
 */
public class ThreadLocalBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * The Java ThreadLocal class enables you to create variables that can only be read and written by the same thread.
		 * Even two thread execute same code,  the two threads cannot see each other's ThreadLocal variables
		 * Java ThreadLocal class, provides a simple way to make code thread safe
		 */
	
		ThreadLocal<String> threadLocal = new ThreadLocal<>();
		
		Thread thread1 = new Thread(()-> {
			threadLocal.set("From Thread - 1");
			System.out.println("Thread Local Value in Thread 1: " + threadLocal.get());
		});
		
		Thread thread2 = new Thread(()-> {
			System.out.println("Thread Local Value in Thread 2: " + threadLocal.get());
			threadLocal.set("From Thread - 2");
			System.out.println("Thread Local Value in Thread 2: " + threadLocal.get());
			threadLocal.remove();
			System.out.println("After remove from thread 2 : " + threadLocal.get());
		});
		
		thread1.start();
		thread2.start();
	}

}
