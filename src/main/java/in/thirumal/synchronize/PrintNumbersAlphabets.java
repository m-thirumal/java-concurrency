/**
 * 
 */
package in.thirumal.synchronize;

/**
 * 
 */
public class PrintNumbersAlphabets {

	static Object lock = new Object();
	private static boolean printLetter = true;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread numberTask = new Thread(() -> {
			for (int i = 0 ; i <= 26; i++) {
				synchronized (lock) {
					if (printLetter) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					printLetter = false;
					lock.notify();
				}
				System.out.print(i + " ");
			}
		});
		
		Thread letterTask = new Thread(() -> {
			for (char c = 'A'; c <= 'Z'; c++) {
				synchronized (lock) {
					if (!printLetter) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					printLetter = true;
					lock.notify();
				}
				System.out.print(c + " ");
			}
		});
		
		numberTask.start();
		letterTask.start();
		
		// 
	}

}
