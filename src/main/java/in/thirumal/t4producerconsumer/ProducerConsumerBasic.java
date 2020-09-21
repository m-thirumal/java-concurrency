/**
 * 
 */
package in.thirumal.t4producerconsumer;

/**
 * @author Thirumal
 *
 */
public class ProducerConsumerBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Proccessor proccessor = new Proccessor();
		Thread producerThread = new Thread(()->  {
			try {
				proccessor.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Thread consumerThread = new Thread(()->  {
			try {
				proccessor.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		producerThread.start();
		consumerThread.start();
		
		try {
			producerThread.join();
			consumerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	

}
