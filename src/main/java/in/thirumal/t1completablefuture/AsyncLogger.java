package in.thirumal.t1completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncLogger {

	private static ExecutorService logExecutor = Executors.newFixedThreadPool(2);
	
	//
	 public static void logTransaction(String logMessage) {
	        CompletableFuture.runAsync(() -> {
	            // Simulate log writing
	            simulateDelay(500);
	            System.out.println("Logged: " + logMessage);
	        }, logExecutor);
	    }
	 
	private static void simulateDelay(int i) {
		try {
			Thread.sleep(i);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		for (int i = 0 ; i < 10; i++) {
			logTransaction("Log message " + i);
		}
		logExecutor.shutdown();
	}

}
