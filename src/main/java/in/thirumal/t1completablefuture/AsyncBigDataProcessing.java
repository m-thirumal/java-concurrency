package in.thirumal.t1completablefuture;

import java.util.concurrent.CompletableFuture;

public class AsyncBigDataProcessing {

	public static void main(String[] args) {
		CompletableFuture<Void> task = CompletableFuture.runAsync(() -> {
            for (int i = 1; i <= 5; i++) {
                processBatch(i);
            }
        });
        task.join();
	}
	
	 private static void processBatch(int batchNumber) {
	        simulateDelay(1000);
	        System.out.println("Processed batch " + batchNumber);
	    }
	
	private static void simulateDelay(int i) {
		try {
			Thread.sleep(i);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
