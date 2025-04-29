/**
 * 
 */
package in.thirumal.threadgroup;

/**
 * 
 */
public class ThreadGroupExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadGroup threadGroup = new ThreadGroup("First");
		System.out.println(threadGroup);
		threadGroup.setMaxPriority(10);
		Thread t1 = new Thread(() -> {
			System.out.println("hi");
		});
		System.out.println(t1.getPriority());
		System.out.println(threadGroup.getMaxPriority());
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
