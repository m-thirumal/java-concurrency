/**
 * 
 */
package in.thirumal.t1threadlocal;

/**
 * @author Thirumal
 *
 */
public class InheritableThreadLocalBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadLocal<String> threadLocal = new ThreadLocal<>();
		
		InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
		
		Thread parentThread = new Thread(() -> {
			threadLocal.set("Value set in parent Thread");
			System.out.println("Accessing THREAD LOCAL from Parent thread: " + threadLocal.get());
			
			inheritableThreadLocal.set("Value set in parent Thread");
			System.out.println("Accessing INHERITABLE THREAD LOCAL from Parent thread: " + inheritableThreadLocal.get());
			
			Thread childThread = new Thread(()-> {
				System.out.println("Accessing THREAD LOCAL from child thread: " + threadLocal.get());
				
				System.out.println("Accessing INHERITABLE THREAD LOCAL from child thread: " + inheritableThreadLocal.get());
				
			});
			
			childThread.start();
		});
		
		parentThread.start();

	}

}
