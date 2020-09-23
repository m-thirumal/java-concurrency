package in.thirumal.t6semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Thirumal
 *
 */
public class SemaphoreBasic {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 200; i++) {
			executorService.submit(()->{
				Connection.getInstance().connect();
			});
		}
		
		
		executorService.shutdown();
		
		try {
			executorService.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Connection {
	
	private int numberOfConnection = 0;
	
	private static Connection instance = new Connection();
	
	private Semaphore semaphore = new Semaphore(1);
	
	private Connection() {
		
	}
	
	public static Connection getInstance() {
		return instance;
	}
	
	public void connect() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			doConnect();
		} finally {
			semaphore.release();
		}
		
		
	}
	
	public void doConnect() {
		synchronized (this) {
			numberOfConnection++;
			System.out.println("Number of connection: " + numberOfConnection);
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized (this) {
			numberOfConnection--;
			System.out.println("Number of connection: " + numberOfConnection);
		}
	}
	
}
